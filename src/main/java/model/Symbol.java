package model;

public class Symbol {

    private final String chord;

    public Symbol(String chord) {
        this.chord = chord;
    }

    public String getRootNote() {
        if (isNotFlat(chord) && isNotSharp(chord))
            return chord.substring(0, 1);
        if (isFlat(chord) || isSharp(chord))
            return chord.substring(0, 2);

        throw new IllegalArgumentException("unable to get root note of given chord: " + chord);
    }

    private boolean isNotFlat(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != 'b');
    }

    private boolean isFlat(String s) {
        return s.length() >= 2 && s.charAt(1) == 'b';
    }

    private boolean isNotSharp(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != '#');
    }

    private boolean isSharp(String s) {
        return s.length() >= 2 && s.charAt(1) == '#';
    }

    public String getChordTones() {
        if (isNotFlat(chord))
            return chord.substring(1);
        if (isFlat(chord))
            return chord.substring(2);

        throw new IllegalArgumentException("unable to get given chord: " + chord);
    }
}
