package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Stylish {
    public static String getStylishResult(Map<String, List<Object>> differencesMap) throws IllegalArgumentException {
        if (differencesMap.isEmpty()) {
            return "";
        }
        List<String> result = new ArrayList<>();
        result.add("{");
        differencesMap.forEach((k, v) -> {
            if (v.size() == 2) {
                result.add("  - " + k + ": " + v.get(1));
            } else if (v.size() == 3) {
                if (Objects.equals(v.get(1), v.get(2))) {
                    result.add("    " + k + ": " + v.get(2));
                } else {
                    if (Objects.equals(v.get(1), "stub")) {
                        result.add("  + " + k + ": " + v.get(2));
                    } else {
                        result.add("  - " + k + ": " + v.get(1));
                        result.add("  + " + k + ": " + v.get(2));
                    }
                }
            } else {
                throw new IllegalArgumentException("IllegalArgumentException: getStylishResult:"
                        + "invalid size of List<Object> size = 2 or 3 expected");
            }
        });
        result.add("}");
        return String.join("\n", result);
    }
}
