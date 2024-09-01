package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

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

    // Expected parsing results
    String correctParseResult1 = "{setting2=200, key1=value1, setting3=true,"
            + " default=null, chars2=[d, e, f], setting1=Some value,"
            + " chars1=[a, b, c], numbers3=[3, 4, 5], numbers2=[2, 3, 4, 5],"
            + " numbers1=[1, 2, 3, 4], checked=false, id=45}";
    String correctParseResult2 = "{setting2=300, setting3=none, key2=value2,"
            + " chars2=false, setting1=Another value, chars1=[a, b, c],"
            + " numbers4=[4, 5, 6], numbers2=[22, 33, 44, 55], numbers1=[1, 2, 3, 4],"
            + " obj1={nestedKey=value, isNested=true}, default=[value1, value2],"
            + " checked=true, id=null}";

    @Test
    public void invalidInputTest() {
        // Testing invalid JSON file
        try {
            Parser.parse(invalidJsonFilePath);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass(),
                    "ERROR: jsonParsingTest : Parser.parse() must throw"
                            + " IOException in case of invalid JSON file");
        }
        // Testing invalid YAML file
        try {
            Parser.parse(invalidYamlFilePath);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass(),
                    "ERROR: yamlParsingTest : Parser.parse() must throw"
                            + " IOException in case of invalid YAML file");
        }
        //  Testing invalid path as argument
        try {
            Parser.parse(invalidPath);
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getClass(),
                    "ERROR: invalidInputTest : Parser.parse() must throw"
                            + " NoSuchFileException in case of invalid path");
        }
        // Testing valid path but invalid file format
        try {
            Parser.parse(noJsonYamlFilePath);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass(),
                    "ERROR: invalidInputTest : Parser.parse() must throw"
                            + " IllegalArgumentException in case of invalid file format");
        }
    }

    @Test
    public void jsonParsingTest() {
        try {
            // Testing valid JSON files
            assertEquals(new HashMap<String, Object>().toString(), Parser.parse(emptyJsonFilePath).toString(),
                    "ERROR: jsonParsingTest : wrong empty.json parsing");
            assertEquals(correctParseResult1, Parser.parse(correctJsonFilePath1).toString(),
                    "ERROR: jsonParsingTest : wrong file1.json parsing");
            assertEquals(correctParseResult2, Parser.parse(correctJsonFilePath2).toString(),
                    "ERROR: jsonParsingTest : wrong file2.json parsing");
            // Testing valid YAML files
            assertEquals(new HashMap<String, Object>().toString(), Parser.parse(emptyYamlFilePath).toString(),
                    "ERROR: yamlParsingTest : wrong empty.yaml parsing");
            assertEquals(correctParseResult1, Parser.parse(correctYamlFilePath1).toString(),
                    "ERROR: yamlParsingTest : wrong file1.yaml parsing");
            assertEquals(correctParseResult2, Parser.parse(correctYamlFilePath2).toString(),
                    "ERROR: yamlParsingTest : wrong file2.yaml parsing");
        } catch (Exception e) {
            // If you have a mistake in paths to valid test files Parser.parse() throw NoSuchFileException
            System.err.println("ERROR: jsonParsingTest : Check paths to correct json files in Parser.parse()");
            fail();
        }
    }
}
