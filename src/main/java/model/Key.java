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
                if (noteFinder.name.equals(note) && getDisplayBasis(noteFinder.alternate).equals(basis)) {
                    return noteFinder.alternate;
                }
            }

            throw new IllegalArgumentException(
                    EquivalentNoteFinder.class.getCanonicalName() + ": can't format given note "
                            + note + " in " + basis + "!"
            );
        }

        private static Note getDisplayBasis(Note note) {
            return NoteFactory.create(note.toString().substring(0, 1));
        }
    }

    private final Note rootNote;
    private final Interval interval;

    public Key(Note rootNote) {

        this.rootNote = rootNote;
        this.interval = new Interval();
    }

    public Note convertToSharpNoteOfSamePitch(Note note, Note basis) {

        return EquivalentNoteFinder.findEquivalentNoteMeetsFormat(note, basis);
    }

    public SemitoneCount semitones(Note base, Note target) {
        return this.interval.semitones(base, target);
    }

    public Note raise(Note base, SemitoneCount target) {
        return this.interval.raise(base, target);
    }

    public DegreeNumber degree(SemitoneCount semitones) {
        return this.interval.degree(semitones);
    }
}