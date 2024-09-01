package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DifferTest {

    // Paths to test files
    Path correctJsonFilePath1 = Path.of("src/test/resources/file1.json");
    Path correctJsonFilePath2 = Path.of("src/test/resources/file2.json");
    Path invalidJsonFilePath = Path.of("src/test/resources/invalidFile.json");
    Path emptyJsonFilePath = Path.of("src/test/resources/empty.json");
    Path correctYamlFilePath1 = Path.of("src/test/resources/file1.yaml");
    Path correctYamlFilePath2 = Path.of("src/test/resources/file2.yaml");
    Path invalidYamlFilePath = Path.of("src/test/resources/invalidFile.yaml");
    Path emptyYamlFilePath = Path.of("src/test/resources/empty.yaml");
    Path noJsonYamlFilePath = Path.of("src/test/resources/noJsonYamlFile.txt");
    Path invalidPath = Path.of("/src");

    String expStylishDiff = """
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
    String expPlainDiff = """
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

    @Test
    public void correctFilesGenerateTest() {
        try {
            // JSON
            assertEquals(expStylishDiff, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(expPlainDiff, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "plain"));
            // YAML
            assertEquals(expStylishDiff, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
            assertEquals(expPlainDiff, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "plain"));
            // NO/UNKNOWN FORMAT
            assertEquals(expStylishDiff, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "asdf"));
            assertEquals(expStylishDiff, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "asdf"));
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
