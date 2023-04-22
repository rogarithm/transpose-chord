import model.Chord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChordTest {

    @Test
    public void transposeOneChord() {
        Chord gMajor = new Chord("G", "G");
        Chord aMajor = gMajor.transpose("A");
        Assertions.assertEquals(aMajor.getBass(), "A");
    }

    @Test
    public void transposeSubDominantChord() {
        Chord aMinor = new Chord("G", "Am");
        Chord bMinor = aMinor.transpose("A");
        Assertions.assertEquals(bMinor.getBass(), "B");
    }

}
