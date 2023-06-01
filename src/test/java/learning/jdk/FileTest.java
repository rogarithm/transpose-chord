package learning.jdk;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTest {

    private File file;
    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @BeforeEach
    public void setUp() {
        file = new File(pathName + File.separator + fileName);
    }

    @Test
    public void checkFileExists() {
        assertThat(file.exists()).isTrue();
    }

    @Test
    public void checkWhatCanBeDoneWithFile() {
        assertThat(file.canRead()).isTrue();
        assertThat(file.canWrite()).isTrue();
        assertThat(file.canExecute()).isFalse();
    }

    @Test
    public void checkFileInfo() throws IOException {
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getCanonicalFile());
        System.out.println(file.getName());
        System.out.println(file.getPath());
    }

    @Test
    public void checkFileObjectIsDirectoryOrFile() {
        assertThat(file.isFile()).isTrue();
        assertThat(file.isDirectory()).isFalse();
        assertThat(file.isHidden()).isFalse();
    }

    @Test
    public void createNewFile() throws IOException {
        File newFile = new File(pathName, "test.txt");
        if (newFile.exists())
            newFile.delete();
        assertThat(newFile.createNewFile()).isTrue();
    }
}