package model;

import model.note.NoteFactory;
import model.note.Note;

public class Interval {

    enum Intervals {
        MAJOR_SECOND(2, 2),
        MAJOR_THIRD(3, 4),
        MAJOR_FOURTH(4, 5),
        MAJOR_FIFTH(5, 7),
        MAJOR_SIXTH(6, 9),
        MAJOR_SEVENTH(7, 11);

        private final int degreeNumber;
        private final int semitonesFromRootNote;

        Intervals(int degreeNumber, int semitonesFromRootNote) {
            this.degreeNumber = degreeNumber;
            this.semitonesFromRootNote = semitonesFromRootNote;
        }

        public int getSemitonesFromRootNote() {
            return semitonesFromRootNote;
        }

        public int getDegreeNumber() {
            return degreeNumber;
        }
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

        static String findRaisedNote(Note base, int numberOfSemitones) {

            String baseName = base.toString();
            HigherNoteFinder noteFinder = HigherNoteFinder.valueOf(baseName);

            while (numberOfSemitones != 0) {
                noteFinder = HigherNoteFinder.valueOf(noteFinder.next);
                numberOfSemitones -= 1;
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

    public int getNumberOfSemitonesBetween(Note base, Note target) {

        if (base.toString().endsWith("#")) {
            base = HigherNoteFinder.findEquivalentNoteInFlat(base);
        }

        return HigherNoteFinder.computeSemitonesBetween(base, target);
    }

    public Note getRaisedNote(Note base, int numberOfSemitones) {

        if (base.toString().endsWith("#")) {
            base = HigherNoteFinder.findEquivalentNoteInFlat(base);
        }

        String raisedNoteName = HigherNoteFinder.findRaisedNote(base, numberOfSemitones);

        return NoteFactory.create(raisedNoteName);
    }

    public Intervals getIntervalName(int steps) {

        for (Intervals itvName : Intervals.values()) {
            if (steps == itvName.semitonesFromRootNote)
                return itvName;
        }

        throw new IllegalArgumentException("can't find interval name for given semitones count: " + steps);
    }
}
