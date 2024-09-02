package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StylishTest {

    String correctJsonFilePath1 = "src/test/resources/file1.json";
    String correctJsonFilePath2 = "src/test/resources/file2.json";
    String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    String correctYamlFilePath2 = "src/test/resources/file2.yaml";

    String expResult = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    @Test
    public void stylishFormatterTest() {
        // REDUCED ARGUMENT Differ.generate()
        try {
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(expResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            assertEquals(expResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
        // FULL ARGUMENT Differ.generate()
        try {
            // EMPTY INPUT MAP
            assertEquals("", Stylish.getStylishResult(new HashMap<String, List<Object>>()));
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(expResult, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(expResult, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
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
            Stylish.getStylishResult(map);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
