import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
    public void transposeChordsInOneLine() {
        LineParser parser = new LineParser(new Transposer("G", "D"));
        String line = "GM7 C Am7 D7sus4 G";

        List<Symbol> chords = parser.splitChordsInLine(line);
        List<Symbol> transposedChords = parser.transposeChordsInLine(chords);

        List<Symbol> expectedResult = Stream.of("DM7", "G", "Em7", "A7sus4", "D")
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