package service.chord;

import model.Chord;
import model.Degree;
import model.DegreeNumber;
import model.Interval;
import model.Key;
import model.SemitoneCount;
import model.note.Note;
import model.note.NoteFactory;

public class Transposer {

    private final Note currentKey;
    private final Note transposeTo;
    private final Degree degree;
    private final Key key;

    public Transposer(String currentKey, String transposeTo) {
        this.currentKey = NoteFactory.create(currentKey);
        this.transposeTo = NoteFactory.create(transposeTo);
        this.degree = new Degree(this.transposeTo);
        this.key = new Key(this.transposeTo);
    }

    public Chord doTranspose(Chord chord) {
        Note bass = NoteFactory.create(chord.getRootNote());

        Interval interval = new Interval();
        SemitoneCount semitones = interval.semitones(currentKey, bass);
        Note bassOfTransposedKey = interval.raise(transposeTo, semitones);

        DegreeNumber degreeNumber = interval.degree(semitones);
        Note noteToFormat = degree.note(degreeNumber);

        if (!bassOfTranposedKey.equals(noteToFormat)) {
            bassOfTranposedKey = key.convertToSharpNoteOfSamePitch(bassOfTranposedKey, noteToFormat);
        }

        return new Chord(bassOfTranposedKey.toString() + chord.getChordTones());
    }
}