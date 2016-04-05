package com.crookedqueue.simple531remake.Model.DataBaseClassModels;

import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType;

/**
 * Created by qumbaala on 4/5/2016.
 */
public class PersonalRecord {
    private String note;
    private String label;
    LiftType liftType;
    private double weight;
    private int reps;
    private long date;

    public PersonalRecord() {
    }

    public PersonalRecord(String label, double weight, int reps, String note, long date) {
        this.label = label;
        this.weight = weight;
        this.reps = reps;
        this.note = note;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public String getLabel() {
        return label;
    }

    public String getNote() {
        return note;
    }

    public LiftType getLiftType() {
        return liftType;
    }

    public double getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }
}
