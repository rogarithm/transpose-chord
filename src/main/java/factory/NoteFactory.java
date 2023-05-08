package factory;

import model.FlatNote;
import model.Note;
import model.PlainNote;
import model.SharpNote;

public class NoteFactory {

    public static Note create(String name) {
        if (isNotFlat(name) && isNotSharp(name))
            return PlainNote.valueOf(name.substring(0, 1));
        if (isFlat(name))
            return FlatNote.valueOf(name.substring(0, 2));
        if (isSharp(name))
            return SharpNote.valueOf(name.substring(0, 2));

        throw new IllegalArgumentException("Symbol.getRootNote(): unable to parse given chord");
    };

    private static boolean isNotFlat(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != 'b');
    }

    private static boolean isFlat(String s) {
        return s.length() >= 2 && s.charAt(1) == 'b';
    }

    private static boolean isNotSharp(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != '#');
    }

    private static boolean isSharp(String s) {
        return s.length() >= 2 && s.charAt(1) == '#';
    }

}
