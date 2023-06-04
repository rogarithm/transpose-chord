package model.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void createNoteFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> PlainNote.valueOf("H"));
    }

}
