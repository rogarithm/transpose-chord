package chordtransposer.cmdline.service.file;

import chordtransposer.model.Line;
import java.util.List;

public interface DefaultFileHandler {

    List<Line> readFile();
    String getTransposedFilePath();
    void writeFile(List<Line> lines, String path);
}