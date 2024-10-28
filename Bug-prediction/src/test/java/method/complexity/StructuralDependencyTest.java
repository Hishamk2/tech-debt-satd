package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class StructuralDependencyTest {

    static StructuralDependency structuralDependency;

    @BeforeClass
    public static void setup(){
        structuralDependency = new StructuralDependency();
    }

    @Test
    public void testCalculateFanOut() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource("structural/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        structuralDependency.calculateFanOut(content);
        assertEquals(4, structuralDependency.getTotalFanOut());
        assertEquals(3, structuralDependency.getUniqueFanOut());

        file = new File(classLoader.getResource("structural/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        structuralDependency.calculateFanOut(content);
        assertEquals(5, structuralDependency.getTotalFanOut());
        assertEquals(3, structuralDependency.getUniqueFanOut());

    }
}