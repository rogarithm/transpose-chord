package model;

public class Line {

    String content;

    public Line(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}