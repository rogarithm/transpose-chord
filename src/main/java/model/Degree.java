package model;

import java.util.HashMap;
import java.util.Map;

public enum Degree {
    I(0), II(2), III(4), IV(5), V(7), VI(9), VII(11);

    private final int steps;

    Degree(int steps) {
        this.steps = steps;
    }

    public int getSteps() {
        return this.steps;
    }

    public static Degree getDegree(Note note, Note currentKey) {
        Map<Degree, Note> notesInDegree = new HashMap<>();
        for (Degree d : Degree.values()) {
            notesInDegree.put(d, currentKey.getHalfStepsUpperNote(d.steps));
        }
        for (Degree d : notesInDegree.keySet()) {
            if (notesInDegree.get(d).equals(note))
                return d;
        }
        throw new IllegalArgumentException("no matching degree for given note!");
    }


}
