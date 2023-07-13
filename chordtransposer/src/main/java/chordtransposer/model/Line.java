package chordtransposer.model;

import java.util.Objects;

public class Line {

    String content;

    public Line(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(content, line.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}