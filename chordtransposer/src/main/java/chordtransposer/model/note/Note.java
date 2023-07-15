package chordtransposer.model.note;

public interface Note {

    boolean isFlat();

    boolean isSharp();

    boolean isPlain();

    boolean equals(Note other);
}