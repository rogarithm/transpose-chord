package model;

import model.note.NoteValidator;

public class Chord {

    private final String chord;
    private static final NoteValidator validator = NoteValidator.getInstance();

    public Chord(String chord) {
        this.chord = chord;
    }

    public String getRootNote() {
        if (validator.isPlain(chord))
            return chord.substring(0, 1);
        if (validator.isFlat(chord) || validator.isSharp(chord))
            return chord.substring(0, 2);

        throw new IllegalArgumentException("unable to get root note of given chord: " + chord);
    }

    public String getChordTones() {
        if (validator.isPlain(chord))
            return chord.substring(1);
        if (validator.isFlat(chord) || validator.isSharp(chord))
            return chord.substring(2);

        throw new IllegalArgumentException("unable to get given chord: " + chord);
    }

    @Override
    public String toString() {
        return this.getRootNote() + this.getChordTones();
    }
}