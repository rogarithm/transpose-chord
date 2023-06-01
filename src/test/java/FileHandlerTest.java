import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FileHandlerTest {

    private File file;
    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @Test
    public void readFileOfGivenPath() {
        file = new File(pathName + File.separator + fileName);
        FileHandler handler = new FileHandler();
        List<String> lines = handler.readFile(file);
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }

}