package com.crookedqueue.simple531remake.Model.RoundingRules;

import java.text.DecimalFormat;

/**
 * Created by qumbaala on 4/4/2016.
 */
public class NumberStringFormatter {
    boolean isUseKg;

    public NumberStringFormatter(boolean isUseKg) {
        this.isUseKg = isUseKg;
    }

    public String fixDoubleIfImperial(Double rawWeight){
        DecimalFormat df = new DecimalFormat("0.#");
        return isUseKg ? rawWeight.toString():df.format(rawWeight);
    }

}
