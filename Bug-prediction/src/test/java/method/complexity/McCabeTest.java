package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class McCabeTest {

    static McCabe mcCabe;

    @BeforeClass
    public static void setup(){
         mcCabe = new McCabe();
    }

    @Test
    public void testCalculateMcCabe() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("mccabe/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcCabe.calculateMcCabe(content);
        assertEquals(1, mcCabe.getMcCabeComplexity());
        assertEquals(1, mcCabe.getMcCabeCompleixtyWOcases());

        file = new File(classLoader.getResource("mccabe/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcCabe.calculateMcCabe(content);
        assertEquals(10, mcCabe.getMcCabeComplexity());
        assertEquals(7, mcCabe.getMcCabeCompleixtyWOcases());

        file = new File(classLoader.getResource("mccabe/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcCabe.calculateMcCabe(content);
        assertEquals(8, mcCabe.getMcCabeComplexity());
        assertEquals(8, mcCabe.getMcCabeCompleixtyWOcases());

    }
}