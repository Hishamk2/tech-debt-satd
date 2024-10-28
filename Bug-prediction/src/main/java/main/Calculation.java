package main;

import com.github.javaparser.JavaParser;
import method.Method;
import util.Util;
import util.readwrite.FileOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Calculation {

    static Method method;

    static int processedMethods = 0;
    static int progress = 0;
    static int parsingProblem=0;
    static int noIntroduction=0;
    static int successfull=0;
    static int totalFiles=0;
    static int containTestWord = 0;
//    static List<Method> allMethods = new ArrayList<>();

    public static void startProcess(){


        try {
            FileOperations.writeHeaders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File[] files = FileOperations.returnAllFiles();
        for(File jsonFile: files){
            totalFiles++;
           // if(totalFiles==10)
           //     break;
            try {

                Map<String, Object> data = FileOperations.loadJson(jsonFile);
                Map<String, Object> changeHistoryDetails= (Map<String, Object>) data.get("changeHistoryDetails");

                // this is because, we initially forgot to apply this filtering when we were
                // extracting source methods
                if(Settings.FILTER_OUT_TEST_METHODS){
                    if (data.get("sourceFilePath").toString().toLowerCase().contains("test")
                            || data.get("functionName").toString().toLowerCase().contains("test")){
                        containTestWord++;
                        continue;

                    }

                }

                parseMethodProperties(jsonFile, changeHistoryDetails);

               // startCalculation(jsonFile, changeHistoryDetails, commitDetails);

            } catch (IOException e) {
                System.out.println(jsonFile);
                e.printStackTrace();
            }

        }

    }

    private static void parseMethodProperties(File jsonFile, Map<String, Object> changeHistoryDetails){
        String code = null;
        method =new Method();;
        method.setJsonfileName(jsonFile);
        boolean foundIntroduction = false;
        String introDate = null;

        for (String shawAsString: changeHistoryDetails.keySet()) {

            Map<String, Object> commitDetails = (Map<String, Object>) changeHistoryDetails.get(shawAsString);

/*
            if we enable this, we need to enable inside Method.java as well
            if(commitDetails.get("type").toString().contains("Yfilerename")
            || commitDetails.get("type").toString().contains("Ymovefromfile")){
                continue;
            }

 */

            if(commitDetails.get("type").toString().contains("Yintroduced")){
                foundIntroduction = true;
                introDate = (String) commitDetails.get("commitDate");
                method.setIntroDate(introDate);
            }

            code = extractSourceCode(commitDetails);
           // if(method.getJsonfileName().toString().contains("3040.json"))  {
           //     System.out.println(code);
           // }
            method = processMethod(code, method, commitDetails);

            if (method == null){
                break;
            }
        }

        if(method!=null){// there was no parsing problem

            if (foundIntroduction) {// introduction commit found by codeshovel

                successfull++;
                method.processChange(changeHistoryDetails);
                method.setAge(Util.calculateTimeDifference(Settings.CURRENTDATE, method.getIntroDate()));

           //     testAmethod(method);

                saveMethod(method);
                
                processedMethods++;
                if(processedMethods >2000){
                    processedMethods = 0;
                    System.gc();
                }

            }
            else{
                noIntroduction++;
            }
        }
    }


    private static void saveMethod(Method method){
        try {
            FileOperations.saveResult(method);

            method = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Method processMethod(String code, Method method, Map<String, Object> commitDetails) {

        try {
            // try to parse, so stops if there is a parsing problem
            JavaParser.parseBodyDeclaration(code);
            method.calculateMetrics(code,  commitDetails);

           // method.addListSourceCode(code);

        }catch(Exception e){
          //  System.out.println(code);
            parsingProblem++;
            method = null;
            return null;
        }
        return method;
    }

    public static String extractSourceCode(Map<String, Object> commitDetails) {
        String code;

        if(commitDetails.get("type").toString().contains("Ymultichange")){
            code = Util.sourceMultiChange(commitDetails);
            code = Util.removeEndingSemiColon(code);
        }

        else{
            code = Util.removeEndingSemiColon((String) commitDetails.get("actualSource"));
        }
        return code;
    }

    /*
    public static void testAmethod(Method method){

       if(method.getJsonfileName().toString().equals("/home/shaiful/research/datasets/software-evolution/source-methods/checkstyle/1513.json")){
           System.out.println(method.getJsonfileName());
           for(int i =method.getListComplexity().size()-1;i>=0;i--){
               SLOC sloc = method.getListComplexity().get(i).getSloc();
               McCabe mcCabe = method.getListComplexity().get(i).getMcCabe();
               McClure mcClure = method.getListComplexity().get(i).getMcClure();
               NestedBlockDepth nestedBlockDepth = method.getListComplexity().get(i).getNestedBlockDepth();
               IndentationComplexity indentationComplexity = method.getListComplexity().get(i).getIndentationComplexity();
               StructuralDependency structuralDependency = method.getListComplexity().get(i).getStructuralDependency();
             //  Modifiers modifiers = method.getListComplexity().get(i).getModifiers();

          //     System.out.println(method.getListSourceCode().get(i));
         //      System.out.println(sloc.getSlocAsItIs()+","+sloc.getSlocNoCommentPretty()+","+sloc.getSlocStandard());
         //      System.out.println(mcCabe.getMcCabeComplexity());
          //     System.out.println(mcClure.getMCLC()+"\t"+mcClure.getNCOMP()+"\t"+mcClure.getNVAR());
           //    System.out.println(nestedBlockDepth.getNestedBlockDepthValue());
            //   System.out.println(indentationComplexity.getSum()+","+indentationComplexity.getAverage()+","+
              //         indentationComplexity.getMedian()+","+indentationComplexity.getMedian()+","+indentationComplexity.getStandardDeviation());
              // System.out.println(structuralDependency.getTotalFanOut()+","+structuralDependency.getUniqueFanOut());
               //System.out.println(modifiers.isStatic()+","+modifiers.isPublic()+","+modifiers.isPrivate());
              // System.out.println(method.getListComplexity().get(i).getCommitDate());
           //    System.out.println(Util.calculateTimeDifference(method.getListComplexity().get(i).getCommitDate(), method.getIntroDate()));

           }

           for(int i=0;i<method.getListChange().size();i++){
               Change change = method.getListChange().get(i);
               System.out.println(change.getDiffSize()+","+change.getEditDistance()+"-"+Util.calculateTimeDifference(change.getCommitDate(),method.getIntroDate()));
           }
        //   System.out.println(method.getIntroDate());
       }

   }*/



}
