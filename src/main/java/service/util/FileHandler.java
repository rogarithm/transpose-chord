package service.util;

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

    public List<String> readFile(String pathName, String fileName) {
        File file = getFile(pathName, fileName);
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

    private File getFile(String pathName, String fileName) {
        return new File(pathName + File.separator + fileName);
    }
}