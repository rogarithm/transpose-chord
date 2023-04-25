package model;

import static org.junit.jupiter.api.Assertions.*;

import model.Degree;
import model.Note;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DegreeTest {

    @Test
    public void degreeOfRootChord() {
        Note root = Note.G;
        Note currentKey = Note.G;
        Degree degree = Degree.getDegree(root, currentKey);
        Assertions.assertThat(degree).isEqualTo(Degree.I);
    }

    @Test
    public void degreeOfSecondChord() {
        Note root = Note.A;
        Note currentKey = Note.G;
        Degree degree = Degree.getDegree(root, currentKey);
        Assertions.assertThat(degree).isEqualTo(Degree.II);
    }

    @Test
    public void degreeOfNonExistingChord() {
        Note root = Note.Ab;
        Note currentKey = Note.G;
        assertThrows(IllegalArgumentException.class, () -> Degree.getDegree(root, currentKey));
    }
}