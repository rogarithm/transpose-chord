package learning.jdk;

import java.io.File;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StringTest {

    @TempDir
    Path tempDir;

    @Test
    public void removeFilePathFromFullPath() {
        String fileName = "chords.txt";
        Path fullPath = tempDir.resolve(fileName);
        Assertions.assertThat(fullPath.toString().contains(fileName)).isTrue();
        String pathName = fullPath.toString().replaceFirst(File.separator + fileName, "");
        Assertions.assertThat(pathName.contains(fileName)).isFalse();
    }
}