package unit.service.chord;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import service.chord.Transposer;

class TransposerTest {

    @Test
    public void transposeMajorChord() {
        Transposer transposer = new Transposer("G", "A");
        String transposed = transposer.doTranspose("G");
        Assertions.assertThat(transposed).isEqualTo("A");
    }

    @Test
    public void transposeMinorChord() {
        Transposer transposer = new Transposer("G", "A");
        String transposed = transposer.doTranspose("Bm");
        Assertions.assertThat(transposed).isEqualTo("C#m");
    }

    @Test
    public void transpose7thChord() {
        Transposer transposer = new Transposer("G", "A");
        String transposed = transposer.doTranspose("D7");
        Assertions.assertThat(transposed).isEqualTo("E7");
    }

}