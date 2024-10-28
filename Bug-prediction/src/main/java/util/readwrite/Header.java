package util.readwrite;

public class Header {


    public static String getBasicHeaders(){

        return
                        "Age\t" +
                        "SLOCAsItIs\t" +
                        "SLOCNoCommentPrettyPrinter\t"+
                        "SLOCStandard\t"+
                        "CommentCodeRation\t"+
                        "Readability\t"+
                         "SimpleReadability\t"
                ;
    }

    public static String getOtherHeaders(){

        return
                "Parameters\t" +
                "LocalVariables\t" +
                "EnclosedExpressions\t"+
                "MaxEnclosedNesting\t"
                ;
    }

    public static String getComplexityHeaders(){

        return

                "NVAR\t"+
                "NCOMP\t"+
                "Mcclure\t"+
                "McCabe\t"+
                "McCabeWOCases\t"+
                "IndentSTD\t"+
                "MaximumBlockDepth\t"
                ;
    }

    public static String getHalsteadHeaders(){

        return

                 "n1\t"+
                 "n2\t"+
                 "N1\t"+
                 "N2\t"+
                 "Vocabulary\t"+
                 "Length\t"+
                 "Volume\t"+
                 "Difficulty\t"+
                 "Effort\t"+
                 "Time\t"+
                 "HalsteadBugs\t"+
                 "MaintainabilityIndex\t"
                ;
    }

    public static String getModifier(){

        return "isPrivate\t"+
                "isProtected\t"+
                "isDefault\t"+
                "isPublic\t"+
                "isStatic\t"+
                "isAbstract\t"+
                "isGetterSetter\t"+
                "SATD\t"
                ;
    }

    public static String getStructuralHeaders(){
        return "totalFanOut\t"
               + "uniqueFanOut\t"
                ;
    }

    public static String getDeveloperHeader(){

        return
                "Developers\t"
                ;
    }

    public static String getChangeHeaders(){

        return
                "ChangeAtMethodAge\t"+
                 "ProjectAge\t"+
                 "CommitDate-ProjectFirstDate\t"+
                 "NewAdditions\t"+
                "DiffSizes\t"+
                "EditDistances\t"+
                "CriticalEditDistances\t" +
                "TangledCommit\t"+
                "TangledWMoveandFileRename\t"+
                "Buggycommiit\t"+
                 "PotentiallyBuggycommit\t"+
                 "RiskyCommit\t"+
                "file"
                ;
    }


}
