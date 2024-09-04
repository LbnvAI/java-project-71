package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {

    public static String getPlainResult(Map<String, Map<String, Object>> differencesMap)
            throws IllegalArgumentException {
        if (differencesMap.isEmpty()) {
            return "";
        }
        List<String> result = new ArrayList<>();
        differencesMap.forEach((k, v) -> {
            if (v.containsKey("old") && v.containsKey("new")) {
                if (!Objects.equals(v.get("old"), v.get("new"))) {
                    result.add("Property '" + k + "' was updated."
                            + " From " + toStrByType(v.get("old")) + " to " + toStrByType(v.get("new")));
                }
            } else if (v.containsKey("old")) {
                result.add("Property '" + k + "' was removed");
            } else if (v.containsKey("new")) {
                result.add("Property '" + k + "' was added with value: " + toStrByType(v.get("new")));
            } else {
                throw new IllegalArgumentException("IllegalArgumentException: getStylishResult:"
                        + "invalid size of List<Object> size = 2 or 3 expected");
            }
        });
        return String.join("\n", result);
    }

    private static String toStrByType(Object value) {
        if (Objects.isNull(value)) {
            return "null";
        } else if (Objects.equals(String.class, value.getClass())) {
            return "'" + value + "'";
        } else if (value.toString().startsWith("[") || value.toString().startsWith("{")) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
