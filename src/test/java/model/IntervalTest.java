package model;

import static org.assertj.core.api.Assertions.assertThat;

import model.note.Note;
import model.note.NoteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntervalTest {

    private Interval itv;
    private Note Csp;
    private Note D;
    private Note Gb;
    private Note G;
    private Note Gsp;
    private Note A;
    private Note Bb;

    @BeforeEach
    public void setUp() {
        itv = new Interval();
        Csp = NoteFactory.create("C#");
        D = NoteFactory.create("D");
        Gb = NoteFactory.create("Gb");
        G = NoteFactory.create("G");
        Gsp = NoteFactory.create("G#");
        A = NoteFactory.create("A");
        Bb = NoteFactory.create("Bb");
    }

    @Test
    public void getIntervalsInGKey() {
        int itvM2 = itv.getSemitonesBetween(G, A);
        assertThat(itvM2).isEqualTo(2);

        int itvm3 = itv.getSemitonesBetween(G, Bb);
        assertThat(itvm3).isEqualTo(3);

        int itvP5 = itv.getSemitonesBetween(G, D);
        assertThat(itvP5).isEqualTo(7);

        int itvM3 = itv.getSemitonesBetween(A, Csp);
        assertThat(itvM3).isEqualTo(4);
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
    public void getDegreeFromIntervalName() {
        int degree = itv.getDegreeFromSemitones(2);
        assertThat(degree).isEqualTo(2);
    }
}