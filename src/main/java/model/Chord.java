package model;

public class Chord {
    private String chordString;

    public Chord(String chordString) {
        this.chordString = chordString;
    }

    public String getRootTone() {
        return chordString.substring(0, 1);
    }
}
