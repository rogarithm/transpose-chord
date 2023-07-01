package unit.model.note;

import model.note.NoteFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void createNoteFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> NoteFactory.create("H"));
    }

}