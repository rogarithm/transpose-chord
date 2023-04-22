import model.Chord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChordTest {

    @Test
    public void transposeOneChord() {
        Chord gMajor = new Chord("G");
        Chord aMajor = gMajor.transpose("A");
        Assertions.assertEquals(aMajor.getBass(), "A");
    }

}
