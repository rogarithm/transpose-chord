package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntervalTest {

    @Test
    public void getIntervalsInGKey() {
        Interval itv = new Interval();
        int itvM2 = itv.getInterval(Note.G, Note.A);
        assertThat(itvM2).isEqualTo(2);

        int itvm3 = itv.getInterval(Note.G, Note.Bb);
        assertThat(itvm3).isEqualTo(3);

        int itvP5 = itv.getInterval(Note.G, Note.D);
        assertThat(itvP5).isEqualTo(7);
    }

    @Test
    public void getIntervalFail() {
        Interval itv = new Interval();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> itv.getInterval(Note.G, Note.valueOf("H")));
    }

    @Test
    public void getRaisedNotes() {
        Interval itv = new Interval();
        Note noteA = itv.getRaisedNote(Note.G, 2);
        assertThat(noteA).isEqualTo(Note.A);

        Note noteBb = itv.getRaisedNote(Note.G, 3);
        assertThat(noteBb).isEqualTo(Note.Bb);

        Note noteD = itv.getRaisedNote(Note.G, 7);
        assertThat(noteD).isEqualTo(Note.D);
    }
}