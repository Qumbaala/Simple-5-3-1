package com.crookedqueue.simple531.Model.RoundingRules;

/**
 * Created by qumbaala on 4/4/2016.
 */
public class NumberFormatter {

    //only do this if you check isUseKg first
    public static int fixDoubleIfImperial(double weight){
        int fixed = (int) weight;
        return fixed;
    }

}
