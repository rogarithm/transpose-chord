import model.Symbol;

public class ChordParser {

    public String getRootNote(Symbol chord) {
        return chord.getRootNote();
    }

    public String getOther(Symbol chord) {
        return chord.getOther();
    }
}
