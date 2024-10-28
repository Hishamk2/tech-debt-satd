package method.complexity;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class ModifiersTest {

    static Modifiers modifier;

    @Test
    public void testCalculateMcClure() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        modifier = new Modifiers();
        File file = new File(classLoader.getResource("modifiers/private.txt").getFile());
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        modifier.extractModifiers(content);
        assertEquals(1, modifier.isPrivate());
        assertEquals(0, modifier.isPublic());
        assertEquals(0, modifier.isStatic());


        modifier = new Modifiers();
        file = new File(classLoader.getResource("modifiers/public.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        modifier.extractModifiers(content);
        assertEquals(0, modifier.isPrivate());
        assertEquals(1, modifier.isPublic());
        assertEquals(1, modifier.isStatic());

        modifier = new Modifiers();
        file = new File(classLoader.getResource("modifiers/protected.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        modifier.extractModifiers(content);
        assertEquals(1, modifier.isProtected());
        assertEquals(1, modifier.isStatic());
        assertEquals(0, modifier.isPublic);


        modifier = new Modifiers();
        file = new File(classLoader.getResource("modifiers/getter.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        assertEquals(1, modifier.checkGetter(content));

        modifier = new Modifiers();
        file = new File(classLoader.getResource("modifiers/setter.txt").getFile());
        content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        assertEquals(1, modifier.checkSetter(content));

    }
}