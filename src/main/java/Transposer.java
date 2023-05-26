import model.note.NoteFactory;
import model.Interval;
import model.note.Note;
import model.Symbol;

public class Transposer {

    private final Symbol chord;
    private final Note currentKey;
    private final Note transposedKey;

    public Transposer(String chord, String currentKey, String transposedKey) {
        this.chord = new Symbol(chord);
        this.currentKey = NoteFactory.create(currentKey);
        this.transposedKey = NoteFactory.create(transposedKey);
    }

    public String doTranspose() {
        Interval itv = new Interval();

        String rootNote = chord.getRootNote();
        String chordTones = chord.getChordTones();

        Note note = NoteFactory.create(rootNote);
        int steps = itv.getNumberOfSemitonesBetween(currentKey, note);
        Note transposedNote = itv.getRaisedNote(transposedKey, steps);

        return transposedNote.toString() + chordTones;
    }
}
