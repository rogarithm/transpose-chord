package model;

import factory.NoteFactory;

public class Interval {

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

        throw new IllegalArgumentException("can't find interval for given target note!");
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
}
