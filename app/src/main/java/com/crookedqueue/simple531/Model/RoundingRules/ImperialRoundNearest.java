package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by qumbaala on 5/9/2016.
 */
public class ImperialRoundNearest implements Roundable {
    @Override
    public double performCalc(double weight) {
        return (double) 5*(Math.round(weight/5));
    }
}
