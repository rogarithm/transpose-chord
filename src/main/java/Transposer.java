import model.note.NoteFactory;
import model.Interval;
import model.note.Note;
import model.Symbol;

public class Transposer {

    public Transposer() {
    }

    public String doTranspose(Symbol chord, Note currentKey, Note transposedKey) {
        Interval itv = new Interval();

        String rootNote = chord.getRootNote();
        String chordTones = chord.getChordTones();

        Note note = NoteFactory.create(rootNote);
        int steps = itv.getNumberOfSemitonesBetween(currentKey, note);
        Note transposedNote = itv.getRaisedNote(transposedKey, steps);

        return transposedNote.toString() + chordTones;
    }
}
