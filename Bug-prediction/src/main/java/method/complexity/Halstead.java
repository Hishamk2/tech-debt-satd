package method.complexity;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import main.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import gui.MainScreen;
public class Halstead {
    static int count =0;
   public float n1, n2, N1, N2, vocabulary, length, volume, effort, difficulty, time, bugs;

    public Halstead(){
        n1=-1;
        n2=-1;
        N1=-1;
        N2= -1;
        vocabulary = -1;
        length = -1;
        volume = -1;
        effort =-1;
        difficulty =-1;
        time =-1;
        bugs =-1;
    }

    public void calculateHalstead(String code) {
        code = "class Jewel{\n"+code+"\n}";

        JavaParser.getStaticConfiguration().setAttributeComments(false);
        CompilationUnit cu = JavaParser.parse(code);
        code = cu.toString();
        //System.out.println(code);
        Map<String, Float> halsteadMetrics = MainScreen.start(code);
       // System.out.println(count++);

        n1 = halsteadMetrics.get("n1") ;
        n2 = halsteadMetrics.get("n2");
        N1 = halsteadMetrics.get("N1") ;
        N2 = halsteadMetrics.get("N2");
        vocabulary = halsteadMetrics.get("vocabulary") ;
        length = halsteadMetrics.get("length") ;
        volume = halsteadMetrics.get("volume");
        effort = halsteadMetrics.get("effort");
        difficulty = halsteadMetrics.get("difficulty");
        time = halsteadMetrics.get("time");
        bugs = halsteadMetrics.get("bugs");

    }

}
