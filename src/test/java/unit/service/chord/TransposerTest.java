package unit.service.chord;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Chord;
import org.junit.jupiter.api.Test;
import service.chord.Transposer;

class TransposerTest {

    @Test
    public void transposeFromFlatToPlain() {
        Transposer transposer = new Transposer("Bb", "A");
        List<Chord> chords = List.of("Bb", "C", "D", "Eb", "F", "G", "A")
                                 .stream()
                                 .map(Chord::new)
                                 .collect(Collectors.toList());

        List<Chord> transposed = new ArrayList<>();
        for (Chord chord : chords) {
            transposed.add(transposer.doTranspose(chord));
        }

        List<Chord> expected = List.of("A", "B", "C#", "D", "E", "F#", "G#")
                                   .stream()
                                   .map(Chord::new)
                                   .collect(Collectors.toList());

        for (int i = 0; i < transposed.size(); i++) {
            assertThat(transposed.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void transposeFromPlainToFlat() {
        Transposer transposer = new Transposer("A", "Bb");
        List<Chord> chords = List.of("A", "B", "C#", "D", "E", "F#", "G#")
                                 .stream()
                                 .map(Chord::new)
                                 .collect(Collectors.toList());

        List<Chord> transposed = new ArrayList<>();
        for (Chord chord : chords) {
            transposed.add(transposer.doTranspose(chord));
        }

        List<Chord> expected = List.of("Bb", "C", "D", "Eb", "F", "G", "A")
                                   .stream()
                                   .map(Chord::new)
                                   .collect(Collectors.toList());

        for (int i = 0; i < transposed.size(); i++) {
            assertThat(transposed.get(i)).isEqualTo(expected.get(i));
        }
    }
}