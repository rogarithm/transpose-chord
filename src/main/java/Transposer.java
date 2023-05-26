import model.Degree;
import model.Key;
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
        int semitones = interval.getSemitonesBetween(currentKey, bass);
        Note bassOfTranposedKey = interval.getRaisedNote(transposeTo, semitones);

        int degreeNumber = interval.getDegreeFromSemitones(semitones);
        Degree degree = new Degree(transposedKey);
        String noteToFormat = degree.getNoteOf(degreeNumber);

        if (!bassOfTranposedKey.toString().equals(noteToFormat)) {
            Key key = new Key(transposedKey);
            bassOfTranposedKey = key.convertToSharpNoteOfSamePitch(bassOfTranposedKey, noteToFormat);
        }

        return bassOfTranposedKey.toString() + chord.getChordTones();
    }
}
