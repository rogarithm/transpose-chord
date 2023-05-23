import model.note.Note;
import model.Symbol;
import model.note.NoteFactory;
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
        Note keyBefore = NoteFactory.create("G");
        Note keyAfter = NoteFactory.create("A");
        String transposed = transposer.doTranspose(gMajor, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("A");
    }

    @Test
    public void transposeMinorChord() {
        Symbol bMinor = new Symbol("Bm");
        Note keyBefore = NoteFactory.create("G");
        Note keyAfter = NoteFactory.create("A");
        String transposed = transposer.doTranspose(bMinor, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("Dbm");
    }

    @Test
    public void transpose7thChord() {
        Symbol d7 = new Symbol("D7");
        Note keyBefore = NoteFactory.create("G");
        Note keyAfter = NoteFactory.create("A");
        String transposed = transposer.doTranspose(d7, keyBefore, keyAfter);
        Assertions.assertThat(transposed).isEqualTo("E7");
    }

}