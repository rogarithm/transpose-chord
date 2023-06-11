package unit.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.TransposeService;
import service.chord.Transposer;
import service.line.LineParser;
import service.util.FileHandler;
import service.line.Parser;

@ExtendWith(MockitoExtension.class)
class TransposeServiceTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @InjectMocks
    TransposeService service;
    @Mock
    FileHandler handler;
    @Mock
    Parser parser;

    @Test
    public void parseEveryLineInFile() {
        service = new TransposeService(
                new LineParser(new Transposer("G", "G")),
                new FileHandler(pathName, fileName)
        );
        List<String> expectedResult = Arrays.asList("G Bm D C", "C D C G", "D C C Gmaj7", "Am C");
        Assertions.assertThat(service.handle()).isEqualTo(expectedResult);
    }

    @Test
    public void parseEveryLineInFileToDifferentKey() {
        service = new TransposeService(
                new LineParser(new Transposer("G", "E")),
                new FileHandler(pathName, fileName)
        );
        List<String> expectedResult = Arrays.asList("E G#m B A", "A B A E", "B A A Emaj7", "F#m A");
        Assertions.assertThat(service.handle()).isEqualTo(expectedResult);
    }
}