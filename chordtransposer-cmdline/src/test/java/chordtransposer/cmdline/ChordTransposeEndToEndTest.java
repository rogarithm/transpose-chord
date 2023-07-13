package chordtransposer.cmdline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ChordTransposeEndToEndTest {

    @TempDir
    Path tempDir;

    Path originalFilePath;

    String fileName;

    File transposedFile;

    @BeforeEach
    public void setUpFiles() throws IOException {
        fileName = "chords.txt";
        originalFilePath = tempDir.resolve("chords.txt");
        File originalFile = originalFilePath.toFile();

        String content = "G Bm D C\n"
                + "C D C G\n"
                + "D C C Gmaj7\n"
                + "Am C\n";

        try (FileWriter fileWriter = new FileWriter(originalFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        }

        String[] split = fileName.split("\\.");
        String name = split[0];
        String extension = split[1];
        transposedFile = tempDir.resolve(name + "_transposed." + extension).toFile();
    }

    @Test
    public void transposeFileWithNoLyrics() throws IOException {

        Main.main(new String[]{
                "G",
                "E",
                originalFilePath.toString().replaceFirst(File.separator + fileName, ""),
                fileName});

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(transposedFile))) {
            String line = reader.readLine();
            while (line != null) {
                result.append(line).append("\n");
                line = reader.readLine();
            }
        }

        String expectedResult = "E G#m B A\n" + "A B A E\n" + "B A A Emaj7\n" + "F#m A\n";
        Assertions.assertThat(result.toString()).isEqualTo(expectedResult);
    }

}