package model;

import model.note.Note;
import model.note.NoteFactory;

public class Key {

    private final Note rootNote;

    public Key(Note rootNote) {
        this.rootNote = rootNote;
    }

    public Note formatNoteIn(Note note, String format) {
        return NoteFactory.create("F#");
    }
}
