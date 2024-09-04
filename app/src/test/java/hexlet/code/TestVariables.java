package hexlet.code;

import lombok.Getter;

@Getter
public class TestVariables {

    // PATHS TO TEST FILES

    private final String correctJsonFilePath1 = "src/test/resources/file1.json";
    private final String correctJsonFilePath2 = "src/test/resources/file2.json";
    private final String invalidJsonFilePath = "src/test/resources/invalidFile.json";
    private final String emptyJsonFilePath = "src/test/resources/empty.json";
    private final String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    private final String correctYamlFilePath2 = "src/test/resources/file2.yaml";
    private final String invalidYamlFilePath = "src/test/resources/invalidFile.yaml";
    private final String emptyYamlFilePath = "src/test/resources/empty.yaml";
    private final String noJsonYamlFilePath = "src/test/resources/noJsonYamlFile.txt";
    private final String invalidPath = "/src";

    // PATH TO FILES WITH EXPECTED VALUES

    private final String expStylishFilePath = "src/test/resources/expected_result/expStylish.txt";
    private final String expPlainFilePath = "src/test/resources/expected_result/expPlain.txt";
    private final String expJsonFilePath = "src/test/resources/expected_result/expJson.txt";
}
