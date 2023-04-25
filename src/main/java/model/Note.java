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

    private String current;
    private String next;

    Note(String current, String next) {
        this.current = current;
        this.next = next;
    }

    public Note getHalfStepsUpperNote(int steps) {
        Note current = this;
        for (int i=0; i<steps; i++) {
            current = Note.valueOf(current.next);
        }
        return current;
    }

    public Note getHalfStepUpperNote() {
        return Note.valueOf(this.next);
    }
}
