package method.complexity;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.comments.Comment;
import raykernel.apps.readability.eval.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


//import satd_detector.core.utils.SATDDetector;

import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.comments.Comment;

public class Complexity {

    private String commitDate; // complexity at this date
    private SLOC sloc;
    private McCabe mcCabe;
    private McClure mcClure;
    private NestedBlockDepth nestedBlockDepth;
    private IndentationComplexity indentationComplexity;
    private StructuralDependency structuralDependency;
    private Modifiers modifiers;

    private Others others;
    private double readability;

    private double simpleReadability;

    private float maintenanceIndex;
    private Halstead halstead;

    //    private static SATDDetector detector = new SATDDetector();
    private int satd;

    public Complexity() {
        commitDate = null;
        readability = -1;
        simpleReadability = -1;
        maintenanceIndex = -1;
        satd = -1;
        sloc = new SLOC();
        mcCabe = new McCabe();
        mcClure = new McClure();
        nestedBlockDepth = new NestedBlockDepth();
        indentationComplexity = new IndentationComplexity();
        structuralDependency = new StructuralDependency();
        modifiers = new Modifiers();
        others = new Others();
        halstead = new Halstead();
    }


    public void setSimpleReadability(double volume, int sloc, double entropy) {
        // abram's paper: A simpler model of software readability
        double z = 8.87 - (0.033 * volume) + (0.40 * sloc) - (1.5 * entropy);
        this.simpleReadability = 1 / (1 + Math.exp(-z));
    }

    public double getMaintenanceIndex() {
        return maintenanceIndex;
    }

    public void setMaintenanceIndex(double volume, int sloc, int mccabe) {

        //https://docs.microsoft.com/en-us/visualstudio/code-quality/code-metrics-maintainability-index-range-and-meaning?view=vs-2022
        maintenanceIndex = (float) (171 - (5.2 * Math.log(volume)) - (0.23 * mccabe) - (16.2 * Math.log(sloc)));

    }

    public Halstead getHalstead() {
        return halstead;
    }

    public Others getOthers() {
        return others;
    }

    public StructuralDependency getStructuralDependency() {
        return structuralDependency;
    }


    public Modifiers getModifiers() {
        return modifiers;
    }


    public double getReadability() {
        return readability;
    }

    public void setReadability(String code) {
        this.readability = Main.getReadability(code);//directly from the jar
    }

    public IndentationComplexity getIndentationComplexity() {
        return indentationComplexity;
    }


    public NestedBlockDepth getNestedBlockDepth() {
        return nestedBlockDepth;
    }


    public McClure getMcClure() {
        return mcClure;
    }


    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public SLOC getSloc() {
        return sloc;
    }

    public McCabe getMcCabe() {
        return mcCabe;
    }

