package unit.model;

import static org.assertj.core.api.Assertions.assertThat;

import model.Chord;
import org.junit.jupiter.api.Test;

class ChordTest {

    @Test
    public void getRootOfMajorChord() {
        Chord gMajor = new Chord("G");
        String rootTone = gMajor.getRootNote();
        assertThat(rootTone).isEqualTo("G");
    }

    @Test
    public void getRootOfSharpMajorChord() {
        Chord gSharpMajor = new Chord("G#");
        String rootTone = gSharpMajor.getRootNote();
        assertThat(rootTone).isEqualTo("G#");
    }

    @Test
    public void getRootOfFlatMajorChord() {
        Chord gFlatMajor = new Chord("Gb");
        String rootTone = gFlatMajor.getRootNote();
        assertThat(rootTone).isEqualTo("Gb");
    }

    @Test
    public void getRootOfSharpMinorChord() {
        Chord aSharpMinor = new Chord("A#m");
        String rootTone = aSharpMinor.getRootNote();
        assertThat(rootTone).isEqualTo("A#");
    }

    @Test
    public void getRootOfMinorChord() {
        Chord aMinor = new Chord("Am");
        String rootTone = aMinor.getRootNote();
        assertThat(rootTone).isEqualTo("A");
    }

    @Test
    public void getOtherOfMajorChord() {
        Chord gMajor = new Chord("G");
        String other = gMajor.getChordTones();
        assertThat(other).isEqualTo("");
    }

    @Test
    public void getOtherOfFlatMajorChord() {
        Chord gFlatMajor = new Chord("Gb");
        String other = gFlatMajor.getChordTones();
        assertThat(other).isEqualTo("");
    }

    @Test
    public void getOtherOfMinorChord() {
        Chord aMinor = new Chord("Am");
        String other = aMinor.getChordTones();
        assertThat(other).isEqualTo("m");
    }

    @Test
    public void constructSymbolOfSharpChord() {
        Chord chord = new Chord("C#m7");
        assertThat(chord.toString()).isEqualTo("C#m7");
    }
}