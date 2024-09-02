package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private final TestVariables vrb = new TestVariables();
    private final Path correctJsonFilePath1 = Path.of(vrb.getCorrectJsonFilePath1());
    private final Path correctJsonFilePath2 = Path.of(vrb.getCorrectJsonFilePath2());
    private final Path correctYamlFilePath1 = Path.of(vrb.getCorrectYamlFilePath1());
    private final Path correctYamlFilePath2 = Path.of(vrb.getCorrectYamlFilePath2());
    private final Path emptyJsonFilePath = Path.of(vrb.getEmptyJsonFilePath());
    private final Path emptyYamlFilePath = Path.of(vrb.getEmptyYamlFilePath());
    private final Path invalidJsonFilePath = Path.of(vrb.getInvalidJsonFilePath());
    private final Path invalidYamlFilePath = Path.of(vrb.getInvalidYamlFilePath());
    private final Path invalidPath = Path.of(vrb.getInvalidPath());
    private final Path noJsonYamlFilePath = Path.of(vrb.getNoJsonYamlFilePath());
    private final String correctParseResult1 = vrb.getCorrectParseResult1();
    private final String correctParseResult2 = vrb.getCorrectParseResult2();

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
