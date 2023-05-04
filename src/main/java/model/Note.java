package model;

public enum Note {
    C("C"),
    Db("Db"),
    D("D"),
    Eb("Eb"),
    E("E"),
    F("F"),
    Gb("Gb"),
    G("G"),
    Ab("Ab"),
    A("A"),
    Bb("Bb"),
    B("B");

    private final String current;

    Note(String current) {
        this.current = current;
    }

}
