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
}
