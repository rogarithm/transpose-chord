package model.note;

import model.note.PlainNote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void createNoteFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PlainNote.valueOf("H"));
    }

}
