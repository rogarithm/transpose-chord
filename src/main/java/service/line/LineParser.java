package service.line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Chord;
import model.Line;
import service.chord.Transposer;

public class LineParser implements Parser {

    private final Transposer transposer;

    public LineParser(Transposer transposer) {
        this.transposer = transposer;
    }

    public List<String> parseLine(Line line) {
        List<Chord> chords = collectChordsInLine(line);
        List<Chord> transposedChords = transposeChordsInLine(chords);

        List<String> result = new ArrayList<>();
        for (Chord chord : transposedChords) {
            result.add(chord.toString());
        }

        return result;
    }

    private List<Chord> collectChordsInLine(Line line) {

        return Arrays.stream(line
                             .toString()
                             .split(" +"))
                     .map(Chord::new)
                     .collect(Collectors.toList());
    }

    private List<Chord> transposeChordsInLine(List<Chord> chords) {
        List<Chord> result = new ArrayList<>();

        for (Chord chord : chords) {
            String transposed = transposer.doTranspose(chord.toString());
            result.add(new Chord(transposed));
        }

        return result;
    }
}