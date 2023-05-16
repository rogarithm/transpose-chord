package model;

import static org.assertj.core.api.Assertions.*;

import model.Interval.Intervals;
import model.note.Note;
import model.note.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyTest {

    @Test
    public void formatFlatNote() {
        Key k = new Key(NoteFactory.create("D"));
        Note formatted = k.formatNoteIn(NoteFactory.create("Gb"), "F");
        assertThat(formatted).isEqualTo(NoteFactory.create("F#"));
    }
}
