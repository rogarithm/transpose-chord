package model;

import model.note.Note;

public class Degree {

    private enum NoteDisplayBasis {
        C("C", "D"),
        D("D", "E"),
        E("E", "F"),
        F("F", "G"),
        G("G", "A"),
        A("A", "B"),
        B("B", "C");

        private final String ofCurrentDegree;
        private final String ofNextDegree;
        private int degreeNumber;

        NoteDisplayBasis(String ofCurrentDegree, String ofNextDegree) {
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
        degreeOneDisplayBasis.degreeNumber = degreeNumber;
        degreeNumber++;

        NoteDisplayBasis displayBasis = NoteDisplayBasis.valueOf(degreeOneDisplayBasis.ofNextDegree);

        while (!displayBasis.ofCurrentDegree.equals(degreeOneDisplayBasis.ofCurrentDegree)) {
            displayBasis.degreeNumber = degreeNumber;
            displayBasis = NoteDisplayBasis.valueOf(displayBasis.ofNextDegree);
            degreeNumber++;
        }
    }

    public int getDegreeNumberOf(Note note) {

        NoteDisplayBasis noteDisplayBasis = NoteDisplayBasis.valueOf(note.toString());
        return noteDisplayBasis.degreeNumber;
    }

    public String getNoteOf(int degreeNumber) {

        if (degreeNumber < 1 || degreeNumber > 8) {
            throw new IllegalArgumentException("you put invalid degree number: " + degreeNumber + "\nThe degree number should be between 1 and 8 (inclusive).");
        }

        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber == degreeNumber) {
                return noteDisplayBasis.ofCurrentDegree;
            }
        }

        throw new IllegalArgumentException("there's no note for given degree number: " + degreeNumber);
    }
}
