package unit.service.chord;

import model.Chord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import service.chord.Transposer;

class TransposerTest {

    @Test
    public void transposeMajorChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose(new Chord("G"));
        Assertions.assertThat(transposed).isEqualTo(new Chord("A"));
    }

    @Test
    public void transposeMinorChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose(new Chord("Bm"));
        Assertions.assertThat(transposed).isEqualTo(new Chord("C#m"));
    }

    @Test
    public void transpose7thChord() {
        Transposer transposer = new Transposer("G", "A");
        Chord transposed = transposer.doTranspose(new Chord("D7"));
        Assertions.assertThat(transposed).isEqualTo(new Chord("E7"));
    }

    @Test
    public void t() {
        Transposer transposer = new Transposer("A", "Bb");

        Chord transposed = transposer.doTranspose(new Chord("A"));
        Chord expected = new Chord("Bb");

        Assertions.assertThat(transposed).isEqualTo(expected);
    }
}