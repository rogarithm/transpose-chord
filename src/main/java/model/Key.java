package model;

import model.note.Note;
import model.note.NoteFactory;

public class Key {

    private enum EquivalentNoteFinder {
        Db("Db", "C#"),
        Eb("Eb", "D#"),
        Gb("Gb", "F#"),
        Ab("Ab", "G#"),
        Bb("Bb", "A#");

        private final String name;
        private final String alternate;

        EquivalentNoteFinder(String name, String alternate) {

            this.name = name;
            this.alternate = alternate;
        }

        static String findEquivalentNoteMeetsFormat(Note note, String format) {

            for (EquivalentNoteFinder noteFinder : EquivalentNoteFinder.values()) {
                if (noteFinder.name.equals(note.toString()) &&
                        noteFinder.alternate.substring(0,1).equals(format)) {
                    return noteFinder.alternate;
                }
            }

            throw new IllegalArgumentException("can't format given note " + note + " in " + format + "!");
        }
    }

    private final Note rootNote;
    private final Interval interval;

    public Key(Note rootNote) {

        this.rootNote = rootNote;
        this.interval = new Interval();
    }

    public Note formatNoteIn(Note note, String format) {

        String formatted = EquivalentNoteFinder.findEquivalentNoteMeetsFormat(note, format);
        return NoteFactory.create(formatted);
    }
}
