package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DifferTest {

    TestVariables vrb = new TestVariables();

    @Test
    public void validInputTest() throws Exception {
        try {
            // STYLISH FORMAT
            var expected = Files.readString(Path.of(vrb.getExpStylishFilePath()));
            // 2 ARGUMENT CALL
            // json
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectJsonFilePath1(),
                    vrb.getCorrectJsonFilePath2()), "ERROR: validInputTest(): 2 json files, 2 arguments, stylish");
            // yaml
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectYamlFilePath1(),
                    vrb.getCorrectYamlFilePath2()), "ERROR: validInputTest(): 2 yaml files, 2 arguments, stylish");
            // 3 ARGUMENT CALL
            // json
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectJsonFilePath1(),
                    vrb.getCorrectJsonFilePath2(),
                    "stylish"), "ERROR: validInputTest(): 2 json files, 3 arguments, stylish");
            // yaml
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectYamlFilePath1(),
                    vrb.getCorrectYamlFilePath2(),
                    "stylish"), "ERROR: validInputTest(): 2 yaml files, 3 arguments, stylish");
            // PLAIN FORMAT
            expected = Files.readString(Path.of(vrb.getExpPlainFilePath()));
            // json
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectJsonFilePath1(),
                    vrb.getCorrectJsonFilePath2(),
                    "plain"), "ERROR: validInputTest(): 2 json files, 3 arguments, plain");
            // yaml
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectYamlFilePath1(),
                    vrb.getCorrectYamlFilePath2(),
                    "plain"), "ERROR: validInputTest(): 2 yaml files, 3 arguments, plain");
            // JSON FORMAT
            expected = Files.readString(Path.of(vrb.getExpJsonFilePath()));
            // json
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectJsonFilePath1(),
                    vrb.getCorrectJsonFilePath2(),
                    "json"), "ERROR: validInputTest(): 2 json files, 3 arguments, json");
            // yaml
            assertEquals(expected, Differ.generate(
                    vrb.getCorrectYamlFilePath1(),
                    vrb.getCorrectYamlFilePath2(),
                    "json"), "ERROR: validInputTest(): 2 yaml files, 3 arguments, json");
            // JSON FORMAT
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void invalidInput() {
        // INVALID FILE PATH, FILE NOT EXIST
        try {
            Differ.generate(vrb.getInvalidPath(), vrb.getInvalidPath());
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getClass());
        }
        // INVALID FILE
        try {
            Differ.generate(vrb.getInvalidYamlFilePath(), vrb.getInvalidJsonFilePath());
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // FILE EXIST BUT IT IS NO JSON OR YAML
        try {
            Differ.generate(vrb.getNoJsonYamlFilePath(), vrb.getNoJsonYamlFilePath());
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        // VALID BUT EMPTY FILES
        try {
            assertEquals("", Differ.generate(vrb.getEmptyJsonFilePath(), vrb.getEmptyJsonFilePath()));
            assertEquals("", Differ.generate(vrb.getEmptyYamlFilePath(), vrb.getEmptyYamlFilePath()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
