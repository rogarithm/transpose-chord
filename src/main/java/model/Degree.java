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
        Map<Degree, Note> notesInKey = new HashMap<>();
        for (Degree degree : Degree.values()) {
            notesInKey.put(degree, currentKey.getHalfStepsUpperNote(degree.steps));
        }
        for (Degree degree : notesInKey.keySet()) {
            if (notesInKey.get(degree).equals(note))
                return degree;
        }
        throw new IllegalArgumentException("no matching degree for given note!");
    }


}
