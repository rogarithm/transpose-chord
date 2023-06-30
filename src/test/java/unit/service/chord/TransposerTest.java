package unit.service.chord;

import model.Chord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import service.chord.Transposer;

class TransposerTest {

    @Test
    public void transposeMajorChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose("G");
        Assertions.assertThat(transposed.toString()).isEqualTo("A");
    }

    @Test
    public void transposeMinorChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose("Bm");
        Assertions.assertThat(transposed.toString()).isEqualTo("C#m");
    }

    @Test
    public void transpose7thChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose("D7");
        Assertions.assertThat(transposed.toString()).isEqualTo("E7");
    }

}