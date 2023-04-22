package model;

public class Chord {
    private String bass;

    public Chord(String bass) {
        this.bass = bass;
    }

    public Chord transpose(String otherBass) {
        this.bass = otherBass;
        return this;
    }

    public String getBass() {
        return bass;
    }
}
