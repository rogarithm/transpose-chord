package chordtransposer.cmdline.service.line;

import chordtransposer.chord.Transposer;
import chordtransposer.model.Chord;
import chordtransposer.model.Line;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineParser implements Parser {

    private final Transposer transposer;

    public LineParser(Transposer transposer) {
        this.transposer = transposer;
    }

    public List<Chord> parseLine(Line line) {

        List<Chord> chords = collectChordsInLine(line);
        return transposeChordsInLine(chords);
    }

    private List<Chord> collectChordsInLine(Line line) {

        return Arrays.stream(line
                             .toString()
                             .split(" +"))
                     .map(Chord::new)
                     .collect(Collectors.toList());
    }

    private List<Chord> transposeChordsInLine(List<Chord> chords) {

        return chords.stream()
                     .map(transposer::doTranspose)
                     .collect(Collectors.toList());
    }
}