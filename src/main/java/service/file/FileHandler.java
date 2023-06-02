package service.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import service.line.Parser;

public class FileHandler {

    private final Parser parser;

    public FileHandler(Parser parser) {
        this.parser = parser;
    }

    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                result.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<String> handle(List<String> lines) {
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