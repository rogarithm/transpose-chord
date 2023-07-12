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

        Note basis = root;
        if (!basis.isPlain()) {
            basis = getDisplayBasis(basis);
        }

        int degreeNumber = 1;
        NoteDisplayBasis degreeOneDisplayBasis = NoteDisplayBasis.from(basis);
        degreeOneDisplayBasis.degreeNumber = new DegreeNumber(degreeNumber);
        degreeNumber++;

        NoteDisplayBasis nextDisplayBasis = NoteDisplayBasis.from(degreeOneDisplayBasis.next);

        while (!nextDisplayBasis.current.equals(degreeOneDisplayBasis.current)) {
            nextDisplayBasis.degreeNumber = new DegreeNumber(degreeNumber);
            nextDisplayBasis = NoteDisplayBasis.from(nextDisplayBasis.next);
            degreeNumber++;
        }
    }

    private NoteDisplayBasis displayBasis(DegreeNumber degreeNumber) {

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber.equals(degreeNumber)) {
                return noteDisplayBasis;
            }
        }

        throw new IllegalArgumentException(
                this.getClass().getCanonicalName() + ": no suitable display basis for degree number of " + degreeNumber
        );
    }

    private NoteDisplayBasis displayBasis(Note note) {

        Note basis = note;
        if (!basis.isPlain()) {
            basis = getDisplayBasis(basis);
        }

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.current.equals(basis)) {
                return noteDisplayBasis;
            }
        }

        throw new IllegalArgumentException(
                this.getClass().getCanonicalName() + ": no suitable display basis for note of " + note
        );
    }

    public Note displayBasisNote(DegreeNumber degreeNumber) {

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber.equals(degreeNumber)) {
                return noteDisplayBasis.current;
            }
        }

        throw new IllegalArgumentException(
                this.getClass().getCanonicalName()
                        + ": no suitable note representing display basis for degree number of " + degreeNumber
        );
    }

    private Note getDisplayBasis(Note note) {
        return NoteFactory.create(note.toString().substring(0, 1));
    }

    public boolean compareDisplayBasis(Note note, DegreeNumber degreenumber) {
        NoteDisplayBasis displaybasis1 = this.displayBasis(note);
        NoteDisplayBasis displaybasis2 = this.displayBasis(degreenumber);
        return displaybasis1.equals(displaybasis2);
    }
}