package service.line;

import service.chord.Transposer;

public class LineParserFactory {

    public static Parser create(Transposer transposer) {
        return new LineParser(transposer);
    }

}