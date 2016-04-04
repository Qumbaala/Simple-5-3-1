package com.crookedqueue.simple531remake.Model.RoundingRules;

import java.io.Serializable;

/**
 * Created by Jason on 11/30/2015.
 */
public class ImperialRoundDown implements Roundable{

    public ImperialRoundDown() {
    }

    @Override
    public Double performCalc(Double weight) {
        return (5 * (Math.floor(Math.abs(weight / 5))));
    }
}
