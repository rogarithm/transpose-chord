package chordtransposer.model;

import static org.assertj.core.api.Assertions.assertThat;

import chordtransposer.model.note.Note;
import chordtransposer.model.note.NoteFactory;
import org.junit.jupiter.api.Test;

public class KeyTest {

    @Test
    public void formatFlatNote() {
        Key k = new Key(NoteFactory.create("D"));
        Note formatted = k.convertToSharp(NoteFactory.create("Gb"), NoteFactory.create("F"));
        assertThat(formatted).isEqualTo(NoteFactory.create("F#"));
    }
}