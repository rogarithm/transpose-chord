package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolTest {

    @Test
    public void getRootOfMajorChord() {
        Symbol gMajor = new Symbol("G");
        String rootTone = gMajor.getRootNote();
        assertThat(rootTone).isEqualTo("G");
    }

    @Test
    public void getRootOfFlatMajorChord() {
        Symbol gFlatMajor = new Symbol("Gb");
        String rootTone = gFlatMajor.getRootNote();
        assertThat(rootTone).isEqualTo("Gb");
    }

    @Test
    public void getRootOfMinorChord() {
        Symbol aMinor = new Symbol("Am");
        String rootTone = aMinor.getRootNote();
        assertThat(rootTone).isEqualTo("A");
    }
}