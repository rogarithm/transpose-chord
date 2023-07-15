package chordtransposer.cmdline.learning.jdk;


import static org.assertj.core.api.Assertions.assertThat;

import chordtransposer.chord.Transposer;
import chordtransposer.chord.TransposerFactory;
import chordtransposer.model.Chord;
import chordtransposer.model.Line;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @Test
    public void convertStringToChordList() {

        List<Chord> expected = List.of(new Chord("G"), new Chord("Bm"), new Chord("D"), new Chord("C"));
        List<Chord> collect = Arrays.stream(new Line("G Bm D C")
                                            .toString()
                                            .split(" +"))
                                    .map(Chord::new)
                                    .collect(Collectors.toList());

        for (int i = 0; i < collect.size(); i++) {
            assertThat(collect.get(i).toString()).isEqualTo(expected.get(i).toString());
        }
    }

    @Test
    public void makeListAfterToString() {

        List<String> expected = List.of("G", "Bm", "D", "C");
        List<String> collect = List.of(new Chord("G"), new Chord("Bm"), new Chord("D"), new Chord("C"))
                                   .stream()
                                   .map(Chord::toString)
                                   .collect(Collectors.toList());

        for (int i = 0; i < collect.size(); i++) {
            assertThat(collect.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void makeChordListAfterTraspose() {

        Transposer transposer = TransposerFactory.create("G", "E");
        List<Chord> expected = List.of(new Chord("E"), new Chord("G#m"), new Chord("B"), new Chord("A"));
        List<Chord> chords = List.of(new Chord("G"), new Chord("Bm"), new Chord("D"), new Chord("C"));

        List<Chord> collect = chords.stream()
                                    .map(transposer::doTranspose)
                                    .collect(Collectors.toList());

        for (int i = 0; i < collect.size(); i++) {
            assertThat(collect.get(i).toString()).isEqualTo(expected.get(i).toString());
        }
    }
}