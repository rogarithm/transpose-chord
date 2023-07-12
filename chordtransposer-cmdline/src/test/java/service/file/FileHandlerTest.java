package service.file;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import model.Line;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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
        List<Line> lines = handler.readFile();
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }

    @Test
    public void writeFileToGivenPath() {
        List<Line> readFromFile = Arrays.asList(
                new Line("G Bm D C"),
                new Line("C D C G"),
                new Line("D C C Gmaj7"),
                new Line("Am C")
        );

        String[] split = fileName.split("\\.");
        String resultFileName = split[0] + "_transposed." + split[1];
        Path path = tempDir.resolve(resultFileName);
        handler.writeFile(readFromFile, path.toString());

        File file = path.toFile();
        Assertions.assertThat(file.isFile()).isTrue();
        System.out.println(file.length());
    }
}