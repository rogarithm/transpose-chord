package unit.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Degree;
import model.Interval;
import model.note.Note;
import model.note.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DegreeTest {

    @Test
    public void setGivenNoteAsRoot() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeNumberOf(noteD)).isEqualTo(1);
    }

    @Test
    public void setGivenNoteAsSecond() {
        Note noteD = NoteFactory.create("D");
        Note noteE = NoteFactory.create("E");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeNumberOf(noteE)).isEqualTo(2);
    }

    @Test
    public void getNoteForInvalidDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        assertThrows(IllegalArgumentException.class, () -> degree.getNoteOf(0));
    }

    @Test
    public void getNoteForDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getNoteOf(2).toString()).isEqualTo("E");
    }

    @Test
    public void thirdDegreeOfDShouldFormattedWithF() {
        Note keyBefore = NoteFactory.create("G");
        Note noteToTranspose = NoteFactory.create("B");
        Note keyAfter = NoteFactory.create("D");

        Interval itv = new Interval();
        int interval = itv.getSemitonesBetween(keyBefore, noteToTranspose);
        int degreeOfInterval = itv.getDegreeFromSemitones(interval);

        Degree degree = new Degree(keyAfter);
        Note noteToFormat = degree.getNoteOf(degreeOfInterval);
        Assertions.assertThat(noteToFormat.toString()).isEqualTo("F");
    }
}