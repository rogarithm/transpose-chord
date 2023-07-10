package service.chord;

import model.Chord;
import model.Degree;
import model.DegreeNumber;
import model.Key;
import model.SemitoneCount;
import model.note.Note;
import model.note.NoteFactory;

public class Transposer {

    private final Note currentRoot;
    private final Note transposedRoot;
    private final Degree degree;
    private final Key currentKey;
    private final Key transposedKey;

    public Transposer(String currentRoot, String transposedRoot) {
        this.currentRoot = NoteFactory.create(currentRoot);
        this.transposedRoot = NoteFactory.create(transposedRoot);
        this.degree = new Degree(this.transposedRoot);
        this.currentKey = new Key(this.currentRoot);
        this.transposedKey = new Key(this.transposedRoot);
    }

    public Chord doTranspose(Chord chord) {
        Note currentBass = NoteFactory.create(chord.getRootNote());

        SemitoneCount semitones = currentKey.semitones(currentRoot, currentBass);
        Note transposedBass = currentKey.raise(transposedRoot, semitones);
        DegreeNumber degreeNumber =  currentKey.degree(semitones);

        Note resultBass = transposedKey.format(transposedBass, degreeNumber, degree);

        return new Chord(resultBass.toString() + chord.getChordTones());
    }
}