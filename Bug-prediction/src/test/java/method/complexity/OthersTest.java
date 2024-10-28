package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class OthersTest {

    static Others others;

    @BeforeClass
    public static void setup(){
        others = new Others();
    }

    @Test
    public void testParameters() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("others/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        others.calculateOthers(content);
        int param = others.getNumberOfParameters();
        assertEquals(2, param);

    }

    @Test
    public void testEnclosedExpression() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("others/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        others.calculateOthers(content);
        int no = others.getNumberOfEnclosedExpressions();
        assertEquals(14, no);

         no = others.getMaxEnclosedNesting();
        assertEquals(4, no);
    }

    @Test
    public void testLocalVariables() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("others/sample2.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        others.calculateOthers(content);
        int no = others.getNumberOfLocalVariables();
        assertEquals(7, no);

    }



}