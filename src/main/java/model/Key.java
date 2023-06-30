package model;

import model.note.Note;
import model.note.NoteFactory;

public class Key {

    private enum EquivalentNoteFinder {
        Db(NoteFactory.create("Db"), NoteFactory.create("C#")),
        Eb(NoteFactory.create("Eb"), NoteFactory.create("D#")),
        Gb(NoteFactory.create("Gb"), NoteFactory.create("F#")),
        Ab(NoteFactory.create("Ab"), NoteFactory.create("G#")),
        Bb(NoteFactory.create("Bb"), NoteFactory.create("A#"));

        private final Note name;
        private final Note alternate;

        EquivalentNoteFinder(Note name, Note alternate) {

            this.name = name;
            this.alternate = alternate;
        }

        static Note findEquivalentNoteMeetsFormat(Note note, Note basis) {

            for (EquivalentNoteFinder noteFinder : EquivalentNoteFinder.values()) {
                if (noteFinder.name.toString().equals(note.toString()) &&
                        noteFinder.alternate.toString().substring(0,1).equals(basis.toString())) {
                    return noteFinder.alternate;
                }
            }

            throw new IllegalArgumentException(EquivalentNoteFinder.class.getCanonicalName() + ": can't format given note " + note + " in " + basis + "!");
        }
    }

    private final Note rootNote;
    private final Interval interval;

    public Key(Note rootNote) {

        this.rootNote = rootNote;
        this.interval = new Interval();
    }

    public Note convertToSharpNoteOfSamePitch(Note note, Note basis) {

        Note formatted = EquivalentNoteFinder.findEquivalentNoteMeetsFormat(note, basis);
        return formatted;
    }
}