package model.note;

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
}
