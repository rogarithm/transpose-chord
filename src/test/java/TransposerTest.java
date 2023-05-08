import static org.junit.jupiter.api.Assertions.*;

import model.Note;
import model.PlainNote;
import model.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransposerTest {

    private Transposer transposer;

    @BeforeEach
    public void setUp() {
        transposer = new Transposer();
    }

    @Test
    public void transposeMajorChord() {
        Symbol gMajor = new Symbol("G");
        Note keyBefore = PlainNote.G;
        Note keyAfter = PlainNote.A;
        String transposed = transposer.doTranspose(gMajor, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("A");
    }

    @Test
    public void transposeMinorChord() {
        Symbol gMajor = new Symbol("Bm");
        Note keyBefore = PlainNote.G;
        Note keyAfter = PlainNote.A;
        String transposed = transposer.doTranspose(gMajor, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("Dbm");
    }

    @Test
    public void transpose7thChord() {
        Symbol gMajor = new Symbol("D7");
        Note keyBefore = PlainNote.G;
        Note keyAfter = PlainNote.A;
        String transposed = transposer.doTranspose(gMajor, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("E7");
    }

}