    public double getSimpleReadability() {
        return simpleReadability;
    }

//    public void evaluateSATD(String code) {
//        try {
//            // Set JavaParser configuration to include comments
//            JavaParser.getStaticConfiguration().setAttributeComments(true);
//            BodyDeclaration<?> cu = JavaParser.parseBodyDeclaration(code);
//            List<Comment> comments = cu.getAllContainedComments();
//
//            // Initialize foundSATD as false and satd value to -2 (default for error)
//            boolean foundSATD = false;
//            setSatd(-2);
//
//            // Define the absolute path to the JAR file
//            String jarPath = "complexityAnalyzer/libs/satd/satd/1.0.0/satd_detector-1.0.0.jar";
//
//            for (Comment comment : comments) {
//                // Create a command to run the SATD detector JAR file with the comment content as input
//                String command = "java --add-opens java.base/java.lang=ALL-UNNAMED -jar " + jarPath + " test " + comment.getContent();
//                System.out.println("Executing command: " + command);
//                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//                Process process = processBuilder.start();
//
//                // Read the output from the process
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // Print standard output of the subprocess
//                    System.out.println(line);
//                    // Check for "SATD" in the output
//                    if (line.contains("SATD")) {
//                        setSatd(1);
//                        foundSATD = true;
//                        break;
//                    } else if (line.contains("Not SATD")) {
//                        setSatd(0);
//                    }
//                }
//
//                if (foundSATD) {
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            setSatd(-1); // Set satd to -1 if an exception occurs
//        }
//    }

//    public void evaluateSATD(String code) {
//        try {
//            // Set JavaParser configuration to include comments
//            JavaParser.getStaticConfiguration().setAttributeComments(true);
//            BodyDeclaration<?> cu = JavaParser.parseBodyDeclaration(code);
//            List<Comment> comments = cu.getAllContainedComments();
//
//            // Initialize foundSATD as false and satd value to -2 (default for error)
//            boolean foundSATD = false;
//            setSatd(-2);
//
//            // Define the absolute path to the JAR file
//            String jarPath = "complexityAnalyzer/libs/satd/satd/1.0.0/satd_detector-1.0.0.jar";
//
//            for (Comment comment : comments) {
//                // Create a command to run the SATD detector JAR file with the comment content as input
//                String command = "java --add-opens java.base/java.lang=ALL-UNNAMED -jar " + jarPath + " test " + comment.getContent();
//                System.out.println("Executing command: " + command);
//                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//                Process process = processBuilder.start();
//
//                // Read the output from the process
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    // Print standard output of the subprocess
//                    System.out.println("SATD detector output: " + line);
//                    // Check for "SATD" in the output
//                    if (line.contains("SATD")) {
//                        setSatd(1);
//                        foundSATD = true;
//                        break;
//                    } else if (line.contains("Not SATD")) {
//                        setSatd(0);
//                    }
//                }
//
//                if (foundSATD) {
//                    break;
//                }
//            }
//
//            // If no SATD was found, set satd value to 0 if it wasn't already set to 1 or 0
//            if (!foundSATD) {
//                if (getSatd() == -2) {
//                    setSatd(0);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            setSatd(-1); // Set satd to -1 if an exception occurs
//        }
//    }

//    public void evaluateSATD(String code) {
//        try {
//            // Set JavaParser configuration to include comments
//            JavaParser.getStaticConfiguration().setAttributeComments(true);
//            BodyDeclaration<?> cu = JavaParser.parseBodyDeclaration(code);
//            List<Comment> comments = cu.getAllContainedComments();
//
//            // Initialize foundSATD as false and satd value to -2 (default for error)
//            boolean foundSATD = false;
//            setSatd(-2);
//
//            // Define the path to the JAR file in the classpath
//            String jarPath = getClass().getClassLoader().getResource("satd_detector-1.0.0.jar").getPath();
//
//            System.out.println("Comments found: " + comments.size());
//
//            for (Comment comment : comments) {
//                System.out.println("Processing comment: " + comment.getContent());
//
//                // Create a command to run the SATD detector JAR file with the comment content as input
//                String command = "java --add-opens java.base/java.lang=ALL-UNNAMED -jar " + jarPath + " test " + comment.getContent();
//                System.out.println("Executing command: " + command);
//                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//                Process process = processBuilder.start();
//
//                // Read the output from the process
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
//                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
//
//                String line;
//                boolean enteredWhileLoop = false;
//
//                while ((line = reader.readLine()) != null) {
//                    enteredWhileLoop = true;
//                    // Print standard output of the subprocess
//                    System.out.println("SATD detector output: " + line);
//                    // Check for "SATD" in the output
//                    if (line.contains("SATD")) {
//                        setSatd(1);
//                        foundSATD = true;
//                        break;
//                    } else if (line.contains("Not SATD")) {
//                        setSatd(0);
//                    }
//                }
//
//                // Print if the while loop was entered
//                if (!enteredWhileLoop) {
//                    System.out.println("Did not enter while loop for comment: " + comment.getContent());
//                }
//
//                // Read the error output from the process
//                while ((line = errorReader.readLine()) != null) {
//                    System.err.println("SATD detector error output: " + line);
//                }
//
//                if (foundSATD) {
//                    break;
//                }
//            }
//
//            // If no SATD was found, set satd value to 0 if it wasn't already set to 1 or 0
//            if (!foundSATD) {
//                if (getSatd() == -2) {
//                    setSatd(0);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            setSatd(-1); // Set satd to -1 if an exception occurs
//        }
//    }

