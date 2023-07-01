package model;

import java.util.Objects;

public class DegreeNumber {

    private int number;

    public DegreeNumber(int number) {
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