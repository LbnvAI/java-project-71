package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DifferTest {

    @Test
    public void validInputStylishTest() {
        try {
            var expected = readFixture("expStylish.txt");
            // 2 ARGUMENT CALL
            // json
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.json"),
                    getFixturePath("file2.json"),
                    "ERROR: validInputTest(): 2 json files, 2 arguments, stylish"));
            // yaml
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.yaml"),
                    getFixturePath("file2.yaml"),
                    "ERROR: validInputTest(): 2 yaml files, 2 arguments, stylish"));
            // 3 ARGUMENT CALL
            // json
            assertEquals(expected, Differ.generate(
                            getFixturePath("file1.json"),
                            getFixturePath("file2.json"),
                            "stylish"),
                    "ERROR: validInputTest(): 2 json files, 3 arguments, stylish");
            // yaml
            assertEquals(expected, Differ.generate(
                            getFixturePath("file1.yaml"),
                            getFixturePath("file2.yaml"),
                            "stylish"),
                    "ERROR: validInputTest(): 2 yaml files, 3 arguments, stylish");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void validInputPlainTest() {
        try {
            var expected = readFixture("expPlain.txt");
            // json
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.json"),
                    getFixturePath("file2.json"),
                    "plain"), "ERROR: validInputTest(): 2 json files, 3 arguments, plain");
            // yaml
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.yaml"),
                    getFixturePath("file2.yaml"),
                    "plain"), "ERROR: validInputTest(): 2 yaml files, 3 arguments, plain");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void validInputJsonTest() {
        try {
            // JSON FORMAT
            var expected = readFixture("expJson.txt");
            // json
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.json"),
                    getFixturePath("file2.json"),
                    "json"), "ERROR: validInputTest(): 2 json files, 3 arguments, json");
            // yaml
            assertEquals(expected, Differ.generate(
                    getFixturePath("file1.yaml"),
                    getFixturePath("file2.yaml"),
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
            Differ.generate("/invalid/path", "/invalid/path");
        } catch (Exception e) {
            assertEquals(NoSuchFileException.class, e.getClass());
        }
        // INVALID FILE
        try {
            Differ.generate(
                    getFixturePath("invalidFile.json"), getFixturePath("invalidFile.json"));
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
        // FILE EXIST BUT IT IS NO JSON OR YAML
        try {
            Differ.generate(getFixturePath("noJsonYamlFile.txt"), getFixturePath("noJsonYamlFile.txt"));
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        // VALID BUT EMPTY FILES
        try {
            assertEquals("", Differ.generate(
                    getFixturePath("empty.json"),
                    getFixturePath("empty.json")));
            assertEquals("", Differ.generate(
                    getFixturePath("empty.yaml"),
                    getFixturePath("empty.yaml")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize().toString();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = Path.of(getFixturePath(fileName));
        return Files.readString(filePath).trim();
    }
}

