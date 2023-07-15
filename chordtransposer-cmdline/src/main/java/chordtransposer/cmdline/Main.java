package chordtransposer.cmdline;

import chordtransposer.chord.Transposer;
import chordtransposer.chord.TransposerFactory;
import chordtransposer.cmdline.service.TransposeService;
import chordtransposer.cmdline.service.file.DefaultFileHandler;
import chordtransposer.cmdline.service.file.FileHandlerFactory;
import chordtransposer.cmdline.service.line.LineParserFactory;
import chordtransposer.cmdline.service.line.Parser;
import chordtransposer.model.Line;
import java.util.List;

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