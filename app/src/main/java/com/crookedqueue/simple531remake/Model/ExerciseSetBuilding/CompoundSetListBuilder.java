package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

import com.crookedqueue.simple531remake.Model.RoundingRules.Roundable;
import com.crookedqueue.simple531remake.Model.RoundingRules.RoundedWeightCalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class CompoundSetListBuilder {
    ParamsBundle bundle;
    private final Roundable roundedWeightCalc;
    private final SetListBuilder setListBuilder;
    Map<String, List<? extends ExerciseSet>> compoundSetMap;
    private final String WARMUP_SETS_KEY = "WARM_UP_SETS";
    private final String WORKING_SETS_KEY = "WORKING_SETS";
    private final String ASSISTANCE_SETS_KEY = "ASSISTANCE_SETS";

    public CompoundSetListBuilder(ParamsBundle bundle) {
        this.bundle = bundle;
        setListBuilder = new SetListBuilder();
        compoundSetMap = new HashMap<>();
        roundedWeightCalc = new RoundedWeightCalc(bundle.isUseKg(), bundle.isRoundUp());
    }

    public Map<String, List<? extends ExerciseSet>> buildCompoundSets(){
        compoundSetMap.put(WARMUP_SETS_KEY, buildWarmupSets());
        compoundSetMap.put(WORKING_SETS_KEY, buildWorkingSets());
        compoundSetMap.put(ASSISTANCE_SETS_KEY, buildAssistanceSets());
        return compoundSetMap;
    }

    private List<? extends ExerciseSet> buildWarmupSets(){
        return setListBuilder.buildSetList(LiftType.WARMUP_SET, SetType.WARMUP, roundedWeightCalc.performCalc(bundle.getMaxWeight()));
    }

    private List<? extends ExerciseSet> buildWorkingSets(){
        List workingSetList = setListBuilder.buildSetList(bundle.getLiftType(), bundle.getWorkingSetType(), roundedWeightCalc.performCalc(bundle.getMaxWeight()));
        if (bundle.isUseFsl){
            workingSetList.add(workingSetList.get(0));
        }
        return workingSetList;
    }


    private List<? extends ExerciseSet> buildAssistanceSets(){
        return setListBuilder.buildSetList(bundle.getLiftType(), bundle.getAssistanceSetType(), roundedWeightCalc.performCalc(bundle.getMaxWeight()));
    }
}
