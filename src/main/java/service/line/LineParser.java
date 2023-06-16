package service.line;

import java.util.ArrayList;
import java.util.List;
import model.Symbol;
import service.chord.Transposer;

public class LineParser implements Parser {

    private final Transposer transposer;

    public LineParser(Transposer transposer) {
        this.transposer = transposer;
    }

    public List<Symbol> collectChordsInLine(String line) {
        List<Symbol> result = new ArrayList<>();
        String[] splitted = line.split(" +");

        for (String chord : splitted) {
            result.add(new Symbol(chord));
        }

        return result;
    }

    public List<Symbol> transposeChordsInLine(List<Symbol> chords) {
        List<Symbol> result = new ArrayList<>();

        for (Symbol chord : chords) {
            String transposed = transposer.doTranspose(chord.toString());
            result.add(new Symbol(transposed));
        }

        return result;
    }

    public List<String> parseLine(String line) {
        List<Symbol> chords = collectChordsInLine(line);
        List<Symbol> transposedChords = transposeChordsInLine(chords);

        List<String> result = new ArrayList<>();
        for (Symbol chord : transposedChords) {
            result.add(chord.toString());
        }

        return result;
    }
}