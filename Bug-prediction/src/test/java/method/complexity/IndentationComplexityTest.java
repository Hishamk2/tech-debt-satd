package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

public class IndentationComplexityTest {

    static IndentationComplexity indentationComplexity;
    static DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Test
    public void testCalculateIndentation() throws IOException {

        indentationComplexity = new IndentationComplexity();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("indentation/sample1.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        indentationComplexity.calculateIndentation(content);
        assertEquals(9, indentationComplexity.getSum());
        assertEquals(decimalFormat.format(2.70), decimalFormat.format(indentationComplexity.getVariance()));
        assertEquals(decimalFormat.format(1.47), decimalFormat.format(indentationComplexity.getStandardDeviation()));
        assertEquals(decimalFormat.format(1.80), decimalFormat.format(indentationComplexity.getAverage()));
        assertEquals(decimalFormat.format(3), decimalFormat.format(indentationComplexity.getMedian()));

        indentationComplexity = new IndentationComplexity();
        file = new File(classLoader.getResource("indentation/sample2.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        indentationComplexity.calculateIndentation(content);
        assertEquals(20, indentationComplexity.getSum());
        assertEquals(decimalFormat.format(9.07), decimalFormat.format(indentationComplexity.getVariance()));
        assertEquals(decimalFormat.format(2.75), decimalFormat.format(indentationComplexity.getStandardDeviation()));
        assertEquals(decimalFormat.format(3.33), decimalFormat.format(indentationComplexity.getAverage()));
        assertEquals(decimalFormat.format(4), decimalFormat.format(indentationComplexity.getMedian()));

        indentationComplexity = new IndentationComplexity();
        file = new File(classLoader.getResource("indentation/sample3.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        indentationComplexity.calculateIndentation(content);
        assertEquals(40, indentationComplexity.getSum());
        assertEquals(decimalFormat.format(36.27), decimalFormat.format(indentationComplexity.getVariance()));
        assertEquals(decimalFormat.format(5.50), decimalFormat.format(indentationComplexity.getStandardDeviation()));
        assertEquals(decimalFormat.format(6.67), decimalFormat.format(indentationComplexity.getAverage()));
        assertEquals(decimalFormat.format(8), decimalFormat.format(indentationComplexity.getMedian()));

    }


}

