package model;

public class Symbol {
    private String chordString;
    private String key;
    private String other;

    public Symbol(String chordString) {
        this.chordString = chordString;
    }

    public String getRootNote() {
        if (isNotFlat(chordString))
            return chordString.substring(0, 1);
        if (isFlat(chordString))
            return chordString.substring(0, 2);

        throw new IllegalArgumentException("Symbol.getRootNote(): unable to parse given chord");
    }

    private boolean isNotFlat(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != 'b');
    }

    private boolean isFlat(String s) {
        return s.length() >= 2 && s.charAt(1) == 'b';
    }

    public String getOther() {
        if (isNotFlat(chordString))
            return chordString.substring(1);
        if (isFlat(chordString))
            return chordString.substring(2);
        else
            throw new IllegalArgumentException("Symbol.getOther(): unable to parse given chord");
    }
}
