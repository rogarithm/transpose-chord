package model;

import java.util.Objects;

public class DegreeNumber {

    private int number;

    public DegreeNumber(int number) {

        if (number < 1 || number > 8) {
            throw new IllegalArgumentException(
                    this.getClass().getCanonicalName() + ": you put invalid degree number " + number
                            + "\nThe degree number should be between 1 and 8 (inclusive)."
            );
        }

        this.number = number;
    }

    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DegreeNumber that = (DegreeNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}