package model;

import model.note.Note;

public class Degree {

    private enum Degrees {
        C("C", "D"),
        D("D", "E"),
        E("E", "F"),
        F("F", "G"),
        G("G", "A"),
        A("A", "B"),
        B("B", "C");

        private final String current;
        private final String next;
        private int degree;

        Degrees(String current, String next) {
            this.current = current;
            this.next = next;
        }
    }

    Degree(Note root) {
        int degree = 1;
        Degrees currentNote = Degrees.valueOf(root.toString());
        currentNote.degree = degree;
        degree++;

        Degrees nextNote = Degrees.valueOf(currentNote.next);
        while (!nextNote.current.equals(currentNote.current)) {
            nextNote.degree = degree;
            nextNote = Degrees.valueOf(nextNote.next);
            degree++;
        }
    }

    public int getDegreeOfNote(Note note) {
        Degrees degrees = Degrees.valueOf(note.toString());
        return degrees.degree;
    }

}
