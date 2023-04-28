import model.Degree;
import model.Note;
import model.Symbol;

public class Transposer {

    public Transposer() {
    }

    public String doTranspose(Symbol chord, Note currentKey, Note transposedKey) {
        String rootNote = chord.getRootNote();
        String other = chord.getOther();

        Note note = Note.valueOf(rootNote);
        Degree degree = Degree.getDegree(note, currentKey);
        Note transposedNote = note.getTransposedNote(transposedKey, degree);

        return transposedNote.toString() + other;
    }
}
