package learning.jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.Chord;
import model.Line;
import org.assertj.core.api.Assertions;
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
            Assertions.assertThat(collect.get(i).toString()).isEqualTo(expected.get(i).toString());
        }
    }

}