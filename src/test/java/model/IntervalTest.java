package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntervalTest {

    @Test
    public void getIntervalsInGKey() {
        Interval itv = new Interval();
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
        Interval itv = new Interval();
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
    public void getRaisedNoteInGKey() {
        Interval itv = new Interval();
        Note G = PlainNote.G;

        Note noteFsharp = itv.getRaisedNote(G, 11);
        assertThat(noteFsharp).isEqualTo(SharpNote.F);
    }
}