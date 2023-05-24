package model;

import static org.assertj.core.api.Assertions.assertThat;

import model.Interval.Intervals;
import model.note.Note;
import model.note.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntervalTest {

    private Interval itv;
    private Note G;
    private Note Gb;
    private Note A;
    private Note Bb;
    private Note D;
    private Note Gsp;

    @BeforeEach
    public void setUp() {
        itv = new Interval();
        G = NoteFactory.create("G");
        A = NoteFactory.create("A");
        Bb = NoteFactory.create("Bb");
        D = NoteFactory.create("D");
        Gb = NoteFactory.create("Gb");
        Gsp = NoteFactory.create("G#");
    }

    @Test
    public void getIntervalsInGKey() {
        int itvM2 = itv.getNumberOfSemitonesBetween(G, A);
        assertThat(itvM2).isEqualTo(2);

        int itvm3 = itv.getNumberOfSemitonesBetween(G, Bb);
        assertThat(itvm3).isEqualTo(3);

        int itvP5 = itv.getNumberOfSemitonesBetween(G, D);
        assertThat(itvP5).isEqualTo(7);
    }

    @Test
    public void getRaisedNotes() {
        Note noteA = itv.getRaisedNote(G, 2);
        assertThat(noteA).isEqualTo(A);

        Note noteBb = itv.getRaisedNote(G, 3);
        assertThat(noteBb).isEqualTo(Bb);

        Note noteD = itv.getRaisedNote(G, 7);
        assertThat(noteD).isEqualTo(D);
    }

    @Test
    public void getRaisedNoteOfFlatNote() {
        Note noteGflat = itv.getRaisedNote(G, 11);
        assertThat(noteGflat).isEqualTo(Gb);
    }

    @Test
    public void getRaisedNoteOfSharpNote() {
        Note Gsharp = Gsp;

        Note noteA = itv.getRaisedNote(Gsharp, 1);
        assertThat(noteA).isEqualTo(A);
    }

    @Test
    public void getIntervalNameFromIntervalSteps() {
        Intervals majorSecond = Intervals.findDegreeNumberOfGivenSemitoneCount(2);
        Assertions.assertThat(majorSecond).isEqualTo(Intervals.MAJOR_SECOND);
    }

    @Test
    public void getDegreeFromIntervalName() {
        int degree = itv.getDegreeNumberFromSemitoneCount(2);
        assertThat(degree).isEqualTo(2);
    }
}