package chordtransposer.cmdline.service.line;

import static org.assertj.core.api.Assertions.assertThat;

import chordtransposer.chord.Transposer;
import chordtransposer.model.Chord;
import chordtransposer.model.Line;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class LineParserIntegrationTest {

    @Test
    public void transposeLineToSameKeyHasNoChange() {
        LineParser parser = new LineParser(new Transposer("G", "G"));
        Line line = new Line("GM7 C Am7 D7sus4 G");

        List<Chord> parsedLine = parser.parseLine(line);
        List<Chord> expectedResult = Stream.of(line.toString().split(" "))
                                           .map(Chord::new)
                                           .collect(Collectors.toList());

        for (int i=0; i<parsedLine.size(); i++) {
            assertThat(parsedLine.get(i).toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }

    @Test
    public void transposeLineToKeyHavingSharpNote() {
        LineParser parser = new LineParser(new Transposer("G", "D"));
        Line line = new Line("GM7 C Am7 D7sus4 Bm7 G");

        List<Chord> parsedLine = parser.parseLine(line);
        List<Chord> expectedResult = Stream.of("DM7", "G", "Em7", "A7sus4", "F#m7", "D")
                                            .map(Chord::new)
                                            .collect(Collectors.toList());

        for (int i=0; i<parsedLine.size(); i++) {
            assertThat(parsedLine.get(i).toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }

    @Test
    public void transposeLineToOtherKeyHavingSharpNote() {
        LineParser parser = new LineParser(new Transposer("A", "E"));
        Line line = new Line("AM7 D Bm7 E7sus4 C#m7 A");

        List<Chord> parsedLine = parser.parseLine(line);
        List<Chord> expectedResult = Stream.of("EM7", "A", "F#m7", "B7sus4", "G#m7", "E")
                                           .map(Chord::new)
                                           .collect(Collectors.toList());

        for (int i=0; i<parsedLine.size(); i++) {
            assertThat(parsedLine.get(i).toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }

    @Test
    public void transposeLineToKeyHavingOnlyPlainNote() {
        LineParser parser = new LineParser(new Transposer("E", "C"));
        Line line = new Line("EM7 A F#m7 B7sus4 G#m7 E");

        List<Chord> parsedLine = parser.parseLine(line);
        List<Chord> expectedResult = Stream.of("CM7", "F", "Dm7", "G7sus4", "Em7", "C")
                                           .map(Chord::new)
                                           .collect(Collectors.toList());

        for (int i=0; i<parsedLine.size(); i++) {
            assertThat(parsedLine.get(i).toString()).isEqualTo(expectedResult.get(i).toString());
        }
    }
}