package service;

import java.util.ArrayList;
import java.util.List;
import service.util.FileHandler;
import service.line.Parser;

public class TransposeService {

    private final Parser parser;
    private final FileHandler handler;

    public TransposeService(Parser parser, FileHandler handler) {
        this.parser = parser;
        this.handler = handler;
    }

    public List<String> handle() {
        List<String> lines = handler.readFile();

        List<String> result = new ArrayList<>();
        for (String line : lines) {
            List<String> transposedChords = parser.parseLine(line);
            String aLine = collectChordsIntoLine(transposedChords);
            result.add(aLine);
        }

        return result;
    }

    private String collectChordsIntoLine(List<String> parsedLine) {
        StringBuilder aLine = new StringBuilder();
        for (int i=0; i< parsedLine.size(); i++) {
            aLine.append(parsedLine.get(i));
            if (i != parsedLine.size() - 1)
                aLine.append(" ");
        }
        return aLine.toString();
    }
}