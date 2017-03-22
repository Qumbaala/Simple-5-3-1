package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

import com.crookedqueue.simple531.Model.RoundingRules.Roundable;
import com.crookedqueue.simple531.Model.RoundingRules.RoundedWeightCalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class SetListBuilder {
    private final Roundable roundedWeightCalc;
    private boolean isUseKg;
    private boolean isRoundUp;

    public SetListBuilder(boolean isUseKg, boolean isRoundUp) {
        this.isUseKg = isUseKg;
        this.isRoundUp = isRoundUp;
        roundedWeightCalc = new RoundedWeightCalc(this.isUseKg, this.isRoundUp);
    }

    public List<ExerciseSet> buildSetList(LiftLabel liftLabel, SetType type, Double maxWeight) {
        List<ExerciseSet> exerciseSets = new ArrayList<>();
        //must null check in case used to build a no-assistance list (none enum)
        if (type != null) {
            List<Integer> repList = SetListBuildingUtils.getRepScheme(type);
            List<Double> percList = SetListBuildingUtils.getPercentages(type);
            for (int i = 0; i < repList.size(); i++) {
                double rawWeight = (percList.get(i) * maxWeight);
                double roundedWeight = roundedWeightCalc.performCalc(rawWeight);
                ExerciseSet set = new ExerciseSet(liftLabel, roundedWeight, repList.get(i));
                exerciseSets.add(set);
            }
        }
        return exerciseSets;
    }
}