    public void evaluateSATD(String code)
    {
        // String jarPath = "C:\\Users\\hamza\\OneDrive - University of Manitoba\\Documents\\HISHAM\\Research\\Tech Debt\\software-evolution-copy-branch\\software-evolution\\tools\\Bug-prediction\\libs\\satd\\satd\\1.0.0\\satd_detector-1.0.0.jar";
//        String jarPath = "D:\\OneDrive - University of Manitoba\\Documents\\HISHAM\\Research\\Tech Debt\\SATD-Dectector-Core-Tool\\satd_detector.jar";
        String jarPath = "/home/student/kidwaih1/Documents/Tech-Debt/software-evolution/tools/Bug-prediction/libs/satd/satd/1.0.0/satd_detector-1.0.0.jar";
        JavaParser.getStaticConfiguration().setAttributeComments(true);
        BodyDeclaration cu = JavaParser.parseBodyDeclaration(code);
        List<Comment> comments = cu.getAllContainedComments();
        setSatd(0);

        // Print all the comments
//        System.out.println("Comments found: " + comments);

        for (Comment comment : comments)
        {
            // Convert comment of Comment type to String
            String commentContent = comment.getContent();
//            System.out.println("\n\nProcessing comment: " + commentContent + "\n from code: " + code);
            boolean stop = checkIfSATD(jarPath, commentContent);
            if (stop)
            {
                break;
            }
        }
    }
/*
    private boolean checkIfSATD(String jarPath, String comment) {
        try
        {
            // Has to be a list of strings because the ProcessBuilder constructor takes a list of strings
            // Each entry in the list is a command line argument
            // Each command separated by a space in the command line is an entry in the list
            List<String> command = List.of(
                    "java",
                    "--add-opens",
                    "java.base/java.lang=ALL-UNNAMED",
                    "-jar",
                    jarPath,
                    "test"
            );

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Basically passing the comment to check for SATD in as the input
            // like how we would in the command line
//            OutputStream os = process.getOutputStream();
//            try (OutputStream os = process.getOutputStream())
////            try
//            {
//                os.write(comment.getBytes());
//                os.flush();
//                os.close();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }

            // Read the process output
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
//            try
            {
                String line = reader.readLine();
                System.out.println("Processing line: " + line);
                boolean isSATD = true; // true bc we will always get a "SATD" from the detector
                while (line != null)
                {
                    // Important that we check if the line contains "Not SATD"
                    // Instead of checking for "SATD" as "SATD" is always present
                    if (line.contains("Not SATD"))
                    {
                        isSATD = false;
                    }
                    // Note how if we use the below print statement,
                    // it prints the same thing twice
                    // This is probably because the first line is the commetn and the second line is the result

                    // System.out.println(isSATD ? "SATD" : "Not SATD");
                    line = reader.readLine();
                }
//                System.out.println(isSATD);
                System.out.println(isSATD ? "SATD" : "Not SATD");
                setSatd(isSATD ? 1 : -5);

                if (isSATD)
                {
                    return true;
                }
                // close
//                reader.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


            // use this cuz apparantly bad things can happen if we dont include it
            process.waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
*/

