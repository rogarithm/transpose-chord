package unit.service.file;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import service.file.DefaultFileHandler;
import service.file.FileHandlerFactory;

class FileHandlerTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    DefaultFileHandler handler;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        handler = FileHandlerFactory.create(pathName, fileName);
    }

    @Test
    public void readFileOfGivenPath() {
        List<String> lines = handler.readFile();
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }

    @Test
    public void writeFileToGivenPath() {
        List<String> readFromFile = Arrays.asList("G Bm D C", "C D C G", "D C C Gmaj7", "Am C");

        String[] split = fileName.split("\\.");
        String resultFileName = split[0] + "_transposed." + split[1];
        Path path = tempDir.resolve(resultFileName);
        handler.writeFile(readFromFile, path.toString());

        File file = path.toFile();
        Assertions.assertThat(file.isFile()).isTrue();
        System.out.println(file.length());
    }
}