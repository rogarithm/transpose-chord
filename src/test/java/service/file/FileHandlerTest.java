package service.file;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.line.Parser;

@ExtendWith(MockitoExtension.class)
class FileHandlerTest {

    private File file;
    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @InjectMocks
    FileHandler handler;
    @Mock
    Parser parser;

    @BeforeEach
    public void setUp() {
        file = new File(pathName + File.separator + fileName);
        handler = new FileHandler(parser);
    }

    @Test
    public void readFileOfGivenPath() {
        List<String> lines = handler.readFile(file);
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }

    @Test
    public void parseEveryLineInFile() {
        List<String> lines = handler.readFile(file);
        String line1 = "G Bm D C";
        String line2 = "C D C G";
        String line3 = "D C C Gmaj7";
        String line4 = "Bbdim Am C";
        List<String> parsedLine1 = Stream.of("G", "Bm", "D", "C")
                                         .collect(Collectors.toList());
        List<String> parsedLine2 = Stream.of("C", "D", "C", "G")
                                         .collect(Collectors.toList());
        List<String> parsedLine3 = Stream.of("D", "C", "C", "Gmaj7")
                                         .collect(Collectors.toList());
        List<String> parsedLine4 = Stream.of("Bbdim", "Am", "C")
                                         .collect(Collectors.toList());
        List<String> expectedResult = Stream.of("G Bm D C", "C D C G", "D C C Gmaj7", "Bbdim Am C")
                                            .collect(Collectors.toList());

        when(parser.parseLine(line1)).thenReturn(parsedLine1);
        when(parser.parseLine(line2)).thenReturn(parsedLine2);
        when(parser.parseLine(line3)).thenReturn(parsedLine3);
        when(parser.parseLine(line4)).thenReturn(parsedLine4);
        Assertions.assertThat(handler.handle(lines)).isEqualTo(expectedResult);
    }
}