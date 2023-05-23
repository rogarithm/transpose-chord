package model;

import model.note.Note;
import model.note.NoteFactory;

public class Key {

    private enum EquivalentNote {
        Db("Db", "C#"),
        Eb("Eb", "D#"),
        Gb("Gb", "F#"),
        Ab("Ab", "G#"),
        Bb("Bb", "A#");

        private final String current;
        private final String alternate;

        EquivalentNote(String current, String next) {
            this.current = current;
            this.alternate = next;
        }
    }

    private final Note rootNote;
    private final Interval interval;

    public Key(Note rootNote) {
        this.rootNote = rootNote;
        this.interval = new Interval();
    }

    public Note formatNoteIn(Note note, String format) {
        for (EquivalentNote equivalentNote : EquivalentNote.values()) {
            if (equivalentNote.current.equals(note.toString()) &&
            equivalentNote.alternate.substring(0,1).equals(format)) {
                return NoteFactory.create(equivalentNote.alternate);
            }
        }

        throw new IllegalArgumentException("can't format given note " + note + " in " + format + "!");
    }
}
