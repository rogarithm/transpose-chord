package model;

import model.note.Note;
import model.note.NoteFactory;

public class Degree {

    private enum NoteDisplayBasis {
        C(NoteFactory.create("C"), NoteFactory.create("D")),
        D(NoteFactory.create("D"), NoteFactory.create("E")),
        E(NoteFactory.create("E"), NoteFactory.create("F")),
        F(NoteFactory.create("F"), NoteFactory.create("G")),
        G(NoteFactory.create("G"), NoteFactory.create("A")),
        A(NoteFactory.create("A"), NoteFactory.create("B")),
        B(NoteFactory.create("B"), NoteFactory.create("C"));

        private final Note ofCurrentDegree;
        private final Note ofNextDegree;
        private DegreeNumber degreeNumber;

        NoteDisplayBasis(Note ofCurrentDegree, Note ofNextDegree) {
            this.ofCurrentDegree = ofCurrentDegree;
            this.ofNextDegree = ofNextDegree;
        }
    }

    public Degree(Note rootNote) {

        initializeNoteDisplayBasis(rootNote);
    }

    private void initializeNoteDisplayBasis(Note rootNote) {

        int degreeNumber = 1;
        NoteDisplayBasis degreeOneDisplayBasis = NoteDisplayBasis.valueOf(rootNote.toString());
        degreeOneDisplayBasis.degreeNumber = new DegreeNumber(degreeNumber);
        degreeNumber++;

        NoteDisplayBasis displayBasis = NoteDisplayBasis.valueOf(degreeOneDisplayBasis.ofNextDegree.toString());

        while (!displayBasis.ofCurrentDegree.equals(degreeOneDisplayBasis.ofCurrentDegree)) {
            displayBasis.degreeNumber = new DegreeNumber(degreeNumber);
            displayBasis = NoteDisplayBasis.valueOf(displayBasis.ofNextDegree.toString());
            degreeNumber++;
        }
    }

    public int getDegreeNumberOf(Note note) {

        NoteDisplayBasis noteDisplayBasis = NoteDisplayBasis.valueOf(note.toString());
        return noteDisplayBasis.degreeNumber.number();
    }

    public Note getNoteOf(DegreeNumber degreeNumber) {

        if (degreeNumber.number() < 1 || degreeNumber.number() > 8) {
            throw new IllegalArgumentException(this.getClass().getCanonicalName() + ": you put invalid degree number " + degreeNumber
                    + "\nThe degree number should be between 1 and 8 (inclusive).");
        }

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber.number() == degreeNumber.number()) {
                return NoteFactory.create(noteDisplayBasis.ofCurrentDegree.toString());
            }
        }

        throw new IllegalArgumentException(this.getClass().getCanonicalName() + ": there's no note for given degree number for " + degreeNumber);
    }
}