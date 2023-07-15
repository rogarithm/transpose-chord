package chordtransposer.model.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void createNoteFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> NoteFactory.create("H"));
    }

}