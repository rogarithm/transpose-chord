import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TransposerTest {

    @Test
    public void transposeMajorChord() {
        Transposer transposer = new Transposer("G", "G", "A");
        String transposed = transposer.doTranspose();
        Assertions.assertThat(transposed).isEqualTo("A");
    }

    @Test
    public void transposeMinorChord() {
        Transposer transposer = new Transposer("Bm", "G", "A");
        String transposed = transposer.doTranspose();
        Assertions.assertThat(transposed).isEqualTo("Dbm");
    }

    @Test
    public void transpose7thChord() {
        Transposer transposer = new Transposer("D7", "G", "A");
        String transposed = transposer.doTranspose();
        Assertions.assertThat(transposed).isEqualTo("E7");
    }

}