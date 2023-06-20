package integration.service.line;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import service.chord.Transposer;
import service.line.LineParser;

public class LineParserIntegrationTest {

    @Test
    public void transposeLineToSameKeyHasNoChange() {
        LineParser parser = new LineParser(new Transposer("G", "G"));
        String line = "GM7 C Am7 D7sus4 G";

        List<String> parsedLine = parser.parseLine(line);
        List<String> expectedResult = Stream.of(line.split(" "))
                                            .collect(Collectors.toList());

        Assertions.assertThat(parsedLine).isEqualTo(expectedResult);
    }

    @Test
    public void transposeLineToKeyHavingSharpNote() {
        LineParser parser = new LineParser(new Transposer("G", "D"));
        String line = "GM7 C Am7 D7sus4 Bm7 G";

        List<String> parsedLine = parser.parseLine(line);
        List<String> expectedResult = Stream.of("DM7", "G", "Em7", "A7sus4", "F#m7", "D")
                                            .collect(Collectors.toList());

        Assertions.assertThat(parsedLine).isEqualTo(expectedResult);
    }

    @Test
    public void transposeLineToOtherKeyHavingSharpNote() {
        LineParser parser = new LineParser(new Transposer("A", "E"));
        String line = "AM7 D Bm7 E7sus4 C#m7 A";

        List<String> parsedLine = parser.parseLine(line);
        List<String> expectedResult = Stream.of("EM7", "A", "F#m7", "B7sus4", "G#m7", "E")
                                            .collect(Collectors.toList());

        Assertions.assertThat(parsedLine).isEqualTo(expectedResult);
    }

    @Test
    public void transposeLineToKeyHavingOnlyPlainNote() {
        LineParser parser = new LineParser(new Transposer("E", "C"));
        String line = "EM7 A F#m7 B7sus4 G#m7 E";

        List<String> parsedLine = parser.parseLine(line);
        List<String> expectedResult = Stream.of("CM7", "F", "Dm7", "G7sus4", "Em7", "C")
                                            .collect(Collectors.toList());

        Assertions.assertThat(parsedLine).isEqualTo(expectedResult);
    }
}