package unit.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.Degree;
import model.DegreeNumber;
import model.Interval;
import model.SemitoneCount;
import model.note.Note;
import model.note.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DegreeTest {

    @Test
    public void setGivenNoteAsRoot() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeNumberOf(noteD)).isEqualTo(new DegreeNumber(1));
    }

    @Test
    public void setGivenNoteAsSecond() {
        Note noteD = NoteFactory.create("D");
        Note noteE = NoteFactory.create("E");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getDegreeNumberOf(noteE)).isEqualTo(new DegreeNumber(2));
    }

    @Test
    public void getNoteForInvalidDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        assertThrows(IllegalArgumentException.class, () -> degree.getNoteOf(new DegreeNumber(0)));
    }

    @Test
    public void getNoteForDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getNoteOf(new DegreeNumber(2)).toString()).isEqualTo("E");
    }

    @Test
    public void thirdDegreeOfDShouldFormattedWithF() {
        Note keyBefore = NoteFactory.create("G");
        Note noteToTranspose = NoteFactory.create("B");
        Note keyAfter = NoteFactory.create("D");

        Interval itv = new Interval();
        SemitoneCount interval = itv.getSemitonesBetween(keyBefore, noteToTranspose);
        DegreeNumber degreeOfInterval = itv.degree(interval);

        Degree degree = new Degree(keyAfter);
        Note noteToFormat = degree.getNoteOf(degreeOfInterval);
        Assertions.assertThat(noteToFormat.toString()).isEqualTo("F");
    }
}