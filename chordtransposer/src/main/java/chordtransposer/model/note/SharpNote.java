package chordtransposer.model.note;

enum SharpNote implements Note {

    C("C#"),
    D("D#"),
    E("E#"),
    F("F#"),
    G("G#"),
    A("A#"),
    B("B#");

    private final String name;

    SharpNote(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isFlat() {
        return false;
    }

    @Override
    public boolean isSharp() {
        return true;
    }

    @Override
    public boolean isPlain() {
        return false;
    }

    public boolean equals(Note other) {
        return this.toString().equals(other.toString());
    }
}