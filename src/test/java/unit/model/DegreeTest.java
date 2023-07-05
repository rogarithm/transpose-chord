package unit.model;

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
    public void getNoteForDegree() {
        Note noteD = NoteFactory.create("D");
        Degree degree = new Degree(noteD);
        Assertions.assertThat(degree.note(new DegreeNumber(2)).toString()).isEqualTo("E");
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
        Note noteToFormat = degree.note(degreeOfInterval);
        Assertions.assertThat(noteToFormat.toString()).isEqualTo("F");
    }
}