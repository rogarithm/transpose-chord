package chordtransposer.chord;

import chordtransposer.model.Chord;
import chordtransposer.model.Degree;
import chordtransposer.model.DegreeNumber;
import chordtransposer.model.Key;
import chordtransposer.model.SemitoneCount;
import chordtransposer.model.note.Note;
import chordtransposer.model.note.NoteFactory;

public class Transposer {

    private final Degree degree;
    private final Key currentKey;
    private final Key transposedKey;

    public Transposer(String currentRoot, String transposedRoot) {
        this.degree = new Degree(NoteFactory.create(transposedRoot));
        this.currentKey = new Key(NoteFactory.create(currentRoot));
        this.transposedKey = new Key(NoteFactory.create(transposedRoot));
    }

    public Chord doTranspose(Chord chord) {
        Note currentBass = NoteFactory.create(chord.getRootNote());

        SemitoneCount semitones = currentKey.semitones(currentBass);
        Note transposedBass = transposedKey.raise(semitones);
        DegreeNumber degreeNumber =  currentKey.degree(semitones);

        Note resultBass = transposedKey.format(transposedBass, degreeNumber, degree);

        return new Chord(resultBass.toString() + chord.getChordTones());
    }
}