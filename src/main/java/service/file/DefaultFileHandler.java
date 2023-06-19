package service.file;

import java.util.List;
import model.Line;

public interface DefaultFileHandler {

    List<String> readFile();
    String getTransposedFilePath();
    void writeFile(List<Line> lines, String path);
}