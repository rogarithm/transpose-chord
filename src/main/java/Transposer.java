import model.Degree;
import model.Interval;
import model.Note;
import model.Symbol;

public class Transposer {

    public Transposer() {
    }

    public String doTransposeWithInterval(Symbol chord, Note currentKey, Note transposedKey) {
        Interval itv = new Interval();

        String rootNote = chord.getRootNote();
        String other = chord.getOther();

        Note note = Note.valueOf(rootNote);
        int steps = itv.getInterval(currentKey, note);
        Note transposedNote = itv.getRaisedNote(transposedKey, steps);

        return transposedNote.toString() + other;
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
