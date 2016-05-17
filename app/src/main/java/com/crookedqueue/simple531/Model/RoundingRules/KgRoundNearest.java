package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by qumbaala on 5/9/2016.
 */
public class KgRoundNearest implements Roundable {
    @Override
    public double performCalc(double weight) {
        return (double) 2.5*(Math.round(weight/2.5));
    }
}
