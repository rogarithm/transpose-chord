package service.chord;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Chord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransposerTest {

    List<Chord> notesInBflatKey;
    List<Chord> notesInAKey;

    @BeforeEach
    public void setUp() {

        notesInBflatKey = List.of("Bb", "C", "D", "Eb", "F", "G", "A")
                              .stream()
                              .map(Chord::new)
                              .collect(Collectors.toList());

        notesInAKey = List.of("A", "B", "C#", "D", "E", "F#", "G#")
                          .stream()
                          .map(Chord::new)
                          .collect(Collectors.toList());
    }

    @Test
    public void transposeFromFlatToPlain() {

        Transposer transposer = new Transposer("Bb", "A");
        List<Chord> chords = notesInBflatKey;

        List<Chord> transposed = new ArrayList<>();
        for (Chord chord : chords) {
            transposed.add(transposer.doTranspose(chord));
        }

        List<Chord> expected = notesInAKey;

        for (int i = 0; i < transposed.size(); i++) {
            assertThat(transposed.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void transposeFromPlainToFlat() {

        Transposer transposer = new Transposer("A", "Bb");
        List<Chord> chords = notesInAKey;

        List<Chord> transposed = new ArrayList<>();
        for (Chord chord : chords) {
            transposed.add(transposer.doTranspose(chord));
        }

        List<Chord> expected = notesInBflatKey;

        for (int i = 0; i < transposed.size(); i++) {
            assertThat(transposed.get(i)).isEqualTo(expected.get(i));
        }
    }
}