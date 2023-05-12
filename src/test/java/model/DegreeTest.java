package model;

import static org.junit.jupiter.api.Assertions.*;

import model.note.Note;
import model.note.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DegreeTest {

    @Test
    public void setGivenNoteAsRoot() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeOfNote(noteD)).isEqualTo(1);
    }

    @Test
    public void setGivenNoteAsSecond() {
        Note noteD = NoteFactory.create("D");
        Note noteE = NoteFactory.create("E");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeOfNote(noteE)).isEqualTo(2);
    }

}