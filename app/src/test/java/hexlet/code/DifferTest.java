package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DifferTest {

    TestVariables vrb = new TestVariables();
    String stylish = vrb.getExpStylishResult();
    String plain = vrb.getExpPlainResult();
    String json = vrb.getExpJsonResult();
    String correctJsonFilePath1 = vrb.getCorrectJsonFilePath1();
    String correctJsonFilePath2 = vrb.getCorrectJsonFilePath2();
    String correctYamlFilePath1 = vrb.getCorrectYamlFilePath1();
    String correctYamlFilePath2 = vrb.getCorrectYamlFilePath2();
    String emptyJsonFilePath = vrb.getEmptyJsonFilePath();
    String emptyYamlFilePath = vrb.getEmptyYamlFilePath();
    String invalidJsonFilePath = vrb.getInvalidJsonFilePath();
    String invalidYamlFilePath = vrb.getInvalidYamlFilePath();
    String invalidPath = vrb.getInvalidPath();
    String noJsonYamlFilePath = vrb.getNoJsonYamlFilePath();

    @Test
    public void correctFilesGenerateTest() {

        try {
            // REDUCED ARGUMENT Differ.generate()
            // JSON
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            // YAML
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
            // NO/UNKNOWN FORMAT
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
            // EMPTY FILES
            assertEquals("", Differ.generate(emptyJsonFilePath, emptyJsonFilePath));
            assertEquals("", Differ.generate(emptyYamlFilePath, emptyYamlFilePath));
            // FULL ARGUMENT Differ.generate()
            // JSON
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(plain, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "plain"));
            assertEquals(json, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "json"));
            // YAML
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
            assertEquals(plain, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "plain"));
            assertEquals(json, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "json"));
            // NO/UNKNOWN FORMAT
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "asdf"));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "asdf"));
            // EMPTY FILES
            // stylish
            assertEquals("", Differ.generate(emptyJsonFilePath, emptyJsonFilePath, "stylish"));
            assertEquals("", Differ.generate(emptyYamlFilePath, emptyYamlFilePath, "stylish"));
            // Plain
            assertEquals("", Differ.generate(emptyJsonFilePath, emptyJsonFilePath, "plain"));
            assertEquals("", Differ.generate(emptyYamlFilePath, emptyYamlFilePath, "plain"));
        } catch (Exception e) {
            // If you have a mistake in paths to valid test files Parser.parse() throw NoSuchFileException
            System.err.println("ERROR: jsonParsingTest : Check paths to correct json files in Parser.parse()");
            fail();
        }
    }

    @Test
    public void invalidFilesGenerateTest() {
        // REDUCED ARGUMENT Differ.generate()
        try {
            Differ.generate(invalidJsonFilePath, invalidJsonFilePath);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // INVALID YAML
        try {
            Differ.generate(invalidYamlFilePath, invalidYamlFilePath);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // INVALID PATH
        try {
            Differ.generate(invalidPath, invalidPath);
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getClass());
        }
        // INVALID FILE FORMAT (NOT JSON / YAML)
        try {
            Differ.generate(noJsonYamlFilePath, noJsonYamlFilePath);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        // FULL ARGUMENT Differ.generate()
        // INVALID JSON
        try {
            Differ.generate(invalidJsonFilePath, invalidJsonFilePath, "stylish");
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // INVALID YAML
        try {
            Differ.generate(invalidYamlFilePath, invalidYamlFilePath, "stylish");
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // INVALID PATH
        try {
            Differ.generate(invalidPath, invalidPath, "stylish");
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getClass());
        }
        // INVALID FILE FORMAT (NOT JSON / YAML)
        try {
            Differ.generate(noJsonYamlFilePath, noJsonYamlFilePath, "stylish");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
