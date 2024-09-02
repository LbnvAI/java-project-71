package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.TestVariables;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StylishTest {

    private final TestVariables vrb = new TestVariables();
    private final String stylish = vrb.getExpStylishResult();
    private final String correctJsonFilePath1 = vrb.getCorrectJsonFilePath1();
    private final String correctJsonFilePath2 = vrb.getCorrectJsonFilePath2();
    private final String correctYamlFilePath1 = vrb.getCorrectYamlFilePath1();
    private final String correctYamlFilePath2 = vrb.getCorrectYamlFilePath2();

    @Test
    public void stylishFormatterTest() {
        // REDUCED ARGUMENT Differ.generate()
        try {
            // CORRECT MAP
            // !!! IF DIFFER TESTS FAIL MAP WILL BE INVALID !!!
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2));
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
            assertEquals(stylish, Differ.generate(correctJsonFilePath1, correctJsonFilePath2, "stylish"));
            assertEquals(stylish, Differ.generate(correctYamlFilePath1, correctYamlFilePath2, "stylish"));
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
