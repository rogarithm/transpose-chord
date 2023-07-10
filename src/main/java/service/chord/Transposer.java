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

        Note resultBasis1 = degree.displayBasis(transposedBass);
        Note resultBasis2 = degree.displayBasis(degreeNumber);
        boolean needConvertToSharp = !resultBasis1.equals(resultBasis2);
        if (needConvertToSharp) {
            transposedBass = transposedKey.convertToSharpNoteOfSamePitch(transposedBass, resultBasis2);
        }

        return new Chord(transposedBass.toString() + chord.getChordTones());
    }
}