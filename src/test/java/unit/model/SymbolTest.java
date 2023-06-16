package unit.model;

import static org.assertj.core.api.Assertions.assertThat;

import model.Symbol;
import org.junit.jupiter.api.Test;

class SymbolTest {

    @Test
    public void getRootOfMajorChord() {
        Symbol gMajor = new Symbol("G");
        String rootTone = gMajor.getRootNote();
        assertThat(rootTone).isEqualTo("G");
    }

    @Test
    public void getRootOfSharpMajorChord() {
        Symbol gSharpMajor = new Symbol("G#");
        String rootTone = gSharpMajor.getRootNote();
        assertThat(rootTone).isEqualTo("G#");
    }

    @Test
    public void getRootOfFlatMajorChord() {
        Symbol gFlatMajor = new Symbol("Gb");
        String rootTone = gFlatMajor.getRootNote();
        assertThat(rootTone).isEqualTo("Gb");
    }

    @Test
    public void getRootOfSharpMinorChord() {
        Symbol aSharpMinor = new Symbol("A#m");
        String rootTone = aSharpMinor.getRootNote();
        assertThat(rootTone).isEqualTo("A#");
    }

    @Test
    public void getRootOfMinorChord() {
        Symbol aMinor = new Symbol("Am");
        String rootTone = aMinor.getRootNote();
        assertThat(rootTone).isEqualTo("A");
    }

    @Test
    public void getOtherOfMajorChord() {
        Symbol gMajor = new Symbol("G");
        String other = gMajor.getChordTones();
        assertThat(other).isEqualTo("");
    }

    @Test
    public void getOtherOfFlatMajorChord() {
        Symbol gFlatMajor = new Symbol("Gb");
        String other = gFlatMajor.getChordTones();
        assertThat(other).isEqualTo("");
    }

    @Test
    public void getOtherOfMinorChord() {
        Symbol aMinor = new Symbol("Am");
        String other = aMinor.getChordTones();
        assertThat(other).isEqualTo("m");
    }

    @Test
    public void constructSymbolOfSharpChord() {
        Symbol symbol = new Symbol("C#m7");
        assertThat(symbol.toString()).isEqualTo("C#m7");
    }
}