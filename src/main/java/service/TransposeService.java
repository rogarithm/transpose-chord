package service;

import java.util.ArrayList;
import java.util.List;
import model.Line;
import service.file.DefaultFileHandler;
import service.line.Parser;

public class TransposeService {

    private final Parser parser;
    private final DefaultFileHandler handler;

    public TransposeService(Parser parser, DefaultFileHandler handler) {
        this.parser = parser;
        this.handler = handler;
    }

    public List<Line> handle() {
        List<String> lines = handler.readFile();

        List<Line> result = new ArrayList<>();
        for (String line : lines) {
            List<String> transposedChords = parser.parseLine(line);
            Line aLine = collectChordsIntoLine(transposedChords);
            result.add(aLine);
        }

        return result;
    }

    private Line collectChordsIntoLine(List<String> parsedLine) {
        StringBuilder aLine = new StringBuilder();
        for (int i=0; i< parsedLine.size(); i++) {
            aLine.append(parsedLine.get(i));
            if (i != parsedLine.size() - 1)
                aLine.append(" ");
        }
        return new Line(aLine.toString());
    }
}