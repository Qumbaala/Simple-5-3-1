package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class SetListBuilder {

    public SetListBuilder() {
    }

    public List<? extends ExerciseSet> buildSetList(LiftType liftType, SetType type, Double maxWeight) {
        List<Integer> repList = Utils.getRepScheme(type);
        List<Double> percList = Utils.getPercentages(type);
        String label = WordUtils.capitalize(liftType.name()
                                .toLowerCase()
                                .replace("_", " "));
        List<ExerciseSet> exerciseSets = new ArrayList<>();
        for (int i = 0; i < repList.size(); i++) {
            double rawWeight = (percList.get(i) * maxWeight);
            ExerciseSet set = new ExerciseSet(label, rawWeight, repList.get(i));
            exerciseSets.add(set);
        }
        return exerciseSets;
    }

}
