package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain {
    public static String getPlainResult(Map<String, List<Object>> differencesMap) throws IllegalArgumentException {
        if (differencesMap.isEmpty()) {
            return "";
        }
        List<String> result = new ArrayList<>();
        differencesMap.forEach((k, v) -> {
            if (v.size() != 2 && v.size() != 3) {
                throw new IllegalArgumentException("Invalid size of List<Object>."
                        + " Expected 2 or 3, but was " + v.size());
            }
            if (v.size() == 2) {
                result.add("Property '" + k + "' was removed");
            } else {
                if (Objects.equals(v.get(1), "stub")) {
                    result.add("Property '" + k + "' was added with value: " + toStrByType(v.get(2)));
                } else {
                    if (!Objects.equals(v.get(1), v.get(2))) {
                        result.add("Property '" + k + "' was updated."
                                + " From " + toStrByType(v.get(1)) + " to " + toStrByType(v.get(2)));
                    }
                }
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
