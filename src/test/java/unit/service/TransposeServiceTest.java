package unit.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import model.Line;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.TransposeService;
import service.file.DefaultFileHandler;
import service.line.Parser;

@ExtendWith(MockitoExtension.class)
class TransposeServiceTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @InjectMocks
    TransposeService service;
    @Mock
    DefaultFileHandler handler;
    @Mock
    Parser parser;

    @Test
    public void transposeFileToSameKeyHasNoChange() {
        service = new TransposeService(parser, handler);

        List<Line> readFromFile = Arrays.asList(
                new Line("G Bm D C"),
                new Line("C D C G"),
                new Line("D C C Gmaj7"),
                new Line("Am C")
        );
        when(handler.readFile()).thenReturn(readFromFile);

        when(parser.parseLine(readFromFile.get(0))).thenReturn(List.of("G Bm D C"));
        when(parser.parseLine(readFromFile.get(1))).thenReturn(List.of("C D C G"));
        when(parser.parseLine(readFromFile.get(2))).thenReturn(List.of("D C C Gmaj7"));
        when(parser.parseLine(readFromFile.get(3))).thenReturn(List.of("Am C"));

        List<Line> expectedResult = Arrays.asList(
                new Line(readFromFile.get(0).toString()),
                new Line(readFromFile.get(1).toString()),
                new Line(readFromFile.get(2).toString()),
                new Line(readFromFile.get(3).toString())
        );

        List<Line> result = service.handle();
        for (int i=0; i<result.size(); i++) {
            Line line = result.get(i);
            Assertions.assertThat(line.toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }

    @Test
    public void transposeFileToOtherKeyHavingSharpNote() {
        service = new TransposeService(parser, handler);

        List<Line> readFromFile = Arrays.asList(
                new Line("G Bm D C"),
                new Line("C D C G"),
                new Line("D C C Gmaj7"),
                new Line("Am C")
        );
        when(handler.readFile()).thenReturn(readFromFile);

        when(parser.parseLine(readFromFile.get(0))).thenReturn(List.of("E G#m B A"));
        when(parser.parseLine(readFromFile.get(1))).thenReturn(List.of("A B A E"));
        when(parser.parseLine(readFromFile.get(2))).thenReturn(List.of("B A A Emaj7"));
        when(parser.parseLine(readFromFile.get(3))).thenReturn(List.of("F#m A"));

        List<Line> expectedResult = Arrays.asList(
                new Line("E G#m B A"),
                new Line("A B A E"),
                new Line("B A A Emaj7"),
                new Line("F#m A")
        );

        List<Line> result = service.handle();
        for (int i=0; i<result.size(); i++) {
            Line line = result.get(i);
            Assertions.assertThat(line.toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }
}