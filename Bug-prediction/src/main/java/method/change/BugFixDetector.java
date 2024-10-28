/*

confirmed buggy vs confirmed not buggy:
The co-occurence of an action related keyword (e.g, fix) and a bug related keyword (e.g., bug, error)
in a sentence (a commit can have multiple sentences) confirms the commit is a buggy commit.
However, even one of their existence (either action related or bug related) can indicate a commit as buggy, which is why
we never label them as non buggy (we consider them risky).

The below example is a proof that we should not consider the keyword "issue" in confirmed buggy.

"commitMessage": "Fixing things in the code.\nStyle issues.\n\n\ngit-svn-id: http://argouml.tigris.org/svn/argouml/trunk@6936 a161b567-7d1e-0410-9ef9-912c70fedb3f\n",

Buggy method from the paper, "On the “Naturalness” of Buggy Code": Each commit has an associated commit log. We mark a commit
as bugfix, if the corresponding commit log contains at least one of
the error related keywords: ‘error’, ‘bug’, ‘fix’, ‘issue’, ‘mistake’,
‘incorrect’, ‘fault’, ‘defect’ and ‘flaw’,


 */

package method.change;

import edu.stanford.nlp.simple.Document;

import java.util.HashSet;
import java.util.Set;

public class BugFixDetector {

    static Document doc;


    static boolean actionFound;
    static boolean buggyFound;

    // can not label a commit as non-buggy is risky is true
    static boolean isPotentiallyBuggy; // at least one hit from our two list..
    static boolean isBuggy;

    static boolean isRisky; // buggy according to the naturallness paper.
    static Set<String> riskyWords = new HashSet<String>()
    {{

        // according to the naturalness of buggy code paper
        add("error");
        add("errors");
        add("bug");
        add("bugs");
        add("fix");
        add("fixes");
        add("fixed");
        add("fixing");
        add("issue");
        add("issues");
        add("mistake");
        add("mistakes");
        add("mistaken");
        add("incorrect");
        add("fault");
        add("faults");
        add("faulty");
        add("defect");
        add("defects");
        add("defected");
        add("defective");
        add("flaw");
        add("flaws");
        add("flawed");

    }};

    static Set<String> actions = new HashSet<String>() {{
        add("fix");
        add("fixes");
        add("fixed");
        add("fixing");
        add("solve");
        add("solves");
        add("solved");
        add("solving");
        add("resolve");
        add("resolves");
        add("resolved");
        add("resolving");
        add("repair");
        add("repairs");
        add("repairing");
        add("address");
        add("addresses");
        add("addressing");
        add("addressed");

    }};

    static Set<String> buggyWords = new HashSet<String>() {{

        add("bug");
        add("bugs");
        add("buggy");
        add("bugzilla");
        add("error");
        add("errors");
        add("erroneous");
        add("problem");
        add("problems");
        add("problematic");
        add("misfeature");
        add("misfeatures");
        add("fault");
        add("faults");
        add("faulty");
        add ("flaw");
        add ("flaws");
        add ("flawed");
        add("defect");
        add("defects");
        add("defected");
        add("defective");
        add("mistake");
        add("mistakes");
        add("mistaken");

    }};
    public  static void isBugFix (String commitMessage){

        isPotentiallyBuggy = false;
        isBuggy = false;
        isRisky = false;

        doc = new Document(commitMessage);

        for (edu.stanford.nlp.simple.Sentence sentence : doc.sentences()) {

            for (String word : sentence.words()) {
                if (riskyWords.contains(word.toLowerCase())) {
                    isRisky = true;
                    break;
                }

                if (isRisky){
                    break;
                }
            }
        }

        if(commitMessage.toLowerCase().contains("bug#") || commitMessage.contains("bug #")) {
            isBuggy = true; // from observation
        }


        actionFound = false;
        buggyFound = false;

        for (edu.stanford.nlp.simple.Sentence sentence : doc.sentences()) {

            for (String word :sentence.words()) {

                if (actions.contains(word.toLowerCase())) {
                    actionFound = true;
                    isPotentiallyBuggy = true;
                }
            }

            for (String word :sentence.words()) {
                if (buggyWords.contains(word.toLowerCase())) {
                    buggyFound = true;
                    isPotentiallyBuggy = true;
                }

            }
            if (actionFound == true && buggyFound == true) {
                isBuggy = true;
                break;
            }

            actionFound = buggyFound = false; // restart for a new sentence
        }

    }
}
