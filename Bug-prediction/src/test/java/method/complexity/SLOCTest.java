package method.complexity;

import com.github.javaparser.JavaParser;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class SLOCTest {

   static SLOC sloc;

    @BeforeClass
    public static void setup(){
        sloc = new SLOC();
    }

    @Test
    public void testCalculateSLOCAsItIs() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("sloc/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        int asItIs = sloc.calculateSLOCAsItIs(content);
        assertEquals(3, asItIs);

        file = new File(classLoader.getResource("sloc/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        asItIs = sloc.calculateSLOCAsItIs(content);
        assertEquals(6, asItIs);

        file = new File(classLoader.getResource("sloc/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        asItIs = sloc.calculateSLOCAsItIs(content);
        assertEquals(23, asItIs);
    }



    @Test
    public void testCalculateNoCommentSLOCPretty() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("sloc/sample2.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        int pretty = sloc.calculateNoCommentSLOCPretty(content);
        assertEquals(3, pretty);


        file = new File(classLoader.getResource("sloc/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        pretty = sloc.calculateNoCommentSLOCPretty(content);
        assertEquals(4, pretty);

    }


    @Test
    public void testCalculateSlocStandard() throws IOException{

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("sloc/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        int standard = sloc.calculateSlocStandard(content);
        assertEquals(3, standard);


        file = new File(classLoader.getResource("sloc/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(4, standard);

        file = new File(classLoader.getResource("sloc/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(6, standard);


        file = new File(classLoader.getResource("sloc/sample4.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(6, standard);

        file = new File(classLoader.getResource("sloc/sample5.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(4, standard);


        file = new File(classLoader.getResource("sloc/sample7.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(9, standard);


        file = new File(classLoader.getResource("sloc/sample9.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(9, standard);

        file = new File(classLoader.getResource("sloc/sample9.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(9, standard);


        file = new File(classLoader.getResource("sloc/sample10.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        standard = sloc.calculateSlocStandard(content);
        assertEquals(3, standard);


    }

    @Test
    public void testComments() throws IOException{

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("sloc/sample7.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        int lines = sloc.calculateLinesOfComments(content);
        assertEquals(1, lines);

        file = new File(classLoader.getResource("sloc/sample8.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        lines = sloc.calculateLinesOfComments(content);
        assertEquals(10, lines);

        file = new File(classLoader.getResource("sloc/sample9.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        lines = sloc.calculateLinesOfComments(content);
        assertEquals(14, lines);

        file = new File(classLoader.getResource("sloc/sample10.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        lines = sloc.calculateLinesOfComments(content);
        assertEquals(2, lines);

        file = new File(classLoader.getResource("sloc/sample11.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        lines = sloc.calculateLinesOfComments(content);
        assertEquals(3, lines);
    }

    @Test
    public void testRatio() throws IOException{

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("sloc/sample7.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        sloc.calculate(content);
        double rat = sloc.getCommentCodeRatio();
        assertEquals(0.1111111111111111, rat);

    }

}