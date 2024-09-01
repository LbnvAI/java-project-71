package hexlet.code;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;

public class Differ {
    public static String generate(Path filePath1, Path filePath2, String format) throws Exception {
        return Formatter.getResult(getDiffMap(Parser.parse(filePath1), Parser.parse(filePath2)), format);
    }

    private static Map<String, List<Object>> getDiffMap(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        Map<String, List<Object>> diffMap = new TreeMap<>();
        Set<String> keySet = new HashSet<>();
        // Create Key set contains all keys from bought Maps
        fileMap1.forEach((k, v) -> keySet.add(k));
        fileMap2.forEach((k, v) -> keySet.add(k));
        // Create difference Map
        keySet.forEach(key -> {
            List<Object> differences = new ArrayList<>(3);
            differences.add("stub");
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                differences.add(fileMap1.get(key));
                differences.add(fileMap2.get(key));
            } else if (fileMap1.containsKey(key)) {
                differences.add(fileMap1.get(key));
            } else {
                differences.add("stub");
                differences.add(fileMap2.get(key));
            }
            diffMap.put(key, differences);
        });
        return diffMap;
    }
}
