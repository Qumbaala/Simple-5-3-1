package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

import java.util.Arrays;
import java.util.List;

public class Utils {



    public static List<Integer> getRepScheme(SetType type){
        List<Integer> repScheme = null;
        switch (type){
            case WORKING_SET_WEEK1:
                repScheme = Arrays.asList(5,5,5);
                break;
            case WORKING_SET_WEEK2:
                repScheme = Arrays.asList(3,3,3);
                break;
            case WORKING_SET_WEEK3:
                repScheme = Arrays.asList(5,3,1);
                break;
            case WORKING_SET_WEEK4:
                repScheme = Arrays.asList(5,5,5);
                break;
            case WARMUP:
                repScheme = Arrays.asList(5,5,5);
                break;
            case BORING_BUT_BIG_FLAT_50: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10,10,10,10,10);
                break;
            case BORING_BUT_BIG_ASCENDING: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10,10,10,10,10);
                break;
            case BORING_BUT_BIG_DESCENDING: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10,10,10,10,10);
                break;
            case BORING_BUT_BIG_PYRAMID: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10,10,10,10,10);
                break;
        }
        return repScheme;
    }

    public static List<Double> getPercentages(SetType type){
        List<Double> percs = null;
        switch (type){
            case WORKING_SET_WEEK1:
                percs = Arrays.asList(.65, .75, .85);
                break;
            case WORKING_SET_WEEK2:
                percs = Arrays.asList(.7,.8,.9);
                break;
            case WORKING_SET_WEEK3:
                percs = Arrays.asList(.75,.85,.95);
                break;
            case WORKING_SET_WEEK4:
                percs = Arrays.asList(.4,.5,.6);
                break;
            case WARMUP:
                percs = Arrays.asList(.4,.5,.6);
                break;
            case BORING_BUT_BIG_FLAT_50: //boring but big flat 50%
                percs = Arrays.asList(.5,.5,.5,.5,.5);
                break;
            case BORING_BUT_BIG_ASCENDING: //boring but big ascending
                percs = Arrays.asList(.3,.4,.5,.6,.7);
                break;
            case BORING_BUT_BIG_DESCENDING: //boring but big descending
                percs = Arrays.asList(.7,.6,.5,.4,.3);
                break;
            case BORING_BUT_BIG_PYRAMID://boring but big pyramid
                percs = Arrays.asList(.5,.6,.7,.6,.5);
                break;
        }
        return percs;
    }
}
