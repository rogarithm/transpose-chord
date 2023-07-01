package unit.model;

import static org.assertj.core.api.Assertions.assertThat;

import model.DegreeNumber;
import model.Interval;
import model.SemitoneCount;
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
        SemitoneCount itvM2 = itv.getSemitonesBetween(G, A);
        assertThat(itvM2.count()).isEqualTo(2);

        SemitoneCount itvm3 = itv.getSemitonesBetween(G, Bb);
        assertThat(itvm3.count()).isEqualTo(3);

        SemitoneCount itvP5 = itv.getSemitonesBetween(G, D);
        assertThat(itvP5.count()).isEqualTo(7);

        SemitoneCount itvM3 = itv.getSemitonesBetween(A, Csp);
        assertThat(itvM3.count()).isEqualTo(4);
    }

    @Test
    public void getRaisedNotes() {
        Note noteA = itv.getRaisedNote(G, new SemitoneCount(2));
        assertThat(noteA).isEqualTo(A);

        Note noteBb = itv.getRaisedNote(G, new SemitoneCount(3));
        assertThat(noteBb).isEqualTo(Bb);

        Note noteD = itv.getRaisedNote(G, new SemitoneCount(7));
        assertThat(noteD).isEqualTo(D);
    }

    @Test
    public void getRaisedNoteOfFlatNote() {
        Note noteGflat = itv.getRaisedNote(G, new SemitoneCount(11));
        assertThat(noteGflat).isEqualTo(Gb);
    }

    @Test
    public void getRaisedNoteOfSharpNote() {
        Note Gsharp = Gsp;

        Note noteA = itv.getRaisedNote(Gsharp, new SemitoneCount(1));
        assertThat(noteA).isEqualTo(A);
    }

    @Test
    public void getDegreeFromIntervalName() {
        DegreeNumber degree = itv.degree(new SemitoneCount(2));
        assertThat(degree.number()).isEqualTo(2);
    }
}