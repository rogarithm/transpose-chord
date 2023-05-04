import model.Interval;
import model.Note;
import model.Symbol;

public class Transposer {

    public Transposer() {
    }

    public String doTranspose(Symbol chord, Note currentKey, Note transposedKey) {
        Interval itv = new Interval();

        String rootNote = chord.getRootNote();
        String other = chord.getOther();

        Note note = Note.valueOf(rootNote);
        int steps = itv.getInterval(currentKey, note);
        Note transposedNote = itv.getRaisedNote(transposedKey, steps);

        return transposedNote.toString() + other;
    }
}
