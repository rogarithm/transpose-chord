package model;

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
        Line line = (Line) o;
        return this.content.equals(line.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}