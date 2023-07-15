package chordtransposer.cmdline.service.line;

import chordtransposer.model.Chord;
import chordtransposer.model.Line;
import java.util.List;

public interface Parser {

    List<Chord> parseLine(Line line);
}