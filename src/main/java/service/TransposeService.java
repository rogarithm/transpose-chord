package service;

import java.util.ArrayList;
import java.util.List;
import service.util.FileHandler;
import service.line.Parser;

public class TransposeService {

    private final Parser parser;
    private final FileHandler handler;

    public TransposeService(FileHandler handler, Parser parser) {
        this.parser = parser;
        this.handler = handler;
    }

    public List<String> handle(String pathName, String fileName, String currentKey, String transposeKey) {
        List<String> lines = handler.readFile(pathName, fileName);
        List<String> result = new ArrayList<>();

        for (String line : lines) {
            StringBuilder oneLine = new StringBuilder();
            List<String> parsedLine = parser.parseLine(line);

            for (int i=0; i<parsedLine.size(); i++) {
                oneLine.append(parsedLine.get(i));
                if (i != parsedLine.size() - 1)
                    oneLine.append(" ");
            }
            result.add(oneLine.toString());
        }

        return result;
    }
}