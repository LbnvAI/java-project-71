package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FormatterTest {
    String expStylishResult = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    String expPlainResult = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
    String expJsonResult = "{\"+chars2\":false,"
            + "\"+checked\":true,"
            + "\"+default\":[\"value1\",\"value2\"],"
            + "\"+id\":null,"
            + "\"+key2\":\"value2\","
            + "\"+numbers2\":[22,33,44,55],"
            + "\"+numbers4\":[4,5,6],"
            + "\"+obj1\":{\"nestedKey\":\"value\",\"isNested\":true},"
            + "\"+setting1\":\"Another value\","
            + "\"+setting2\":300,"
            + "\"+setting3\":\"none\","
            + "\"-chars2\":[\"d\",\"e\",\"f\"],"
            + "\"-checked\":false,"
            + "\"-default\":null,"
            + "\"-id\":45,"
            + "\"-key1\":\"value1\","
            + "\"-numbers2\":[2,3,4,5],"
            + "\"-numbers3\":[3,4,5],"
            + "\"-setting1\":\"Some value\","
            + "\"-setting2\":200,"
            + "\"-setting3\":true}";

    String correctJsonFilePath1 = "src/test/resources/file1.json";
    String correctJsonFilePath2 = "src/test/resources/file2.json";
    String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    String correctYamlFilePath2 = "src/test/resources/file2.yaml";

    @Test
    public void formatTest() {
        // REDUCED ARGUMENT Differ.generate()
        try {
            // EMPTY INPUT MAP
            assertEquals("", Formatter.getResult(new HashMap<String, List<Object>>(), "stylish"));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(expStylishResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            assertEquals(expStylishResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
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
            assertEquals(expStylishResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(expStylishResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
            assertEquals(expPlainResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "plain"));
            assertEquals(expPlainResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "plain"));
            assertEquals(expJsonResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "json"));
            assertEquals(expJsonResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "json"));
        } catch (Exception e) {
            // If you have a mistake in paths to valid test files Parser.parse() throw NoSuchFileException
            System.err.println("ERROR: jsonParsingTest : Check paths to correct json files in Parser.parse()");
            fail();
        }
    }
}
