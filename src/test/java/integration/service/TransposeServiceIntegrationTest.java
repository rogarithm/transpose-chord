package integration.service;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TransposeService;
import service.chord.Transposer;
import service.file.FileHandlerFactory;
import service.line.LineParser;

public class TransposeServiceIntegrationTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    TransposeService service;

    @BeforeEach
    public void setUp() {
        service = new TransposeService(
                new LineParser(new Transposer("G", "E")),
                FileHandlerFactory.create(pathName, fileName)
        );
    }

    @Test
    public void parseEveryLineInFileToDifferentKey() {
        List<String> readFromFile = Arrays.asList("G Bm D C", "C D C G", "D C C Gmaj7", "Am C");
        List<String> expectedResult = Arrays.asList("E G#m B A", "A B A E", "B A A Emaj7", "F#m A");
        Assertions.assertThat(service.handle()).isEqualTo(expectedResult);
    }
}