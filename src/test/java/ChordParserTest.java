import static org.assertj.core.api.Assertions.*;

import model.Chord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChordParserTest {

    private ChordParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ChordParser();
    }

    @Test
    public void getRootToneOfMajorChord() {
        Chord gMajor = new Chord("G");
        String rootTone = parser.getRootTone(gMajor);
        assertThat(rootTone).isEqualTo("G");
    }

}