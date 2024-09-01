package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    String format = "stylish";
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filePath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filePath2;


    @Override
    public Integer call() throws Exception {
        Path path1 = Path.of(filePath1).toAbsolutePath();
        Path path2 = Path.of(filePath2).toAbsolutePath();
        System.out.println(Differ.generate(path1, path2, format));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
