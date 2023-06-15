import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ChordTransposeEndToEndTest {

    @TempDir
    Path tempDir;

    @Test
    public void transposeAllChordsInTextFileWithNoLyrics() throws IOException {

        String fileName = "chords.txt";
        Path originalFilePath = tempDir.resolve(fileName);

        String content = "G Bm D C\n"
                + "C D C G\n"
                + "D C C Gmaj7\n"
                + "Am C\n";
        File file = originalFilePath.toFile();
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        }

        String[] split = fileName.split("\\.");
        String resultFileName = split[0] + "_transposed." + split[1];
        Path resultPath = tempDir.resolve(resultFileName);
        File transposedFile = resultPath.toFile();

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