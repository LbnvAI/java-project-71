package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Json {
    public static String getJsonResult(Map<String, Map<String, Object>> differencesMap)
            throws IllegalArgumentException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(differencesMap);
    }
}
