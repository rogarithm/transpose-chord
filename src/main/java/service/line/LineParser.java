package service.line;

import java.util.ArrayList;
import java.util.List;
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
        List<Chord> result = new ArrayList<>();
        String[] splitted = line.toString().split(" +");

        for (String chord : splitted) {
            result.add(new Chord(chord));
        }

        return result;
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