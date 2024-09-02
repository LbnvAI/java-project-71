package hexlet.code;

import lombok.Getter;

@Getter
public class TestVariables {

    // PATHS TO TEST FILES

    private final String correctJsonFilePath1 = "src/test/resources/file1.json";
    private final String correctJsonFilePath2 = "src/test/resources/file2.json";
    private final String invalidJsonFilePath = "src/test/resources/invalidFile.json";
    private final String emptyJsonFilePath = "src/test/resources/empty.json";
    private final String correctYamlFilePath1 = "src/test/resources/file1.yaml";
    private final String correctYamlFilePath2 = "src/test/resources/file2.yaml";
    private final String invalidYamlFilePath = "src/test/resources/invalidFile.yaml";
    private final String emptyYamlFilePath = "src/test/resources/empty.yaml";
    private final String noJsonYamlFilePath = "src/test/resources/noJsonYamlFile.txt";
    private final String invalidPath = "/src";

    // EXPECTED DIFFERENCE RESULTS

    private final String expStylishResult = """
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

    private final String expPlainResult = """
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
    private final String expJsonResult = "{\"+chars2\":false,"
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

    // EXPECTED PARSING RESULTS
    private final String correctParseResult1 = "{setting2=200, key1=value1, setting3=true,"
            + " default=null, chars2=[d, e, f], setting1=Some value,"
            + " chars1=[a, b, c], numbers3=[3, 4, 5], numbers2=[2, 3, 4, 5],"
            + " numbers1=[1, 2, 3, 4], checked=false, id=45}";
    private final String correctParseResult2 = "{setting2=300, setting3=none, key2=value2,"
            + " chars2=false, setting1=Another value, chars1=[a, b, c],"
            + " numbers4=[4, 5, 6], numbers2=[22, 33, 44, 55], numbers1=[1, 2, 3, 4],"
            + " obj1={nestedKey=value, isNested=true}, default=[value1, value2],"
            + " checked=true, id=null}";
}
