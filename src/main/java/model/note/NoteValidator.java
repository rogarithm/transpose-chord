package model.note;

import java.util.Arrays;
import java.util.List;

public class NoteValidator {

    private static final NoteValidator INSTANCE = new NoteValidator();
    private static final List<String> NOTES = Arrays.asList("C", "D", "E", "F", "G", "A", "B");

    public static final NoteValidator getInstance() {
        return INSTANCE;
    }

    public boolean isValidNote(String s) {
        for (String note : NOTES) {
            if (s.charAt(0) == note.charAt(0)) {
                return true;
            }
        }

        return false;
    }

    /*
     * TODO
     *  Note의 유효성 검증과 Symbol의 유효성 검증을 동시에 처리하고 있는 점이 걸린다
     *  둘을 나눌 수 있는 클래스 관계와 패키지 구조 고민이 필요하다
     *  NoteValidator에 정의한 메서드 입력 타입이 모두 String인 점이 걸린다
     *  Note 유효성 검증이라면 Note 객체를 입력 타입으로 하고, Symbol 유효성 검증이라면 Symbol 객체를 입력 타입으로 하는 게 더 낫지 않을까?
     */
    public boolean isFlat(String s) {
        return s.length() >= 2
                && this.isValidNote(s)
                && s.charAt(1) == 'b';
    }

    public boolean isSharp(String s) {
        return s.length() >= 2
                && this.isValidNote(s)
                && s.charAt(1) == '#';
    }

    public boolean isNotFlat(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != 'b');
    }

    public boolean isNotSharp(String s) {
        return (s.length() == 1) || (s.length() >= 2 && s.charAt(1) != '#');
    }
}
