package service.file;

import java.util.List;

public interface DefaultFileHandler {

    List<String> readFile();
    String getTransposedFilePath();
    void writeFile(List<String> lines, String path);
}