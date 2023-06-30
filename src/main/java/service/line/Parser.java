package service.line;

import java.util.List;
import model.Chord;
import model.Line;

public interface Parser {

    List<Chord> parseLine(Line line);
}