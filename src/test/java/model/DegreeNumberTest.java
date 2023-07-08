package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DegreeNumberTest {

    @Test
    public void makeDegreeNumberFailWhenDegreeIsInvalid() {

        assertThrows(IllegalArgumentException.class, () -> new DegreeNumber(0));
    }

}