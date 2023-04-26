import model.Degree;
import model.Note;
import model.Symbol;

public class Transposer {

    private ChordParser parser;

    public Transposer(ChordParser parser) {
        this.parser = parser;
    }

    public String doTranspose(Symbol chord, Note currentKey, Note transposedKey) {
        String rootNote = parser.getRootNote(chord);
        String other = parser.getOther(chord);

        Note note = Note.valueOf(rootNote);
        Degree degree = Degree.getDegree(note, currentKey);
        Note transposedNote = note.getTransposedNote(transposedKey, degree);

        return transposedNote.toString() + other;
    }
}
