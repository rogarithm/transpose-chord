import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LineParserTest {

    @Test
    public void splitChordsInOneLine() {
        String line = "GM7 C Am7 D7sus4 G";
        LineParser parser = new LineParser(null);
        List<Symbol> chords = parser.splitChordsInLine(line);
        Assertions.assertThat(chords.size()).isEqualTo(5);
    }

    @Test
    public void transposeChordsInOneLine() {
        String line = "GM7 C Am7 D7sus4 G";
        LineParser parser = new LineParser(new Transposer("G", "G", "D"));
        List<Symbol> chords = parser.splitChordsInLine(line);
        for (Symbol chord : chords) {
            LineParser eachParser = new LineParser(new Transposer(chord.getRootNote() + chord.getChordTones(), "G", "D"));
            List<Symbol> transposed = eachParser.transposeChordsInLine(chords);
            System.out.println(transposed.get(0).getRootNote() + transposed.get(0).getChordTones());
        }
    }
}