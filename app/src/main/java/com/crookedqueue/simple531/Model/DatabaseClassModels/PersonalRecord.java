package com.crookedqueue.simple531.Model.DatabaseClassModels;


import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetListBuildingUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by qumbaala on 4/5/2016.
 */
public class PersonalRecord {
    private final String note;
    private final String stringLabel;
    private final int intLiftType;
    private final LiftLabel liftLabel;
    private final double weight;
    private final int reps;
    private long longDate = 0L; //gets set upon insertion into db, will need to be set when retrieved...will overload constructor


    //use this when inserting into db
    public PersonalRecord(int intLiftType, double weight, int reps, String note) {
        this.intLiftType = intLiftType;
        this.weight = weight;
        this.reps = reps;
        this.note = note;
        this.liftLabel = SetListBuildingUtils.mapIntToLiftLabel(intLiftType);
        stringLabel = SetListBuildingUtils.stringFromLabel(liftLabel);
    }

    //use this when retrieving from db
    public PersonalRecord(int intLiftType, double weight, int reps, String note, long longDate) {
        this.intLiftType = intLiftType;
        this.weight = weight;
        this.reps = reps;
        this.note = note;
        this.longDate = longDate;
        this.liftLabel = SetListBuildingUtils.mapIntToLiftLabel(intLiftType);
        stringLabel = SetListBuildingUtils.stringFromLabel(liftLabel);
    }

    public String[] getPersonalRecordAsStringArray(){
        return new String[]{getFormattedDate(), stringLabel, String.valueOf(weight), String.valueOf(reps), note};
    }

    public String getFormattedDate() {
        Date date = new Date(this.longDate);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }

    public String getStringLabel() {
        return stringLabel;
    }

    public long getLongDate() {
        return longDate;
    }

    public int getIntLiftType() {
        return intLiftType;
    }

    public String getNote() {
        return note;
    }

    public LiftLabel getLiftLabel() {
        return liftLabel;
    }

    public double getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }
}
