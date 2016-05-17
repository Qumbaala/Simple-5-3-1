package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by Jason on 11/30/2015.
 */
public class KgRoundDown implements Roundable {
    public KgRoundDown() {
    }

    @Override
    public double performCalc(double weight) {
        return (2.5 * (Math.floor(Math.abs(weight / 2.5))));
    }
}
