package com.crookedqueue.simple531remake.Model.DataBaseClassModels;

/**
 * Created by qumbaala on 4/5/2016.
 */
public class MaxesContainer {
    private final double squatMax, benchMax, deadliftMax, pressMax;

    public MaxesContainer(double squatMax, double benchMax, double deadliftMax, double pressMax) {
        this.squatMax = squatMax;
        this.benchMax = benchMax;
        this.deadliftMax = deadliftMax;
        this.pressMax = pressMax;
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
