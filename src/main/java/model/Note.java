package model;

public enum Note {
    C("C", "Db"),
    Db("Db", "D"),
    D("D", "Eb"),
    Eb("Eb", "E"),
    E("E", "F"),
    F("F", "Gb"),
    Gb("Gb", "G"),
    G("G", "Ab"),
    Ab("Ab", "A"),
    A("A", "Bb"),
    Bb("Bb", "B"),
    B("B", "C");

    private final String current;
    private final String next;

    Note(String current, String next) {
        this.current = current;
        this.next = next;
    }

}
