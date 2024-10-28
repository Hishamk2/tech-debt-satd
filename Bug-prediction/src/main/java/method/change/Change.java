package method.change;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.printer.PrettyPrinter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import main.Main;
import util.Util;
import org.apache.commons.lang3.StringUtils;
//import satd_detector.core.utils.SATDDetector;


import java.util.*;

public class Change {

    // source: On the “Naturalness” of Buggy Code
    // must say that the word issue does not fit here
    private int isBuggy;
    private int isPotentiallyBuggy;
    private int isRisky;
    private String commitDate; // changed happened at this date
    private int diffSize;
    private int newAdditionOnly;
    private int editDistance;
    private int criticalEditDistance;
    private int tangledCommit;


    private int tangledCommitWOMoveandRename;

    //private  static SATDDetector detector = new SATDDetector();

    public void setTangledCommit(int tangledCommit) {
        this.tangledCommit = tangledCommit;
    }

    public Change(){
        commitDate = null;
        tangledCommit = -1;
        tangledCommitWOMoveandRename = -1;
        diffSize = -1;
        editDistance = -1;
        criticalEditDistance = -1;
        newAdditionOnly = -1;
        isBuggy = -1;
        isPotentiallyBuggy = -1;
        isRisky = -1;

    }

    public void setTangledCommit(Map<String, Object> commitDetails){
        setTangledCommit(Main.listTangledCommit.get((String) commitDetails.get("commitName")));
    }

    public void setTangledCommitWOMoveandRename(Map<String, Object> commitDetails){
        try {
            setTangledCommitWOMoveandRename(Main.listTangledCommitWOMoveandFileRename.get((String) commitDetails.get("commitName")));
        }catch(Exception e){
            setTangledCommitWOMoveandRename(0); //this commit did never change any content.. that's why it does not exist in the list
        }
    }

    public void calculateDiffSize( Map<String, Object> commitDetails){
        List<Integer> diffSize = new ArrayList<>();
        if(commitDetails.get("diff")== null){
            diffSize =  Util.processMultiChange(commitDetails);
        }
        else {
            diffSize = Util.calculateDiffSize((String) commitDetails.get("diff"));
        }

        setNewAdditionOnly(diffSize.get(0));
        setDiffSize(diffSize.get(1));
     //   System.out.println(getNewAdditionOnly()+","+ getDiffSize());

    }


    public int calculateEditDistanceCritical(String code1, String code2){

        PrettyPrinterConfiguration conf = new PrettyPrinterConfiguration();
        conf.setPrintComments(false);
        PrettyPrinter prettyPrinter = new PrettyPrinter(conf);

        MethodDeclaration methodDeclaration = JavaParser.parseBodyDeclaration(code1).asMethodDeclaration();
        String convertedCode1 = prettyPrinter.print(methodDeclaration);

        methodDeclaration = JavaParser.parseBodyDeclaration(code2).asMethodDeclaration();
        String convertedCode2 = prettyPrinter.print(methodDeclaration);

        int distance = StringUtils.getLevenshteinDistance(convertedCode1, convertedCode2);
        setCriticalEditDistance(distance);
        return  distance;
    }
    public int calculateEditDistance(String code1, String code2){
        int distance = StringUtils.getLevenshteinDistance(code1, code2);
        setEditDistance(distance);
        return  distance;
    }
    public void  calculateBugCommit(Map<String, Object> commitDetails){

        //commitMessage
        String commitMessage = (String) commitDetails.get("commitMessage");
        isBuggy= 0;
        isPotentiallyBuggy = 0;
        isRisky = 0;

        BugFixDetector.isBugFix(commitMessage);

        if(BugFixDetector.isBuggy){
            isBuggy = 1;
        }
        if(BugFixDetector.isPotentiallyBuggy){
            isPotentiallyBuggy = 1;
        }

        if(BugFixDetector.isRisky){
            isRisky = 1;
        }
        setIsBuggy(isBuggy);
        setIsPotentiallyBuggy(isPotentiallyBuggy);
        setIsRisky(isRisky);
    }


    public int getIsBuggy() {
        return isBuggy;
    }
    public void setIsBuggy(int isBuggy) {
        this.isBuggy = isBuggy;
    }
    public int getIsPotentiallyBuggy(){return isPotentiallyBuggy;}
    public void setIsPotentiallyBuggy(int isPotentiallyBuggy){this.isPotentiallyBuggy = isPotentiallyBuggy; }

    public int getIsRisky() {
        return isRisky;
    }

    public void setIsRisky(int isRisky) {
        this.isRisky = isRisky;
    }


    public int getNewAdditionOnly() {
        return newAdditionOnly;
    }

    public void setNewAdditionOnly(int newAdditionOnly) {
        this.newAdditionOnly = newAdditionOnly;
    }


    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public int getDiffSize() {
        return diffSize;
    }

    public void setDiffSize(int diffSize) {
        this.diffSize = diffSize;
    }

    public int getEditDistance() {
        return editDistance;
    }

    public void setEditDistance(int editDistance) {
        this.editDistance = editDistance;
    }

    public int getCriticalEditDistance(){
        return criticalEditDistance;
    }

    public void setCriticalEditDistance(int criticalEditDistance) {
        this.criticalEditDistance = criticalEditDistance;
    }

    public int getTangledCommit() {
        return tangledCommit;
    }
    public int getTangledCommitWOMoveandRename() {
        return tangledCommitWOMoveandRename;
    }

    public void setTangledCommitWOMoveandRename(int tangledCommitWOMoveandRename) {
        this.tangledCommitWOMoveandRename = tangledCommitWOMoveandRename;
    }



}
