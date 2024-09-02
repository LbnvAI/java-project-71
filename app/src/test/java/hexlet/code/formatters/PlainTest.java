package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PlainTest {

    String correctJsonFilePath1 = "src/test/resources/file1.json";
    String correctJsonFilePath2 = "src/test/resources/file2.json";
    String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    String correctYamlFilePath2 = "src/test/resources/file2.yaml";

    String expResult = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    @Test
    public void plainFormatterTest() {
        try {
            // EMPTY INPUT MAP
            assertEquals("", Plain.getPlainResult(new HashMap<String, List<Object>>()));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(expResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "plain"));
            assertEquals(expResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "plain"));
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
            Plain.getPlainResult(map);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
