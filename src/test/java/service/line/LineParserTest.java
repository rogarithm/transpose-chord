package service.line;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import service.line.LineParser;
import service.chord.Transposer;

class LineParserTest {

    @Test
    public void splitChordsInOneLine() {
        LineParser parser = new LineParser(null);
        String line = "GM7 C Am7 D7sus4 G";

        List<Symbol> chords = parser.splitChordsInLine(line);
        List<Symbol> expectedResult = Stream.of("GM7", "C", "Am7", "D7sus4", "G")
                                            .map(Symbol::new)
                                            .collect(Collectors.toList());

        for (int idx = 0; idx < chords.size(); idx++) {
            String actual = chords.get(idx).toString();
            String expected = expectedResult.get(idx).toString();
            Assertions.assertThat(actual).isEqualTo(expected);
            idx++;
        }
    }

    @Test
    public void transposeChordsInOneLineFromGkeyToDkey() {
        LineParser parser = new LineParser(new Transposer("G", "D"));
        String line = "GM7 C Am7 D7sus4 Bm7 G";

        List<Symbol> chords = parser.splitChordsInLine(line);
        List<Symbol> transposedChords = parser.transposeChordsInLine(chords);

        List<Symbol> expectedResult = Stream.of("DM7", "G", "Em7", "A7sus4", "F#m7", "D")
                                            .map(Symbol::new)
                                            .collect(Collectors.toList());

        for (int idx = 0; idx < transposedChords.size(); idx++) {
            String actual = transposedChords.get(idx).toString();
            String expected = expectedResult.get(idx).toString();
            Assertions.assertThat(actual).isEqualTo(expected);
            idx++;
        }
    }

    @Test
    public void transposeChordsInOneLineFromAKeyToEKey() {
        LineParser parser = new LineParser(new Transposer("A", "E"));
        String line = "AM7 D Bm7 E7sus4 C#m7 A";

        List<Symbol> chords = parser.splitChordsInLine(line);
        List<Symbol> transposedChords = parser.transposeChordsInLine(chords);

        List<Symbol> expectedResult = Stream.of("EM7", "A", "F#m7", "B7sus4", "G#m7", "E")
                                            .map(Symbol::new)
                                            .collect(Collectors.toList());

        for (int idx = 0; idx < transposedChords.size(); idx++) {
            String actual = transposedChords.get(idx).toString();
            String expected = expectedResult.get(idx).toString();
            Assertions.assertThat(actual).isEqualTo(expected);
            idx++;
        }
    }

    @Test
    public void transposeChordsInOneLineFromEkeyToCKey() {
        LineParser parser = new LineParser(new Transposer("E", "C"));
        String line = "EM7 A F#m7 B7sus4 G#m7 E";

        List<Symbol> chords = parser.splitChordsInLine(line);
        List<Symbol> transposedChords = parser.transposeChordsInLine(chords);

        List<Symbol> expectedResult = Stream.of("CM7", "F", "Dm7", "G7sus4", "Em7", "C")
                                            .map(Symbol::new)
                                            .collect(Collectors.toList());

        for (int idx = 0; idx < transposedChords.size(); idx++) {
            String actual = transposedChords.get(idx).toString();
            String expected = expectedResult.get(idx).toString();
            Assertions.assertThat(actual).isEqualTo(expected);
            idx++;
        }
    }
}