package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String getResult(Map<String, List<Object>> differencesMap, String format) throws Exception {
        return switch (format) {
            case "plain" -> Plain.getPlainResult(differencesMap);
            case "json" -> Json.getJsonResult(differencesMap);
            default -> Stylish.getStylishResult(differencesMap);
        };
    }
}
