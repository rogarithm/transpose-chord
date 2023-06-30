package model.note;

enum PlainNote implements Note {

    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    A("A"),
    B("B");

    private final String name;

    PlainNote(String name) {
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
        return false;
    }

    @Override
    public boolean isPlain() {
        return true;
    }
}