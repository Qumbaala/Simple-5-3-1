package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;

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

    //using the dbHelper is preferred, but is android framework dependent
    //pass null for liftLabel and workingSetType when building files
    public ParamsBundle(LiftLabel liftLabel, SetType workingSetType, DbHelper dbHelper){
        this.liftLabel = liftLabel;
        this.workingSetType = workingSetType;
        //these four values are properties of settings
        this.assistanceSetType = dbHelper.retrieveAssistanceWork();
        this.isUseKg = dbHelper.retrieveIsUseKg();
        this.isRoundUp = dbHelper.retrieveIsUseRoundUp();
        this.isUseFsl = dbHelper.retrieveIsUseFsl();
        //must null check before searching db
        this.maxWeight = liftLabel != null ? dbHelper.retrieveCurrentMaxes().getMaxFromLiftLabel(liftLabel):0;
    }

    //factory for getting settings only bundle for building files
    public static ParamsBundle getSettingsBundle(DbHelper dbHelper){
        //return new params bundle with first two values as null
        return new ParamsBundle(null, null, dbHelper);
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
