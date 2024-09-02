package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonTest {

    String correctJsonFilePath1 = "src/test/resources/file1.json";
    String correctJsonFilePath2 = "src/test/resources/file2.json";
    String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    String correctYamlFilePath2 = "src/test/resources/file2.yaml";

    String expResult = "{\"+chars2\":false,"
            + "\"+checked\":true,"
            + "\"+default\":[\"value1\",\"value2\"],"
            + "\"+id\":null,"
            + "\"+key2\":\"value2\","
            + "\"+numbers2\":[22,33,44,55],"
            + "\"+numbers4\":[4,5,6],"
            + "\"+obj1\":{\"nestedKey\":\"value\",\"isNested\":true},"
            + "\"+setting1\":\"Another value\","
            + "\"+setting2\":300,"
            + "\"+setting3\":\"none\","
            + "\"-chars2\":[\"d\",\"e\",\"f\"],"
            + "\"-checked\":false,"
            + "\"-default\":null,"
            + "\"-id\":45,"
            + "\"-key1\":\"value1\","
            + "\"-numbers2\":[2,3,4,5],"
            + "\"-numbers3\":[3,4,5],"
            + "\"-setting1\":\"Some value\","
            + "\"-setting2\":200,"
            + "\"-setting3\":true}";

    @Test
    public void jsonFormatterTest() {
        // FULL ARGUMENT Differ.generate()
        try {
            // EMPTY INPUT MAP
            assertEquals("", Plain.getPlainResult(new HashMap<String, List<Object>>()));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(expResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "json"));
            assertEquals(expResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "json"));
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
        try {
            // INVALID MAP (WRONG SIZE OF List<Object>)
            var map = new HashMap<String, List<Object>>();
            var obj = new Object();
            var list = new ArrayList<Object>();
            list.add(obj);
            map.put("1", list);
            Json.getJsonResult(map);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
