package service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import model.Line;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void transposeFileToOtherKeyHavingSharpNote() {
        List<Line> expectedResult = Arrays.asList(
                new Line("E G#m B A"),
                new Line("A B A E"),
                new Line("B A A Emaj7"),
                new Line("F#m A")
        );

        List<Line> result = service.handle();
        Iterator<Line> iterator1 = result.iterator();
        Iterator<Line> iterator2 = expectedResult.iterator();
        for (; iterator1.hasNext(); ) {
            Line line = iterator1.next();
            Line expected = iterator2.next();
            Assertions.assertThat(line).isEqualTo(expected);
        }
    }
}