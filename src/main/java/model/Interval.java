package model;

import model.note.Note;
import model.note.NoteFactory;

public class Interval {

    private enum DegreeFinder {

        PERFECT_UNISON(new DegreeNumber(1), new SemitoneCount(0)),
        MAJOR_SECOND  (new DegreeNumber(2), new SemitoneCount(2)),
        MAJOR_THIRD   (new DegreeNumber(3), new SemitoneCount(4)),
        PERFECT_FOURTH(new DegreeNumber(4), new SemitoneCount(5)),
        PERFECT_FIFTH (new DegreeNumber(5), new SemitoneCount(7)),
        MAJOR_SIXTH   (new DegreeNumber(6), new SemitoneCount(9)),
        MAJOR_SEVENTH (new DegreeNumber(7), new SemitoneCount(11));

        private final DegreeNumber degreeNumber;
        private final SemitoneCount semitones;

        DegreeFinder(DegreeNumber degreeNumber, SemitoneCount semitones) {
            this.degreeNumber = degreeNumber;
            this.semitones = semitones;
        }

        static DegreeFinder from(SemitoneCount semitones) {

            for (DegreeFinder itvName : DegreeFinder.values()) {
                if (semitones.count() == itvName.semitones.count())
                    return itvName;
            }

            throw new IllegalArgumentException(DegreeFinder.class.getCanonicalName() + ": can't find interval name for given semitones count " + semitones);
        }
    }

    public DegreeNumber degree(SemitoneCount semitones) {

        DegreeFinder intervalName = DegreeFinder.from(semitones);
        return new DegreeNumber(intervalName.degreeNumber.number());
    }

    private enum NoteAscender {

        C (NoteFactory.create("C"),  NoteFactory.create("Db"), NoteFactory.create("C#")),
        Db(NoteFactory.create("Db"), NoteFactory.create("D")),
        D (NoteFactory.create("D"),  NoteFactory.create("Eb"), NoteFactory.create("D#")),
        Eb(NoteFactory.create("Eb"), NoteFactory.create("E")),
        E (NoteFactory.create("E"),  NoteFactory.create("F")),
        F (NoteFactory.create("F"),  NoteFactory.create("Gb"), NoteFactory.create("F#")),
        Gb(NoteFactory.create("Gb"), NoteFactory.create("G")),
        G (NoteFactory.create("G"),  NoteFactory.create("Ab"), NoteFactory.create("G#")),
        Ab(NoteFactory.create("Ab"), NoteFactory.create("A")),
        A (NoteFactory.create("A"),  NoteFactory.create("Bb"), NoteFactory.create("A#")),
        Bb(NoteFactory.create("Bb"), NoteFactory.create("B")),
        B (NoteFactory.create("B"),  NoteFactory.create("C"));

        private final Note name;
        private final Note next;
        private Note alternateNext;

        NoteAscender(Note name, Note next) {

            this.name = name;
            this.next = next;
        }

        NoteAscender(Note name, Note next, Note alternateNext) {

            this.name = name;
            this.next = next;
            this.alternateNext = alternateNext;
        }

        static NoteAscender from(Note note) {
            return NoteAscender.valueOf(note.toString());
        }

        static SemitoneCount computeSemitonesBetween(Note base, Note target) {

            NoteAscender noteFinder = NoteAscender.from(base);

            int result = 0;
            while (!noteFinder.name.equals(target)) {
                noteFinder = NoteAscender.from(noteFinder.next);
                result += 1;
            }

            return new SemitoneCount(result);
        }

        static Note findRaisedNote(Note base, SemitoneCount semitones) {

            NoteAscender noteFinder = NoteAscender.from(base);
            int count = semitones.count();

            while (count != 0) {
                noteFinder = NoteAscender.from(noteFinder.next);
                count -= 1;
            }

            return noteFinder.name;
        }

        static Note findEquivalentFlatNote(Note sharpNote) {

            for (NoteAscender noteFinder : NoteAscender.values()) {
                if (canDisplayInSharp(noteFinder) && sharpNote.equals(noteFinder.alternateNext)) {
                    sharpNote = noteFinder.next;
                }
            }

            return sharpNote;
        }

        private static boolean canDisplayInSharp(NoteAscender noteFinder) {
            return noteFinder.alternateNext != null;
        }
    }

    public SemitoneCount getSemitonesBetween(Note base, Note target) {

        if (base.isSharp()) {
            base = NoteAscender.findEquivalentFlatNote(base);
        }

        if (target.isSharp()) {
            target = NoteAscender.findEquivalentFlatNote(target);
        }

        return NoteAscender.computeSemitonesBetween(base, target);
    }

    public Note getRaisedNote(Note base, SemitoneCount semitones) {

        if (base.isSharp()) {
            base = NoteAscender.findEquivalentFlatNote(base);
        }

        Note raisedNoteName = NoteAscender.findRaisedNote(base, semitones);

        return raisedNoteName;
    }
}