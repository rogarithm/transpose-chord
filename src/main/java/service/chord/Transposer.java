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

    private final Note currentRoot;
    private final Note transposedRoot;
    private final Degree degree;
    private final Key transposedKey;

    public Transposer(String currentRoot, String transposedRoot) {
        this.currentRoot = NoteFactory.create(currentRoot);
        this.transposedRoot = NoteFactory.create(transposedRoot);
        this.degree = new Degree(this.transposedRoot);
        this.transposedKey = new Key(this.transposedRoot);
    }

    public Chord doTranspose(Chord chord) {
        Note currentBass = NoteFactory.create(chord.getRootNote());

        Interval interval = new Interval();
        SemitoneCount semitones = interval.semitones(currentRoot, currentBass);

        Note tranposedBass = interval.raise(transposedRoot, semitones);
        DegreeNumber degreeNumber = interval.degree(semitones);

        Note resultBasis1 = degree.displayBasis(tranposedBass);
        Note resultBasis2 = degree.displayBasis(degreeNumber);
        boolean needConvertToSharp = !resultBasis1.equals(resultBasis2);
        if (needConvertToSharp) {
            tranposedBass = transposedKey.convertToSharpNoteOfSamePitch(tranposedBass, resultBasis2);
        }

        return new Chord(tranposedBass.toString() + chord.getChordTones());
    }
}