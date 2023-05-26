package model.note;

public class NoteFactory {

    private static final NoteValidator validator = NoteValidator.getInstance();

    public static Note create(String name) {
        if (validator.isNotFlat(name) && validator.isNotSharp(name)) {
            return PlainNote.valueOf(name.substring(0, 1));
        }
        if (validator.isFlat(name)) {
            return FlatNote.valueOf(name.substring(0, 1));
        }
        if (validator.isSharp(name)) {
            return SharpNote.valueOf(name.substring(0, 1));
        }

        throw new IllegalArgumentException("unable to to make Note from given chord " + name);
    }
}
