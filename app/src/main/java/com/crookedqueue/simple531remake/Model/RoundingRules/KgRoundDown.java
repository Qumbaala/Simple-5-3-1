package com.crookedqueue.simple531remake.Model.RoundingRules;

import java.io.Serializable;

/**
 * Created by Jason on 11/30/2015.
 */
public class KgRoundDown implements Roundable {
    public KgRoundDown() {
    }

    @Override
    public Double performCalc(Double weight) {
        return (2.5 * (Math.floor(Math.abs(weight / 2.5))));
    }
}
