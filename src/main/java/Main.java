import service.TransposeService;
import service.chord.Transposer;
import service.chord.TransposerFactory;
import service.line.LineParserFactory;
import service.line.Parser;
import service.util.FileHandler;
import service.util.FileHandlerFactory;

public class Main {

    public static void main(String[] args) {
        String currentKey = args[0];
        String transposeTo = args[1];
        String pathName = args[2];
        String fileName = args[3];

        FileHandler fileHandler = FileHandlerFactory.create(pathName, fileName);
        Transposer transposer = TransposerFactory.create(currentKey, transposeTo);
        Parser parser = LineParserFactory.create(transposer);

        TransposeService service = new TransposeService(parser, fileHandler);
        service.handle();
    }

}