package model;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NoteTest {

    @Test
    public void getHalfStepUpperNote() {
        Note c = Note.C;
        Note dFlat = c.getHalfStepUpperNote();
        Assertions.assertThat(dFlat).isEqualTo(Note.Db);
    }

    @Test
    public void getWholeStepUpperNote() {
        Note c = Note.C;
        Note d = c.getHalfStepsUpperNote(2);
        Assertions.assertThat(d).isEqualTo(Note.D);
    }
}