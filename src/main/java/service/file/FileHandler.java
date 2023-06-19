package service.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Line;

public class FileHandler implements DefaultFileHandler {

    private final String pathName;
    private final String fileName;

    public FileHandler(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    public List<String> readFile() {
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

    public String getTransposedFilePath() {
        String[] split = fileName.split("\\.");
        String resultFileName = split[0] + "_transposed." + split[1];
        return pathName + File.separator + resultFileName;
    }

    public void writeFile(List<Line> lines, String path) {
        File file = new File(path);

        StringBuilder result = new StringBuilder();
        for (Line line : lines) {
            result.append(line.toString()).append("\n");
        }

        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(result.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}