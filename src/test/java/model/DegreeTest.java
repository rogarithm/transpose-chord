package model;

import model.Interval.Intervals;
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
    public void getNoteForDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.getNoteOf(2)).isEqualTo("E");
    }

    @Test
    public void thirdDegreeOfDShouldFormattedWithF() {
        Note keyBefore = NoteFactory.create("G");
        Note noteToTranspose = NoteFactory.create("B");
        Note keyAfter = NoteFactory.create("D");

        Interval itv = new Interval();
        int interval = itv.getInterval(keyBefore, noteToTranspose);
        Intervals intervalName = itv.getIntervalName(interval);
        int degreeOfInterval = intervalName.getDegree();

        Degree degree = new Degree(keyAfter);
        String noteToFormat = degree.getNoteOf(degreeOfInterval);
        Assertions.assertThat(noteToFormat).isEqualTo("F");
    }
}
