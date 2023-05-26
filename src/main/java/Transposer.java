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

        Note bass = NoteFactory.create(chord.getRootNote());

        Interval interval = new Interval();
        int semitones = interval.getNumberOfSemitonesBetween(currentKey, bass);
        Note bassOfTranposedKey = interval.getRaisedNote(transposedKey, semitones);

        return bassOfTranposedKey.toString() + chord.getChordTones();
    }
}
