package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filePath) throws Exception {
        if (!Files.exists(filePath)) {
            throw new NoSuchFileException("NoSuchFileException: Check path to file or file existence.");
        }
        String fileName = filePath.toString();
        return switch (fileName.substring(fileName.lastIndexOf("."))) {
            case ".json" ->
                // Parsing JSON to Map
                    getParse(filePath, new ObjectMapper());
            case ".yaml", ".yml" ->
                // Parsing YAML to Map
                    getParse(filePath, new YAMLMapper());
            default ->
                // Do with other file formats
                    throw new IllegalArgumentException(
                            "IllegalArgumentException: Check file format. JSON or YAML expected");
        };
    }

    private static Map<String, Object> getParse(Path filePath, ObjectMapper mapper) throws IOException {
        try {
            byte[] fileBytes = Files.readAllBytes(filePath);
            return mapper.readValue(fileBytes, HashMap.class);
        } catch (Exception e) {
            throw new IOException("IOException: File reading or parsing problems"
                    + " Check file validity");
        }
    }
}
