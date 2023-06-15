package service.chord;

public class TransposerFactory {

    public static Transposer create(String currentKey, String transposeTo) {
        return new Transposer(currentKey, transposeTo);
    }

}