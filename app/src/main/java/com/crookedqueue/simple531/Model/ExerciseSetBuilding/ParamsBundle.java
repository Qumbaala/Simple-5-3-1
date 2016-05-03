package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

/**
 * This class is going to bundle all the parameters we need to pass off to the SetListBuilder(s) in order to construct our sets.
 * Will contain params for LiftLabel of Workingset and assistance, Max Weight, Units, Rounding Rule, and FSL
 *
 */
public class ParamsBundle{
    protected final LiftLabel liftLabel;
    protected final SetType workingSetType, assistanceSetType;
    protected final boolean isUseKg, isRoundUp, isUseFsl;
    protected final double maxWeight;

    //used when all values are being assigned in mass
    public ParamsBundle(LiftLabel liftLabel, SetType workingSetType, SetType assistanceSetType, boolean isUseKg, boolean isRoundUp, boolean isUseFsl, double maxWeight) {
        //these two are properties of the button click in cycle day
        this.liftLabel = liftLabel;
        this.workingSetType = workingSetType;
        //these four values are properties of settings
        this.assistanceSetType = assistanceSetType;
        this.isUseKg = isUseKg;
        this.isRoundUp = isRoundUp;
        this.isUseFsl = isUseFsl;
        //this value is from the db, the max of the given lift we're operating on
        this.maxWeight = maxWeight;
    }




    public LiftLabel getLiftLabel() {
        return liftLabel;
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
