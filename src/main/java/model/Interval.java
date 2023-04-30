package model;

public class Interval {

    private enum NextNotes {
        C("C", "Db"),
        Db("Db", "D"),
        D("D", "Eb"),
        Eb("Eb", "E"),
        E("E", "F"),
        F("F", "Gb"),
        Gb("Gb", "G"),
        G("G", "Ab"),
        Ab("Ab", "A"),
        A("A", "Bb"),
        Bb("Bb", "B"),
        B("B", "C");

        private final String current;
        private final String next;

        NextNotes(String current, String next) {
            this.current = current;
            this.next = next;
        }
    }

    public int getInterval(Note base, Note target) {
        int result = 0;
        NextNotes currentNote = NextNotes.valueOf(base.name());

        while (!currentNote.current.equals(target.name())) {
            currentNote = NextNotes.valueOf(currentNote.next);
            result += 1;
        }

        if (currentNote.current.equals(target.name())) {
            return result;
        }

        throw new IllegalArgumentException("can't find interval for given target note!");
    }

    public Note getRaisedNote(Note base, int steps) {
        NextNotes currentNote = NextNotes.valueOf(base.name());

        while (steps != 0) {
            currentNote = NextNotes.valueOf(currentNote.next);
            steps -= 1;
        }

        return Note.valueOf(currentNote.current);
    }
}
