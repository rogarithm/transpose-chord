package service.util;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.line.Parser;

@ExtendWith(MockitoExtension.class)
class FileHandlerTest {

    private String pathName = "/Users/sehun/Documents/music";
    private String fileName = "chords.txt";

    @InjectMocks
    FileHandler handler;
    @Mock
    Parser parser;

    @BeforeEach
    public void setUp() {
        handler = new FileHandler(parser);
    }

    @Test
    public void readFileOfGivenPath() {
        List<String> lines = handler.readFile(pathName, fileName);
        Assertions.assertThat(lines.size()).isEqualTo(4);
    }
}