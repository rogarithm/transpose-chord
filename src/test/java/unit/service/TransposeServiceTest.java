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

    @BeforeEach
    public void setUp() {
        service = new TransposeService(handler, parser);
    }

    @Test
    public void parseEveryLineInFile() {
        List<String> readFromFile = Arrays.asList("G Bm D C", "C D C G", "D C C Gmaj7", "Bbdim Am C");
        when(handler.readFile(pathName, fileName)).thenReturn(readFromFile);

        List<String> parsedLine1 = Arrays.asList("G", "Bm", "D", "C");
        List<String> parsedLine2 = Arrays.asList("C", "D", "C", "G");
        List<String> parsedLine3 = Arrays.asList("D", "C", "C", "Gmaj7");
        List<String> parsedLine4 = Arrays.asList("Bbdim", "Am", "C");

        when(parser.parseLine(readFromFile.get(0))).thenReturn(parsedLine1);
        when(parser.parseLine(readFromFile.get(1))).thenReturn(parsedLine2);
        when(parser.parseLine(readFromFile.get(2))).thenReturn(parsedLine3);
        when(parser.parseLine(readFromFile.get(3))).thenReturn(parsedLine4);

        List<String> expectedResult = Arrays.asList("G Bm D C", "C D C G", "D C C Gmaj7", "Bbdim Am C");
        Assertions.assertThat(service.handle(pathName, fileName, "G", "G")).isEqualTo(expectedResult);
    }
}