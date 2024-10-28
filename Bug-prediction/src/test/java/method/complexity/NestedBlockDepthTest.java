package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class NestedBlockDepthTest {

    static NestedBlockDepth nestedBlockDepth;

    @BeforeClass
    public static void setup(){
        nestedBlockDepth = new NestedBlockDepth();
    }

    @Test
    public void testCalculateNBD() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("nestedBlockDepth/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        nestedBlockDepth.calculateNBD(content);
        assertEquals(7, nestedBlockDepth.getNestedBlockDepthValue());

        file = new File(classLoader.getResource("nestedBlockDepth/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        nestedBlockDepth.calculateNBD(content);
        assertEquals(6, nestedBlockDepth.getNestedBlockDepthValue());

        file = new File(classLoader.getResource("nestedBlockDepth/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        nestedBlockDepth.calculateNBD(content);
        assertEquals(3, nestedBlockDepth.getNestedBlockDepthValue());

        file = new File(classLoader.getResource("nestedBlockDepth/sample4.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        nestedBlockDepth.calculateNBD(content);
        assertEquals(2, nestedBlockDepth.getNestedBlockDepthValue());

    }
}