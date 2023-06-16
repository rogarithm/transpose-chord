package unit.model;

import static org.assertj.core.api.Assertions.*;

import model.Key;
import model.note.Note;
import model.note.NoteFactory;
import org.junit.jupiter.api.Test;

public class KeyTest {

    @Test
    public void formatFlatNote() {
        Key k = new Key(NoteFactory.create("D"));
        Note formatted = k.convertToSharpNoteOfSamePitch(NoteFactory.create("Gb"), "F");
        assertThat(formatted).isEqualTo(NoteFactory.create("F#"));
    }
}
