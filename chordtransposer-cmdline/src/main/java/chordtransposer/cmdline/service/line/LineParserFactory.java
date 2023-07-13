package chordtransposer.cmdline.service.line;

import chordtransposer.chord.Transposer;

public class LineParserFactory {

    public static Parser create(Transposer transposer) {
        return new LineParser(transposer);
    }

}