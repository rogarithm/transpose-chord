package model;

public class Chord {
    private String key;
    private String bass;
    private String other;

    public Chord(String key, String chordString) {
        if (chordString.length() == 1) {
            this.key = key;
            this.bass = chordString;
        }
        else if (chordString.length() > 1) {
            this.key = key;
            this.bass = chordString.substring(0, 1);
            this.other = chordString.substring(1);
        }
    }

    public Chord transpose(String toTranspose) {

        if (this.bass == this.key) {
            this.bass = toTranspose;
            return this;
        }
        else {
            String transposedBass = getTransposedBass(this.key, this.bass);
            this.bass = transposedBass;
            return this;
        }
    }

    private String getTransposedBass(String key, String bass) {
        return "B";
    }

    public String getBass() {
        return bass;
    }
}
