package service.file;

import java.util.List;
import model.Line;

public interface DefaultFileHandler {

    List<Line> readFile();
    String getTransposedFilePath();
    void writeFile(List<Line> lines, String path);
}