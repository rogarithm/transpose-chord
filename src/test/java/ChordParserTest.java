import static org.assertj.core.api.Assertions.*;

import model.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChordParserTest {

    private ChordParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ChordParser();
    }

    @Test
    public void getRootOfMajorChord() {
        Symbol gMajor = new Symbol("G");
        String rootTone = parser.getRootNote(gMajor);
        assertThat(rootTone).isEqualTo("G");
    }

    @Test
    public void getRootOfFlatMajorChord() {
        Symbol gMajor = new Symbol("Gb");
        String rootTone = parser.getRootNote(gMajor);
        assertThat(rootTone).isEqualTo("Gb");
    }
        assertThat(rootTone).isEqualTo("A");
    }
}