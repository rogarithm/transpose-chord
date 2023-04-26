package model;

public class Symbol {
    private String chordString;
    private String key;
    private String other;

    public Symbol(String chordString) {
        this.chordString = chordString;
    }

    public String getRootNote() {
        if (chordString.length() == 1)
            return chordString.substring(0, 1);
        else if (chordString.length() >= 2 && chordString.charAt(1) == 'b')
            return chordString.substring(0, 2);
        else if (chordString.length() >= 2)
            return chordString.substring(0, 1);
        else
            throw new IllegalArgumentException("Symbol.getRootNote(): unable to parse given chord");
    }

    public String getOther() {
        if (chordString.length() == 1)
            return "";
        else if (chordString.length() >= 2 && chordString.charAt(1) == 'b')
            return "";
        else if (chordString.length() >= 2)
            return chordString.substring(1);
        else
            throw new IllegalArgumentException("Symbol.getOther(): unable to parse given chord");
    }
}
