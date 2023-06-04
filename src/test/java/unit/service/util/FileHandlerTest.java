package service.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileHandlerTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    FileHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new FileHandler();
    }

    @Test
    public void readFileOfGivenPath() {
        List<String> lines = handler.readFile(pathName, fileName);
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }
}