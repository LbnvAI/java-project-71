package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;
import hexlet.code.TestVariables;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FormatterTest {

    private final TestVariables vrb = new TestVariables();
    private final String stylish = vrb.getExpStylishResult();
    private final String plain = vrb.getExpPlainResult();
    private final String json = vrb.getExpJsonResult();
    private final String correctJsonFilePath1 = vrb.getCorrectJsonFilePath1();
    private final String correctJsonFilePath2 = vrb.getCorrectJsonFilePath2();
    private final String correctYamlFilePath1 = vrb.getCorrectYamlFilePath1();
    private final String correctYamlFilePath2 = vrb.getCorrectYamlFilePath2();

    @Test
    public void formatTest() {
        // REDUCED ARGUMENT Differ.generate()
        try {
            // EMPTY INPUT MAP
            assertEquals("", Formatter.getResult(new HashMap<String, List<Object>>(), "stylish"));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
        } catch (Exception e) {
            // If you have a mistake in paths to valid test files Parser.parse() throw NoSuchFileException
            System.err.println("ERROR: jsonParsingTest : Check paths to correct json files in Parser.parse()");
            fail();
        }
        // FULL ARGUMENT Differ.generate()
        try {
            // EMPTY INPUT MAP
            assertEquals("", Formatter.getResult(new HashMap<String, List<Object>>(), "stylish"));
            assertEquals("", Formatter.getResult(new HashMap<String, List<Object>>(), "plain"));
            assertEquals("{}", Formatter.getResult(new HashMap<String, List<Object>>(), "json"));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
            assertEquals(plain, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "plain"));
            assertEquals(plain, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "plain"));
            assertEquals(json, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "json"));
            assertEquals(json, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "json"));
        } catch (Exception e) {
            // If you have a mistake in paths to valid test files Parser.parse() throw NoSuchFileException
            System.err.println("ERROR: jsonParsingTest : Check paths to correct json files in Parser.parse()");
            fail();
        }
    }
}
