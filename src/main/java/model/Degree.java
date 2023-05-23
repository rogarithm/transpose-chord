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

    Degree(Note rootNote) {
        int degreeNumber = 1;
        NoteDisplayBasis degree1DisplayBasis = NoteDisplayBasis.valueOf(rootNote.toString());
        degree1DisplayBasis.degreeNumber = degreeNumber;
        degreeNumber++;

        NoteDisplayBasis displayBasis = NoteDisplayBasis.valueOf(degree1DisplayBasis.ofNextDegree);
        while (!displayBasis.ofCurrentDegree.equals(degree1DisplayBasis.ofCurrentDegree)) {
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
        for (NoteDisplayBasis noteDisplayBasis : NoteDisplayBasis.values()) {
            if (noteDisplayBasis.degreeNumber == degreeNumber) {
                return noteDisplayBasis.ofCurrentDegree;
            }
        }
        throw new IllegalArgumentException("there's no note for given degreeNumber: " + degreeNumber);
    }
}
