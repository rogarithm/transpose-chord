package model;

public class Chord {
    private String chordString;

    public Chord(String chordString) {
        this.chordString = chordString;
    }

    public String getRootTone() {
        if (chordString.length() == 1)
            return chordString.substring(0, 1);
        else if (chordString.length() == 2 && chordString.charAt(1) == 'm')
            return chordString.substring(0, 1);
        else
            throw new IllegalArgumentException("unable to parse given chord");
    }
}
