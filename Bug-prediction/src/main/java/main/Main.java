package main;

//import method.complexity.Halstead;
import util.readwrite.FileOperations;
import org.apache.commons.cli.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import util.Util;
public class Main {


    public static Map<String, Integer> listTangledCommit; // multiple methods were changed in one commit
    public static Map<String, Integer> listTangledCommitWOMoveandFileRename; //
    public static void main(String[] args) throws Exception {

        setSettings(args);

        File f = new File(Settings.PROJECTS);;

        String lines = FileOperations.loadAsString(f);
        String[] projects = lines.split("\r\n|\r|\n");

        for(String project: projects){
          init(project);
          Calculation.startProcess();
          System.out.println("Total Methods: "+Calculation.totalFiles);
          System.out.println("Test Methods that were not filtered before: "+Calculation.containTestWord);
          System.out.println("Total Parsing Problems: "+Calculation.parsingProblem);
          System.out.println("No Introduction: "+Calculation.noIntroduction);
          System.out.println("Succssfull Methods: "+Calculation.successfull);
          System.out.println("###########################################");

        }


    }

    private static void setSettings(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("projectsInfo",true, "Which file contains all the project names and date");
        options.addOption("codeShovelHistoryDir",true, "Which directory contains JSON history");
        options.addOption("resultDir",true, "Where should the result go");
        options.addOption("filterOutTestMethods",true, "don't consider test methods");
        options.addOption("halsteadPath",true, "provide halstead jar file path");
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
            String infoFile = line.getOptionValue("projectsInfo");
            String historyDir = line.getOptionValue("codeShovelHistoryDir");
            String resultDir = line.getOptionValue("resultDir");
            String noTest = line.getOptionValue("filterOutTestMethods");
            String halstead = line.getOptionValue("halsteadPath");

        //    if(halstead!=null){
        //        Settings.HALSTED_PATH = halstead;
        //    }

            if(infoFile!=null){
                Settings.PROJECTS = infoFile;
            }
            if(historyDir != null){
                Settings.JSON_HISTORY_DIR = historyDir;
            }
            if(resultDir != null){
                Settings.RESULT_DIR = resultDir;
            }

            if(noTest != null){
                Settings.FILTER_OUT_TEST_METHODS = Boolean.parseBoolean(noTest);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void init(String project){

        String[] data =  project.split("\t");
        Settings.PROJECT = data[0];
        Settings.CURRENTDATE = data[1];
        Settings.PROJECT_FIRST_DATE = data[3];
        Calculation.parsingProblem=0;
        Calculation.noIntroduction=0;
        Calculation.successfull=0;
        Calculation.totalFiles=0;
        Calculation.containTestWord = 0;
        Calculation.progress = 0;
        listTangledCommit = new HashMap<>();
        listTangledCommitWOMoveandFileRename = new HashMap<>();

        System.out.println("\nRunning for:"+Settings.PROJECT);

        Util.listTangledCommits();
        System.out.println("Done with listing Tangled Changes");
    }
}
