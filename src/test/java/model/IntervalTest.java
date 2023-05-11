package model;

import static org.assertj.core.api.Assertions.assertThat;

import model.Interval.Intervals;
import model.note.FlatNote;
import model.note.Note;
import model.note.PlainNote;
import model.note.SharpNote;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntervalTest {

    private Interval itv;

    @BeforeEach
    public void setUp() {
        itv = new Interval();
    }
    @Test
    public void getIntervalsInGKey() {
        Note G = PlainNote.G;
        Note A = PlainNote.A;
        Note Bb = FlatNote.B;
        Note D = PlainNote.D;

        int itvM2 = itv.getInterval(G, A);
        assertThat(itvM2).isEqualTo(2);

        int itvm3 = itv.getInterval(G, Bb);
        assertThat(itvm3).isEqualTo(3);

        int itvP5 = itv.getInterval(G, D);
        assertThat(itvP5).isEqualTo(7);
    }

    @Test
    public void getRaisedNotes() {
        Note G = PlainNote.G;
        Note A = PlainNote.A;
        Note Bb = FlatNote.B;
        Note D = PlainNote.D;

        Note noteA = itv.getRaisedNote(G, 2);
        assertThat(noteA).isEqualTo(A);

        Note noteBb = itv.getRaisedNote(G, 3);
        assertThat(noteBb).isEqualTo(Bb);

        Note noteD = itv.getRaisedNote(G, 7);
        assertThat(noteD).isEqualTo(D);
    }

    @Test
    public void getRaisedNoteOfFlatNote() {
        Note G = PlainNote.G;

        Note noteGflat = itv.getRaisedNote(G, 11);
        assertThat(noteGflat).isEqualTo(FlatNote.G);
    }

    @Test
    public void getRaisedNoteOfSharpNote() {
        Note Gsharp = SharpNote.G;

        Note noteA = itv.getRaisedNote(Gsharp, 1);
        assertThat(noteA).isEqualTo(PlainNote.A);
    }

    @Test
    public void getIntervalNameFromIntervalSteps() {
        Intervals majorSecond = itv.getIntervalName(2);
        Assertions.assertThat(majorSecond).isEqualTo(Intervals.MAJOR_SECOND);
    }
}