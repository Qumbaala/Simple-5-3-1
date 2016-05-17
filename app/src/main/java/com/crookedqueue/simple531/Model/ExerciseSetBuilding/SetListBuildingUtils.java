package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetListBuildingUtils {


    public static List<Integer> getRepScheme(SetType type) {
        List<Integer> repScheme = new ArrayList<>();
        switch (type) {
            case WORKING_SET_WEEK1:
                repScheme = Arrays.asList(5, 5, 5);
                break;
            case WORKING_SET_WEEK2:
                repScheme = Arrays.asList(3, 3, 3);
                break;
            case WORKING_SET_WEEK3:
                repScheme = Arrays.asList(5, 3, 1);
                break;
            case WORKING_SET_WEEK4:
                repScheme = Arrays.asList(5, 5, 5);
                break;
            case WARMUP:
                repScheme = Arrays.asList(5, 5, 5);
                break;
            case BORING_BUT_BIG_FLAT_50: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10, 10, 10, 10, 10);
                break;
            case BORING_BUT_BIG_ASCENDING: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10, 10, 10, 10, 10);
                break;
            case BORING_BUT_BIG_DESCENDING: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10, 10, 10, 10, 10);
                break;
            case BORING_BUT_BIG_PYRAMID: //redundant for readability, default case not future-proof
                repScheme = Arrays.asList(10, 10, 10, 10, 10);
                break;
        }
        return repScheme;
    }

    public static List<Double> getPercentages(SetType type) {
        List<Double> percs = null;
        switch (type) {
            case WORKING_SET_WEEK1:
                percs = Arrays.asList(.65, .75, .85);
                break;
            case WORKING_SET_WEEK2:
                percs = Arrays.asList(.7, .8, .9);
                break;
            case WORKING_SET_WEEK3:
                percs = Arrays.asList(.75, .85, .95);
                break;
            case WORKING_SET_WEEK4:
                percs = Arrays.asList(.4, .5, .6);
                break;
            case WARMUP:
                percs = Arrays.asList(.4, .5, .6);
                break;
            case BORING_BUT_BIG_FLAT_50: //boring but big flat 50%
                percs = Arrays.asList(.5, .5, .5, .5, .5);
                break;
            case BORING_BUT_BIG_ASCENDING: //boring but big ascending
                percs = Arrays.asList(.3, .4, .5, .6, .7);
                break;
            case BORING_BUT_BIG_DESCENDING: //boring but big descending
                percs = Arrays.asList(.7, .6, .5, .4, .3);
                break;
            case BORING_BUT_BIG_PYRAMID://boring but big pyramid
                percs = Arrays.asList(.5, .6, .7, .6, .5);
                break;
        }
        return percs;
    }

    public static SetType mapIntToSetType(int setType) {
        switch (setType) {
            case 0:
                return SetType.WARMUP;
            case 1:
                return SetType.WORKING_SET_WEEK1;
            case 2:
                return SetType.WORKING_SET_WEEK2;
            case 3:
                return SetType.WORKING_SET_WEEK3;
            case 4:
                return SetType.WORKING_SET_WEEK4;
            case 5:
                return SetType.NONE;
            case 6:
                return SetType.BORING_BUT_BIG_FLAT_50;
            case 7:
                return SetType.BORING_BUT_BIG_ASCENDING;
            case 8:
                return SetType.BORING_BUT_BIG_DESCENDING;
            case 9:
                return SetType.BORING_BUT_BIG_PYRAMID;
            default:
                return null;
        }
    }

    public static int mapSetTypeToInt(SetType setType) {
        int setTypeReturn = -1;
        switch (setType) {
            case WARMUP:
                setTypeReturn = 0;
                break;
            case WORKING_SET_WEEK1:
                setTypeReturn = 1;
                break;
            case WORKING_SET_WEEK2:
                setTypeReturn = 2;
                break;
            case WORKING_SET_WEEK3:
                setTypeReturn = 3;
                break;
            case WORKING_SET_WEEK4:
                setTypeReturn = 4;
                break;
            case NONE:
                setTypeReturn = 5;
                break;
            case BORING_BUT_BIG_FLAT_50:
                setTypeReturn = 6;
                break;
            case BORING_BUT_BIG_ASCENDING:
                setTypeReturn = 7;
                break;
            case BORING_BUT_BIG_DESCENDING:
                setTypeReturn = 8;
                break;
            case BORING_BUT_BIG_PYRAMID:
                setTypeReturn = 9;
                break;
        }
        return setTypeReturn;
    }

    public static LiftLabel mapIntToLiftLabel(int liftTypeInt) {
        LiftLabel label = null;
        switch (liftTypeInt) {
            case 0:
                label = LiftLabel.SQUAT;
                break;
            case 1:
                label = LiftLabel.BENCH_PRESS;
                break;
            case 2:
                label = LiftLabel.DEADLIFT;
                break;
            case 3:
                label = LiftLabel.PRESS;
                break;
            case 4:
                label = LiftLabel.WARM$UP;
                break;
            case 5:
                label = LiftLabel.ASSISTANCE;
                break;
        }
        return label;
    }

    public static int mapLiftLabelToInt(LiftLabel label) {
        int i = -1;
        switch (label) {
            case SQUAT:
                i = 0;
                break;
            case BENCH_PRESS:
                i = 1;
                break;
            case DEADLIFT:
                i = 2;
                break;
            case PRESS:
                i = 3;
                break;
            case WARM$UP:
                i = 4;
                break;
            case ASSISTANCE:
                i = 5;
                break;
        }
        return i;
    }

    public static String stringFromLabel(LiftLabel liftLabel){
        String stringLabel = WordUtils.capitalize(liftLabel.name()
                .toLowerCase()
                .replace("_", " "))
                .replace("$", "-");
        return stringLabel;
    }
}
