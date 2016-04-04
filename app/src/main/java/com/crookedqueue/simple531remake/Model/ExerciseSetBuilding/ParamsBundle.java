package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

/**
 * This class is going to bundle all the parameters we need to pass off to the SetListBuilder(s) in order to construct our sets.
 * Will contain params for LiftType of Workingset and assistance, Max Weight, Units, Rounding Rule, and FSL
 */
public class ParamsBundle {
    protected final LiftType liftType;
    protected final SetType workingSetType, assistanceSetType;
    protected final boolean isUseKg, isRoundUp, isUseFsl;
    protected final double maxWeight;

    public ParamsBundle(LiftType liftType, SetType workingSetType, SetType assistanceSetType, boolean isUseKg, boolean isRoundUp, boolean isUseFsl, double maxWeight) {
        this.liftType = liftType;
        this.workingSetType = workingSetType;
        this.assistanceSetType = assistanceSetType;
        this.isUseKg = isUseKg;
        this.isRoundUp = isRoundUp;
        this.isUseFsl = isUseFsl;
        this.maxWeight = maxWeight;
    }

    public LiftType getLiftType() {
        return liftType;
    }
    public SetType getWorkingSetType() {
        return workingSetType;
    }

    public SetType getAssistanceSetType() {
        return assistanceSetType;
    }

    public boolean isUseKg() {
        return isUseKg;
    }

    public boolean isRoundUp() {
        return isRoundUp;
    }

    public boolean isUseFsl() {
        return isUseFsl;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
