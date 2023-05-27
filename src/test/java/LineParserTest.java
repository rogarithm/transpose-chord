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
}