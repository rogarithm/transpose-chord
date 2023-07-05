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

        private final Note current;
        private final Note next;
        private DegreeNumber degreeNumber;

        NoteDisplayBasis(Note current, Note next) {
            this.current = current;
            this.next = next;
        }

        static NoteDisplayBasis from(Note note) {
            return NoteDisplayBasis.valueOf(note.toString());
        }
    }

    public Degree(Note root) {

        initializeNoteDisplayBasis(root);
    }

    private void initializeNoteDisplayBasis(Note root) {

        int degreeNumber = 1;
        NoteDisplayBasis degreeOneDisplayBasis = NoteDisplayBasis.from(root);
        degreeOneDisplayBasis.degreeNumber = new DegreeNumber(degreeNumber);
        degreeNumber++;

        NoteDisplayBasis nextDisplayBasis = NoteDisplayBasis.from(degreeOneDisplayBasis.next);

        while (!nextDisplayBasis.current.equals(degreeOneDisplayBasis.current)) {
            nextDisplayBasis.degreeNumber = new DegreeNumber(degreeNumber);
            nextDisplayBasis = NoteDisplayBasis.from(nextDisplayBasis.next);
            degreeNumber++;
        }
    }

    public DegreeNumber getDegreeNumberOf(Note note) {

        NoteDisplayBasis displayBasis = NoteDisplayBasis.from(note);
        return displayBasis.degreeNumber;
    }

    public Note getNoteOf(DegreeNumber degreeNumber) {

        if (degreeNumber.number() < 1 || degreeNumber.number() > 8) {
            throw new IllegalArgumentException(this.getClass().getCanonicalName() + ": you put invalid degree number " + degreeNumber
                    + "\nThe degree number should be between 1 and 8 (inclusive).");
        }

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber.equals(degreeNumber)) {
                return noteDisplayBasis.current;
            }
        }

        throw new IllegalArgumentException(this.getClass().getCanonicalName() + ": there's no note for given degree number for " + degreeNumber);
    }
}