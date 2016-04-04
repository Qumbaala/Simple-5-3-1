package com.crookedqueue.simple531remake.Model.RoundingRules;

import java.io.Serializable;

/**
 * Created by Jason on 11/30/2015.
 */
public class ImperialRoundUp implements Roundable {
    public ImperialRoundUp() {
    }

    @Override
    public Double performCalc(Double weight) {
        return (5 * (Math.ceil(Math.abs(weight / 5))));
    }
}
