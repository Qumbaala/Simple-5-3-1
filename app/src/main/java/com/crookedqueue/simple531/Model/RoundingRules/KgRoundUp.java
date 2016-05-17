package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by Jason on 11/30/2015.
 */
public class KgRoundUp implements Roundable {

    public KgRoundUp() {
    }

    @Override
    public double performCalc(double weight) {
        return ((2.5 * (Math.ceil(weight / 2.5))));
    }
}
