package util;

import main.Main;
import util.readwrite.FileOperations;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Util {

    static Map<String, Object> commitDetails;
    static List<Integer> diffSize;

    public static int calculateNumberOfAllChanges(Map<String, Object> changeHistoryDetails){
        return changeHistoryDetails.size()-1;// exclude introduction
    }

    public static List<Integer> processMultiChange(Map<String, Object> commitDetails){

        List<Integer> diffSize = new ArrayList<>();
        List<Map<String, Object>> subchanges = (List<Map<String, Object>>) commitDetails.get("subchanges");
        for(Map<String, Object> element: subchanges){
            // System.out.println(element.get("diff"));
            diffSize = calculateDiffSize((String) element.get("diff"));

            break;
        }
        return diffSize;
    }

    public static List<Integer> calculateDiffSize(String code){

//        System.out.println(code);
        String[] lines = code.split("\r\n|\r|\n");
        List<Integer> list = new ArrayList<>();
        int countTotal = 0;
        int countAdditionOnly = 0;

        for(String line:lines){
            if(line.startsWith("+")){
                countAdditionOnly++;
            }
            if(line.startsWith("+") || line.startsWith("-")){
                countTotal++;
            }
        }

        list.add(countAdditionOnly);
        list.add(countTotal);
        return list;
    }



    public static void listTangledCommits() {
        File[] files = FileOperations.returnAllFiles();
        for (File jsonFile : files) {
            try {
                Map<String, Object> data = FileOperations.loadJson(jsonFile);
                Map<String, Object> changeHistoryDetails = (Map<String, Object>) data.get("changeHistoryDetails");

                for (String shawAsString: changeHistoryDetails.keySet()) {
                    if (Main.listTangledCommit.containsKey(shawAsString)){
                        Main.listTangledCommit.put(shawAsString, Main.listTangledCommit.get(shawAsString) + 1);
                    }
                    else{
                        Main.listTangledCommit.put(shawAsString, 1);
                    }

                    // this is to calculate tangled without filerename and methodmove
                    // we also check multichange..

                    commitDetails = (Map<String, Object>) changeHistoryDetails.get(shawAsString);

                    if(commitDetails.get("type").toString().equals("Yfilerename")
                            || commitDetails.get("type").toString().equals("Ymovefromfile")
                            || commitDetails.get("type").toString().equals("Ymultichange(Ymovefromfile,Yfilerename)")
                            ||  commitDetails.get("type").toString().equals("Ymultichange(Yfilerename,Ymovefromfile)")

                    ){
                        continue;
                    }

                    if (Main.listTangledCommitWOMoveandFileRename.containsKey(shawAsString)){
                        Main.listTangledCommitWOMoveandFileRename.put(shawAsString, Main.listTangledCommitWOMoveandFileRename.
                                get(shawAsString) + 1);
                    }
                    else{
                        Main.listTangledCommitWOMoveandFileRename.put(shawAsString, 1);
                    }

                }

            } catch (Exception e) {
            }

        }
    }

    public static String returnFormattedDate(String date){

        String[] data =  date.split(" ");
        return data[0];
    }
    public static int calculateNumberOfRealChanges(Map<String, Object> changeHistoryDetails){
        int count=0;
        for (String commit: changeHistoryDetails.keySet()){
            Map<String, Object> commitDetails = (Map<String, Object>)changeHistoryDetails.get(commit);

            if(commitDetails.get("type").equals("Yfilerename") ){
                continue;
            }
            count++;
        }
        return (count - 1); // exclude intro

    }

    public static String removeEndingSemiColon(String code){
        if(code.substring(code.length() - 1).equals(";")){
            // some methods has a ending semicolon producing parsing error
            code = code.substring(0, code.length() - 1);
        }
        return code;
    }

    //source: https://www.programcreek.com/2011/04/a-method-to-detect-if-string-contains-1-uppercase-letter-in-java/

    public static boolean isAllUpperCase(String str){
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c >= 97 && c <= 122) {
                return false;
            }
        }
        //str.charAt(index)
        return true;
    }

    //source https://stackoverflow.com/questions/767759/occurrences-of-substring-in-a-string
    public static int countOccurence(String str, String findStr){
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
       return count;
    }

    public static String sourceMultiChange( Map<String, Object> commitDetails){

        List<Map<String, Object>> subchanges = (List<Map<String, Object>>) commitDetails.get("subchanges");
        String code = null;

        for(Map<String, Object> element: subchanges){
            code = (String) element.get("actualSource");
            break;
        }

        return code;
    }

    public static int calculateTimeDifference(String givenDate, String introDate){

        //source https://mkyong.com/java/how-to-calculate-date-time-difference-in-java/

        int timeDifference = -1;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(introDate);
            d2 = format.parse(givenDate);
            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            timeDifference = (int) diffDays;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeDifference;
    }


    public static double calculateEntropy(byte fileContent[]) {
    /*
    source code from https://github.com/willjasen/entropy/blob/master/entropy.java
    I have never tested this code
    */


        // create array to keep track of frequency of bytes
        int []frequency_array = new int[256];
        int fileContentLength = fileContent.length-1;

        // count frequency of occuring bytes
        for(int i=0; i<fileContentLength; i++) {
            byte byteValue=fileContent[i];
            frequency_array[Byte.toUnsignedInt(byteValue)]++;
        }

        // calculate entropy
        double entropy = 0;
        for(int i=0;i<frequency_array.length;i++) {
            if(frequency_array[i]!=0) {
                // calculate the probability of a particular byte occuring
                double probabilityOfByte=(double)frequency_array[i]/(double)fileContentLength;

                // calculate the next value to sum to previous entropy calculation
                double value = probabilityOfByte * (Math.log(probabilityOfByte) / Math.log(2));
                entropy = entropy + value;
            }
            else {}
        }
        entropy *= -1;

        return  entropy;
    }

}
