package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by Jason on 11/30/2015.
 */
public class ImperialRoundUp implements Roundable {
    public ImperialRoundUp() {
    }

    @Override
    public double performCalc(double weight) {
        return (5 * (Math.ceil(Math.abs(weight / 5))));
    }
}
