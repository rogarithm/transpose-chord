package model;

import java.util.Objects;

public class SemitoneCount {

    private int count;

    public SemitoneCount(int count) {
        this.count = count;
    }

    public int count() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SemitoneCount that = (SemitoneCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}