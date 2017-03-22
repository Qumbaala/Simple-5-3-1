package com.crookedqueue.simple531.Model.DatabaseClassModels;

import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qumbaala on 4/5/2016.
 */
public class MaxesContainer {
    private final double squatMax, benchMax, deadliftMax, pressMax;
    private long longDate;

    public MaxesContainer(double squatMax, double benchMax, double deadliftMax, double pressMax) {
        this.squatMax = squatMax;
        this.pressMax = pressMax;
        this.deadliftMax = deadliftMax;
        this.benchMax = benchMax;
    }

    public MaxesContainer(double squatMax, double benchMax, double deadliftMax, double overheadPressMax, long longDate) {
        this.squatMax = squatMax;
        this.benchMax = benchMax;
        this.deadliftMax = deadliftMax;
        this.pressMax = overheadPressMax;
        this.longDate = longDate;
    }

    public double getMaxFromLiftLabel(LiftLabel label){
        switch (label){
            case SQUAT:
                return squatMax;
            case BENCH_PRESS:
                return benchMax;
            case DEADLIFT:
                return deadliftMax;
            case PRESS:
                return pressMax;
            default:
                return 0.0;
        }
    }

    public String[] getMaxContainerStringArray(){
        return new String[]{getFormattedDate(), String.valueOf(squatMax), String.valueOf(benchMax), String.valueOf(deadliftMax), String.valueOf(pressMax)};
    }

    public String getFormattedDate(){
        Date date = new Date(this.longDate);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(date);
    }

    public long getLongDate() {
        return longDate;
    }

    public double getDeadliftMax() {
        return deadliftMax;
    }

    public double getSquatMax() {
        return squatMax;
    }

    public double getPressMax() {
        return pressMax;
    }

    public double getBenchMax() {
        return benchMax;
    }
}
