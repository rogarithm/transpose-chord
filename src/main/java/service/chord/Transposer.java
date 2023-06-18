package service.chord;

import model.Chord;
import model.Degree;
import model.Key;
import model.note.NoteFactory;
import model.Interval;
import model.note.Note;

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

    public String doTranspose(String chordString) {
        Chord chord = new Chord(chordString);
        Note bass = NoteFactory.create(chord.getRootNote());

        Interval interval = new Interval();
        int semitones = interval.getSemitonesBetween(currentKey, bass);
        Note bassOfTranposedKey = interval.getRaisedNote(transposeTo, semitones);

        int degreeNumber = interval.getDegreeFromSemitones(semitones);
        String noteToFormat = degree.getNoteOf(degreeNumber);

        if (!bassOfTranposedKey.toString().equals(noteToFormat)) {
            bassOfTranposedKey = key.convertToSharpNoteOfSamePitch(bassOfTranposedKey, noteToFormat);
        }

        return bassOfTranposedKey.toString() + chord.getChordTones();
    }
}