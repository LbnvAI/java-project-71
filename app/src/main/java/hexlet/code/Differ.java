package hexlet.code;
import java.nio.file.Path;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Path path1 = Path.of(filePath1).toAbsolutePath();
        Path path2 = Path.of(filePath2).toAbsolutePath();
        return Formatter.getResult(DiffCreator.getInternalRepresent(
                Parser.parse(path1), Parser.parse(path2)), format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Path.of(filePath1).toAbsolutePath();
        Path path2 = Path.of(filePath2).toAbsolutePath();
        return Formatter.getResult(DiffCreator.getInternalRepresent(
                Parser.parse(path1), Parser.parse(path2)), "stylish");
    }
}
