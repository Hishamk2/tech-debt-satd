package main;

public class Settings {

  public  static String BASE_PATH="/home/shaiful/research/method-level-bug-prediction/mydata/";
  public  static String PROJECTS=BASE_PATH+"info/settings-project-with-first-date.txt";
 // public final static String BASE_DIR_SOURCE_CODE ="/home/shaiful/research/codeshovel/codeshovel-projects/";
  public  static String JSON_HISTORY_DIR ="/home/student/kidwaih1/Documents/Tech-Debt/source-methods-49-projects/source-methods/";
  public  static String RESULT_DIR=BASE_PATH+"data/";
// for test projects, filtering would be false
  public static boolean FILTER_OUT_TEST_METHODS = true;
//  public static String HALSTED_PATH = "/home/shaiful/research/code-metrics/software-evolution/jars/halstead.jar";

  // THE FOLLOWING VALUES DOES NOT MATTER AS EVERYTHING WILL COME FROM FILE
  // I.E., SETTINGS.PROJECTS

  public static String PROJECT="";
  public static String CURRENTDATE =""; //dd/mm/yy
  public static String PROJECT_FIRST_DATE = "";

//this will change based on the project last commit where the methods' history started
// is not needed when working from file (S)

// mytest project for accuracy
//  public static String CURRENTDATE ="29/06/20"; //dd/mm/yy

//checkstyle
//  public final static String CURRENTDATE ="01/05/20"; //dd/mm/yy

//hadoop
//  public final static String CURRENTDATE ="27/05/2020";

//commons-io
// public final static String CURRENTDATE ="25/12/19"; //dd/mm/yy

//lucene-solr
//public final static String CURRENTDATE ="01/02/20"; //dd/mm/yy

//mockito
//public final static String CURRENTDATE ="19/08/18"; //dd/mm/yy

//hibernate-search
// public final static String CURRENTDATE ="24/07/18"; //dd/mm/yy

// jetty (had to follow codeshovel's commit)
//Fri Aug 17 16:57:28 2018 -0500

// flink
//    Fri May 1 01:12:12 2020 +0800


}
