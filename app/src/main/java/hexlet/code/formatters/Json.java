package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Json {
    public static String getJsonResult(Map<String, List<Object>> differencesMap)
            throws IllegalArgumentException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getFormatedMap(differencesMap));
    }

    public static Map<String, Object> getFormatedMap(Map<String, List<Object>> oldMap) throws IllegalArgumentException {
        Map<String, Object> formatedMap = new TreeMap<>();
        oldMap.forEach((k, v) -> {
            if (v.size() != 2 && v.size() != 3) {
                throw new IllegalArgumentException("Invalid size of List<Object>."
                        + " Expected 2 or 3, but was " + v.size());
            }
            if (v.size() == 2) {
                formatedMap.put("-" + k, v.get(1));
            } else {
                if (Objects.equals(v.get(1), "stub")) {
                    formatedMap.put("+" + k, v.get(2));
                } else {
                    if (!Objects.equals(v.get(1), v.get(2))) {
                        formatedMap.put("-" + k, v.get(1));
                        formatedMap.put("+" + k, v.get(2));
                    }
                }
            }
        });
        return formatedMap;
    }
}
