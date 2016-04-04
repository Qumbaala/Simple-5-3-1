package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class ExerciseSet {
    private final String label;
    private final double rawWeight;
    private final int reps;

    public ExerciseSet(String label, double weight, int reps) {
        this.label = label;
        this.rawWeight = weight;
        this.reps = reps;
    }

    public String getLabel() {
        return label;
    }

    public int getReps() {
        return reps;
    }

    public double getRawWeight() {
        return rawWeight;
    }
}
