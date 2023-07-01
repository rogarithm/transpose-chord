package model.note;

enum FlatNote implements Note {

    C("Cb"),
    D("Db"),
    E("Eb"),
    F("Fb"),
    G("Gb"),
    A("Ab"),
    B("Bb");

    private final String name;

    FlatNote(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isFlat() {
        return true;
    }

    @Override
    public boolean isSharp() {
        return false;
    }

    @Override
    public boolean isPlain() {
        return false;
    }

    public boolean equals(Note other) {
        return this.toString().equals(other.toString());
    }
}