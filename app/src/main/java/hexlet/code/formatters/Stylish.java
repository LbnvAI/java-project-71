package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Stylish {

    public static String getStylishResult(Map<String, Map<String, Object>> differencesMap)
            throws IllegalArgumentException {
        if (differencesMap.isEmpty()) {
            return "";
        }
        List<String> result = new ArrayList<>();
        result.add("{");
        differencesMap.forEach((k, v) -> {
            if (v.containsKey("old") && v.containsKey("new")) {
                if (Objects.equals(v.get("old"), v.get("new"))) {
                    result.add("    " + k + ": " + v.get("old"));
                } else {
                    result.add("  - " + k + ": " + v.get("old"));
                    result.add("  + " + k + ": " + v.get("new"));
                }
            } else if (v.containsKey("old")) {
                result.add("  - " + k + ": " + v.get("old"));
            } else if (v.containsKey("new")) {
                result.add("  + " + k + ": " + v.get("new"));
            } else {
                throw new IllegalArgumentException("IllegalArgumentException: getStylishResult:"
                        + "invalid size of List<Object> size = 2 or 3 expected");
            }
        });
        result.add("}");
        return String.join("\n", result);
    }
}
