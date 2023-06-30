package service.line;

import java.util.List;
import model.Line;

public interface Parser {

    List<String> parseLine(Line line);
}