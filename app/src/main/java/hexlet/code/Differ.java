package hexlet.code;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Path path1 = Path.of(filePath1).toAbsolutePath();
        Path path2 = Path.of(filePath2).toAbsolutePath();
        return Formatter.getResult(getDiffMap(Parser.parse(path1), Parser.parse(path2)), format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Path.of(filePath1).toAbsolutePath();
        Path path2 = Path.of(filePath2).toAbsolutePath();
        return Formatter.getResult(getDiffMap(Parser.parse(path1), Parser.parse(path2)), "stylish");
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
