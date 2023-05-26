package model;

import model.note.NoteFactory;
import model.note.Note;

public class Interval {

    private enum DegreeFinder {

        PERFECT_UNISON(1, 0),
        MAJOR_SECOND(2, 2),
        MAJOR_THIRD(3, 4),
        PERFECT_FOURTH(4, 5),
        PERFECT_FIFTH(5, 7),
        MAJOR_SIXTH(6, 9),
        MAJOR_SEVENTH(7, 11);

        private final int degreeNumber;
        private final int semitones;

        DegreeFinder(int degreeNumber, int semitones) {
            this.degreeNumber = degreeNumber;
            this.semitones = semitones;
        }

        public int getSemitones() {
            return semitones;
        }

        public int getDegree() {
            return degree;
        }

        static DegreeFinder findDegreeOfSemitones(int semitones) {

            for (DegreeFinder itvName : DegreeFinder.values()) {
                if (semitones == itvName.semitones)
                    return itvName;
            }

            throw new IllegalArgumentException("can't find interval name for given semitones count: " + semitones);
        }
    }

    public int getDegreeFromSemitones(int semitones) {

        DegreeFinder intervalName = DegreeFinder.findDegreeOfSemitones(semitones);
        return intervalName.degreeNumber;
    }

    private enum HigherNoteFinder {

        C("C", "Db", "C#"),
        Db("Db", "D"),
        D("D", "Eb", "D#"),
        Eb("Eb", "E"),
        E("E", "F"),
        F("F", "Gb", "F#"),
        Gb("Gb", "G"),
        G("G", "Ab", "G#"),
        Ab("Ab", "A"),
        A("A", "Bb", "A#"),
        Bb("Bb", "B"),
        B("B", "C");

        private final String name;
        private final String next;
        private String alternateNext;

        HigherNoteFinder(String name, String next) {

            this.name = name;
            this.next = next;
        }

        HigherNoteFinder(String name, String next, String alternateNext) {

            this.name = name;
            this.next = next;
            this.alternateNext = alternateNext;
        }

        static int computeSemitonesBetween(Note base, Note target) {

            String baseName = base.toString();
            String targetName = target.toString();
            HigherNoteFinder noteFinder = HigherNoteFinder.valueOf(baseName);

            int result = 0;
            while (!noteFinder.name.equals(targetName)) {
                noteFinder = HigherNoteFinder.valueOf(noteFinder.next);
                result += 1;
            }

            return result;
        }

        static String findRaisedNote(Note base, int semitones) {

            String baseName = base.toString();
            HigherNoteFinder noteFinder = HigherNoteFinder.valueOf(baseName);

            while (semitones != 0) {
                noteFinder = HigherNoteFinder.valueOf(noteFinder.next);
                semitones -= 1;
            }

            return noteFinder.name;
        }

        static Note findEquivalentNoteInFlat(Note sharpNote) {

            for (HigherNoteFinder noteFinder : HigherNoteFinder.values()) {
                if (canDisplayInSharp(noteFinder) && isSameNote(sharpNote, noteFinder.alternateNext)) {
                    sharpNote = NoteFactory.create(noteFinder.next);
                }
            }

            return sharpNote;
        }

        private static boolean isSameNote(Note note1, String note2) {
            return note1.toString().equals(note2);
        }

        private static boolean canDisplayInSharp(HigherNoteFinder noteFinder) {
            return noteFinder.alternateNext != null;
        }

    }

    public int getSemitonesBetween(Note base, Note target) {

        if (base.toString().endsWith("#")) {
            base = HigherNoteFinder.findEquivalentNoteInFlat(base);
        }

        return HigherNoteFinder.computeSemitonesBetween(base, target);
    }

    public Note getRaisedNote(Note base, int semitones) {

        if (base.toString().endsWith("#")) {
            base = HigherNoteFinder.findEquivalentNoteInFlat(base);
        }

        String raisedNoteName = HigherNoteFinder.findRaisedNote(base, semitones);

        return NoteFactory.create(raisedNoteName);
    }
}
