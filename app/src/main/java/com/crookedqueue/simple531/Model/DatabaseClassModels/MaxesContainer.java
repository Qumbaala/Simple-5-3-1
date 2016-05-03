package com.crookedqueue.simple531.Model.DatabaseClassModels;

import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;

/**
 * Created by qumbaala on 4/5/2016.
 */
public class MaxesContainer {
    private final double squatMax, benchMax, deadliftMax, overheadPressMax;

    public MaxesContainer(double squatMax, double benchMax, double deadliftMax, double overheadPressMax) {
        this.squatMax = squatMax;
        this.benchMax = benchMax;
        this.deadliftMax = deadliftMax;
        this.overheadPressMax = overheadPressMax;
    }

    public double getMaxFromLiftLabel(LiftLabel label){
        switch (label){
            case SQUAT:
                return squatMax;
            case BENCH_PRESS:
                return benchMax;
            case DEADLIFT:
                return deadliftMax;
            case OVERHEAD_PRESS:
                return overheadPressMax;
            default:
                return 0.0;
        }
    }

    public double getDeadliftMax() {
        return deadliftMax;
    }

    public double getSquatMax() {
        return squatMax;
    }

    public double getOverheadPressMax() {
        return overheadPressMax;
    }

    public double getBenchMax() {
        return benchMax;
    }
}
