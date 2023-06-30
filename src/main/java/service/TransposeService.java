package service;

import java.util.List;
import java.util.stream.Collectors;
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
        List<Line> lines = handler.readFile();

        return lines.stream()
                    .map(parser::parseLine)
                    .map(this::collectChordsIntoLine)
                    .collect(Collectors.toList());
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