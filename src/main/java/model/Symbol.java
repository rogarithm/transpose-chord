package model;

public class Symbol {
    private String chordString;

    public Symbol(String chordString) {
        this.chordString = chordString;
    }

    public String getRootNote() {
        if (chordString.length() == 1)
            return chordString.substring(0, 1);
        else if (chordString.length() == 2 && chordString.charAt(1) == 'b')
            return chordString.substring(0, 2);
        else if (chordString.length() == 2 && chordString.charAt(1) == 'm')
            return chordString.substring(0, 1);
        else
            throw new IllegalArgumentException("unable to parse given chord");
    }
}
