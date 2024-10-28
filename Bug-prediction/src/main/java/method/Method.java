package method;

import main.Calculation;
import method.change.Change;
import method.complexity.Complexity;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Util;

public class Method {

    static String previousCode = "";
    private File jsonfileName; // this contains method history from CodeShovel
    private String introDate;// the method was introduced.
    private List<Complexity> listComplexity;// for each source in each commit
    private int age;
    private List<Change> listChange;


    private List<String> developers;
    // will delete this after all tests
   // private List<String> listSourceCode;// for each source in each commit


    public Method(){
        age =-1;// error checking
        introDate = null;
    //    listSourceCode = new ArrayList<>();// the order is in reverse
        listComplexity = new ArrayList<>();// the order is in reverse
        listChange = new ArrayList<>(); // the order is from introduction
        developers = new ArrayList<>(); // the order is in reverse..
    }

    public void calculateMetrics(String code, Map<String, Object> commitDetails){


        Complexity complexity = new Complexity();
        developers.add((String ) commitDetails.get("commitAuthor"));
        complexity.setReadability(code);
        complexity.setCommitDate((String) commitDetails.get("commitDate"));
        complexity.getSloc().calculate(code);
        complexity.getMcCabe().calculateMcCabe(code);
        complexity.getMcClure().calculateMcClure(code);
        complexity.getNestedBlockDepth().calculateNBD(code);
        complexity.getIndentationComplexity().calculateIndentation(code);
        complexity.getStructuralDependency().calculateFanOut(code);
        complexity.getModifiers().extractModifiers(code);
        complexity.getOthers().calculateOthers(code);
        complexity.getHalstead().calculateHalstead(code);
        complexity.evaluateSATD(code);
        complexity.setMaintenanceIndex(complexity.getHalstead().volume, complexity.getSloc().getSlocStandard(),
                complexity.getMcCabe().getMcCabeComplexity());

        double entropy = Util.calculateEntropy(code.getBytes(StandardCharsets.UTF_8));
        complexity.setSimpleReadability(complexity.getHalstead().volume, complexity.getSloc().getSlocAsItIs(),
                entropy);
        listComplexity.add(complexity);
        complexity = null;
    }

    public void processChange(Map<String, Object> changeHistoryDetails){

        ArrayList<Map<String,Object>> forReverseHistory = new ArrayList<>();

        for (String shawAsString: changeHistoryDetails.keySet()) {
            Map<String, Object> commitDetails = (Map<String, Object>) changeHistoryDetails.get(shawAsString);
            forReverseHistory.add(commitDetails);
        }

        // find the introduction commit
        Map<String, Object> commitDetails = (Map<String, Object>) forReverseHistory.get(forReverseHistory.size()-1);
        previousCode = Calculation.extractSourceCode(commitDetails);

        for(int i = forReverseHistory.size()-2; i>=0;i--){ // ignore introduction from change, so start from -2

           commitDetails = (Map<String, Object>) forReverseHistory.get(i);


/*
            if we enable this, we need to enable inside Calculation.java as sell

           if (commitDetails.get("type").toString().contains("Yfilerename")
               || commitDetails.get("type").toString().contains("Ymovefromfile")) {
                continue;
           }

 */

            Change change = new Change();
            change.setCommitDate((String) commitDetails.get("commitDate"));
            change.calculateDiffSize(commitDetails);
            change.calculateBugCommit(commitDetails);
            change.calculateBugCommit(commitDetails);
            String code = Calculation.extractSourceCode(commitDetails);
            change.calculateEditDistance(previousCode, code);
            change.calculateEditDistanceCritical(previousCode, code);
            change.setTangledCommit(commitDetails);
            change.setTangledCommitWOMoveandRename(commitDetails);
            previousCode = code; // update the current code as previous code
            listChange.add(change);
            change = null;
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List<Change> getListChange() {
        return listChange;
    }

    public void addListChange(Change change){
        listChange.add(change);
    }

  //  public List<String> getListSourceCode() {
  //      return listSourceCode;
  //  }

  //  public void addListSourceCode(String code) {
   //     listSourceCode.add(code);
   // }

    public List<Complexity> getListComplexity() {
        return listComplexity;
    }

    public void addComplexity(Complexity complexity) {
        this.listComplexity.add(complexity);
    }

    public String getIntroDate() {
        return introDate;
    }

    public void setIntroDate(String introDate) {
        this.introDate = introDate;
    }

    public File getJsonfileName() {
        return jsonfileName;
    }

    public void setJsonfileName(File jsonfileName) {
        this.jsonfileName = jsonfileName;
    }

    public List<String> getDevelopers() {
        return developers;
    }

}