    private boolean checkIfSATD(String jarPath, String comment) {
        try {
            List<String> command = List.of(
                    "java",
                    "--add-opens",
                    "java.base/java.lang=ALL-UNNAMED",
                    "-jar",
                    jarPath,
                    "test"
            );

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Write the comment to the process's output stream
            try (OutputStream os = process.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
//                System.out.println("Writing comment to process: " + comment);
                writer.write(comment);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Read the process's input stream
            boolean isSATD = true; // Assume SATD unless "Not SATD" is found
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line == null) {
//                    System.out.println("No output from process");
                }
                while (line != null) {
//                    System.out.println("Processing line: " + line);
                    if (line.contains("Not SATD")) {
                        isSATD = false;
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Capture and print the error stream
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String errorLine = errorReader.readLine();
                while (errorLine != null) {
//                    System.out.println("Error: " + errorLine);
                    errorLine = errorReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
//            System.out.println("Process exited with code: " + exitCode);

//            System.out.println(isSATD ? "SATD" : "Not SATD");
            setSatd(isSATD ? 1 : 0);

            return isSATD;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    public void evaluateSATD(String code) {
//        try {
//            // Set JavaParser configuration to include comments
//            JavaParser.getStaticConfiguration().setAttributeComments(true);
//            BodyDeclaration<?> cu = JavaParser.parseBodyDeclaration(code);
//            List<Comment> comments = cu.getAllContainedComments();
//
//            // Initialize foundSATD as false and satd value to -2 (default for error)
//            boolean foundSATD = false;
//            setSatd(-2);
//
//            // Define the absolute path to the JAR file
//            String jarPath = "complexityAnalyzer/libs/satd/satd/1.0.0/satd_detector-1.0.0.jar";
//
//            System.out.println("Comments found: " + comments.size());
//
//            for (Comment comment : comments) {
//                System.out.println("Processing comment: " + comment.getContent());
//
//                // Create a command to run the SATD detector JAR file with the comment content as input
//                String command = "java --add-opens java.base/java.lang=ALL-UNNAMED -jar " + jarPath + " test " + comment.getContent();
//                System.out.println("Executing command: " + command);
//                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//                Process process = processBuilder.start();
//
//                // Read the output from the process
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
//                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
//
//                String line;
//                boolean enteredWhileLoop = false;
//
//                while ((line = reader.readLine()) != null) {
//                    enteredWhileLoop = true;
//                    // Print standard output of the subprocess
//                    System.out.println("SATD detector output: " + line);
//                    // Check for "SATD" in the output
//                    if (line.contains("SATD")) {
//                        setSatd(1);
//                        foundSATD = true;
//                        break;
//                    } else if (line.contains("Not SATD")) {
//                        setSatd(0);
//                    }
//                }
//
//                // Print if the while loop was entered
//                if (!enteredWhileLoop) {
//                    System.out.println("Did not enter while loop for comment: " + comment.getContent());
//                }
//
//                // Read the error output from the process
//                while ((line = errorReader.readLine()) != null) {
//                    System.err.println("SATD detector error output: " + line);
//                }
//
//                if (foundSATD) {
//                    break;
//                }
//            }
//
//            // If no SATD was found, set satd value to 0 if it wasn't already set to 1 or 0
//            if (!foundSATD) {
//                if (getSatd() == -2) {
//                    setSatd(0);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            setSatd(-1); // Set satd to -1 if an exception occurs
//        }
//    }

//    public void evaluateSATD(String code) {
//        JavaParser.getStaticConfiguration().setAttributeComments(true);
//        BodyDeclaration cu = JavaParser.parseBodyDeclaration(code);
//        List<Comment> comments = cu.getAllContainedComments();
//        boolean foundSATD = false;
//        setSatd(-2);
////        for (Comment comment: comments){
//////            setSatd(-2);
//////            foundSATD = true;
//////            break;
//////            System.out.println(comment.getContent());
////
////            if (detector.isSATD(comment.getContent())) {
//////                System.out.println("it's SATD");
////                setSatd(1);
////                foundSATD = true;
////                break;
////            }
////        }
////           if (!foundSATD){
////              setSatd(0);
////          }
//
//    }

    public int getSatd() {
        return satd;
    }

    public void setSatd(int satd) {
        this.satd = satd;
    }

}
