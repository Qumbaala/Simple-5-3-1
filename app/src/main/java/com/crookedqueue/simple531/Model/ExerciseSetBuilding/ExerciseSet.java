package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class ExerciseSet {
    private final String stringLabel;
    private final LiftLabel liftLabel;
    private final double roundedWeight;
    private final int intLiftLabel;
    private final int reps;

    public ExerciseSet(LiftLabel liftLabel, double roundedWeight, int reps) {
        this.liftLabel = liftLabel;
        this.stringLabel = SetListBuildingUtils.stringFromLabel(liftLabel);
        this.intLiftLabel = SetListBuildingUtils.mapLiftLabelToInt(liftLabel);
        this.roundedWeight = roundedWeight;
        this.reps = reps;
    }



    public int getIntLiftLabel() {
        return intLiftLabel;
    }

    public LiftLabel getLiftLabel() {
        return liftLabel;
    }

    public String getLabel() {
        return stringLabel;
    }

    public int getReps() {
        return reps;
    }

    public double getRoundedWeight() {
        return roundedWeight;
    }
}
