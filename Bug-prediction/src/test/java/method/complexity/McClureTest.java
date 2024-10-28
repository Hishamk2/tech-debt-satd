package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class McClureTest {

    static McClure mcClure;

    @Test
    public void testCalculateMcClure() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        mcClure = new McClure();
        File file = new File(classLoader.getResource("mcclure/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcClure.calculateMcClure(content);
        assertEquals(6, mcClure.getMCLC());
        assertEquals(3, mcClure.getNCOMP());
        assertEquals(3, mcClure.getNVAR());


        mcClure = new McClure();
        file = new File(classLoader.getResource("mcclure/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcClure.calculateMcClure(content);
        assertEquals(10, mcClure.getMCLC());
        assertEquals(7, mcClure.getNCOMP());
        assertEquals(3, mcClure.getNVAR());

        mcClure = new McClure();
        file = new File(classLoader.getResource("mcclure/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        mcClure.calculateMcClure(content);
        assertEquals(8, mcClure.getNCOMP());
        assertEquals(4, mcClure.getNVAR());
        assertEquals(12, mcClure.getMCLC());

    }
}