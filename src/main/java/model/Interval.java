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
    }

    public int getNumberOfSemitonesBetween(Note base, Note target) {

        if (base.toString().endsWith("#")) {
            base = findEquivalentNoteInFlat(base);
        }

        int result = 0;
        HigherNoteFinder noteFinder = HigherNoteFinder.valueOf(base.toString());

        while (!noteFinder.name.equals(target.toString())) {
            noteFinder = HigherNoteFinder.valueOf(noteFinder.next);
            result += 1;
        }

        if (noteFinder.name.equals(target.toString())) {
            return result;
        }

        throw new IllegalArgumentException("can't find interval from " + base + " to " + target + "!");
    }

    public Note getRaisedNote(Note base, int numberOfSemitones) {

        if (base.toString().endsWith("#")) {
            base = findEquivalentNoteInFlat(base);
        }

        HigherNoteFinder noteFinder = HigherNoteFinder.valueOf(base.toString());

        while (numberOfSemitones != 0) {
            noteFinder = HigherNoteFinder.valueOf(noteFinder.next);
            numberOfSemitones -= 1;
        }

        return NoteFactory.create(noteFinder.name);
    }

    private Note findEquivalentNoteInFlat(Note note) {

        for (HigherNoteFinder nextNote : HigherNoteFinder.values()) {
            if (nextNote.alternateNext != null && nextNote.alternateNext.equals(note.toString())) {
                note = NoteFactory.create(nextNote.next);
            }
        }

        return note;
    }

    public Intervals getIntervalName(int steps) {

        for (Intervals itvName : Intervals.values()) {
            if (steps == itvName.semitonesFromRootNote)
                return itvName;
        }

        throw new IllegalArgumentException("can't find interval name for given semitones count: " + steps);
    }
}
