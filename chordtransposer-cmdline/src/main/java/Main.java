import chord.Transposer;
import chord.TransposerFactory;
import java.util.List;
import model.Line;
import service.TransposeService;
import service.file.DefaultFileHandler;
import service.file.FileHandlerFactory;
import service.line.LineParserFactory;
import service.line.Parser;

public class Main {

    public static void main(String[] args) {
        String currentKey = args[0];
        String transposeTo = args[1];
        String pathName = args[2];
        String fileName = args[3];

        DefaultFileHandler fileHandler = FileHandlerFactory.create(pathName, fileName);
        Transposer transposer = TransposerFactory.create(currentKey, transposeTo);
        Parser parser = LineParserFactory.create(transposer);

        TransposeService service = new TransposeService(parser, fileHandler);
        List<Line> result = service.handle();
        fileHandler.writeFile(result, fileHandler.getTransposedFilePath());
    }
}