package model;

import model.note.NoteFactory;
import model.note.Note;

public class Interval {

    enum Intervals {
        MAJOR_SECOND(2, 2),
        MAJOR_THIRD(3, 4),
        MAJOR_FOURTH(4, 5),
        MAJOR_FIFTH(5, 7),
        MAJOR_SIXTH(6, 9),
        MAJOR_SEVENTH(7, 11);

        private final int degree;
        private final int offset;

        Intervals(int degree, int offset) {
            this.degree = degree;
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }

        public int getDegree() {
            return degree;
        }
    }

    private enum NextNotes {
        C("C", "Db", "C#"),
        Db("Db", "D"),
        D("D", "Eb", "D#"),
        Eb("Eb", "E"),
        E("E", "F"),
        F("F", "Gb", "F#"),
        Gb("Gb", "G"),
        G("G", "Ab", "G#"),
        Ab("Ab", "A"),
        A("A", "Bb", "A#"),
        Bb("Bb", "B"),
        B("B", "C");

        private final String current;
        private final String next;
        private String alternate;

        NextNotes(String current, String next) {
            this.current = current;
            this.next = next;
        }

        NextNotes(String current, String next, String alternate) {
            this.current = current;
            this.next = next;
            this.alternate = alternate;
        }
    }

    public int getInterval(Note base, Note target) {
        if (base.toString().endsWith("#")) {
            base = findEquivalentNote(base);
        }

        int result = 0;
        NextNotes currentNote = NextNotes.valueOf(base.toString());

        while (!currentNote.current.equals(target.toString())) {
            currentNote = NextNotes.valueOf(currentNote.next);
            result += 1;
        }

        if (currentNote.current.equals(target.toString())) {
            return result;
        }

        throw new IllegalArgumentException("can't find interval from " + base + " to " + target + "!");
    }

    public Note getRaisedNote(Note base, int steps) {
        if (base.toString().endsWith("#")) {
            base = findEquivalentNote(base);
        }

        NextNotes currentNote = NextNotes.valueOf(base.toString());

        while (steps != 0) {
            currentNote = NextNotes.valueOf(currentNote.next);
            steps -= 1;
        }

        return NoteFactory.create(currentNote.current);
    }

    private Note findEquivalentNote(Note note) {

        for (NextNotes nextNote : NextNotes.values()) {
            if (nextNote.alternate != null && nextNote.alternate.equals(note.toString())) {
                note = NoteFactory.create(nextNote.next);
            }
        }

        return note;
    }

    public Intervals getIntervalName(int steps) {
        for (Intervals itvName : Intervals.values()) {
            if (steps == itvName.offset)
                return itvName;
        }

        throw new IllegalArgumentException("can't find interval name for given semitones count: " + steps);
    }
}
