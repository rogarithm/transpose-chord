package service;

import java.util.ArrayList;
import java.util.List;
import service.util.FileHandler;
import service.line.Parser;

public class TransposeService {

    private final Parser parser;
    private final FileHandler handler;

    /*
    TODO
     - parser의 transposer는 handle()에 입력된 두 키 값으로 초기화해야 한다.
     parser를 final로 두지 않고 handle에서 초기화하는 방법이 떠오르는데, 더 괜찮은 방법은 뭐가 있을까?
     - 만약 두 키 값을 TransposeService부터 Transposer까지 모두 필드로 가지면 final을 쓰지 않아도
     될 것 같다. 요청을 DTO로 가지고 있고, 각 필드값을 final로 해서 변경되지 않도록 하고, 이걸
     TransposeService부터 Transposer까지 모두 전달하는 방법은 어떨까.
     - 현재는 transposer가 유효한 키 값으로 초기화되지 않더라도 동작하는데, 이럴 경우 예외를 던지는 등
     조치를 해주는 게 나아 보인다. 최종 목표는 코드를 전조하는 거니까.
    */
    public TransposeService(Parser parser, FileHandler handler) {
        this.parser = parser;
        this.handler = handler;
    }

    public List<String> handle() {
        List<String> lines = handler.readFile();

        List<String> result = new ArrayList<>();
        for (String line : lines) {
            List<String> transposedChords = parser.parseLine(line);
            String aLine = collectChordsIntoLine(transposedChords);
            result.add(aLine);
        }

        return result;
    }

    private String collectChordsIntoLine(List<String> parsedLine) {
        StringBuilder aLine = new StringBuilder();
        for (int i=0; i< parsedLine.size(); i++) {
            aLine.append(parsedLine.get(i));
            if (i != parsedLine.size() - 1)
                aLine.append(" ");
        }
        return aLine.toString();
    }
}