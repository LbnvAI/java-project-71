package hexlet.code;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DiffCreator {
    public static Map<String, Map<String, Object>> getInternalRepresent(
            Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        Map<String, Map<String, Object>> diffMap = new TreeMap<>();
        Set<String> keySet = new HashSet<>();
        // Create Key set contains all keys from bought Maps
        fileMap1.forEach((k, v) -> keySet.add(k));
        fileMap2.forEach((k, v) -> keySet.add(k));
        // Create difference Map
        keySet.forEach(key -> {
            Map<String, Object> map = new HashMap<>();
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                map.put("old", fileMap1.get(key));
                map.put("new", fileMap2.get(key));
                diffMap.put(key, map);
            } else if (fileMap1.containsKey(key)) {
                map.put("old", fileMap1.get(key));
                diffMap.put(key, map);
            } else {
                map.put("new", fileMap2.get(key));
                diffMap.put(key, map);
            }
        });
        return diffMap;
    }
}
