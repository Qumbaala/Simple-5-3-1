package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

import com.crookedqueue.simple531remake.Model.RoundingRules.Roundable;
import com.crookedqueue.simple531remake.Model.RoundingRules.RoundedWeightCalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class CompoundSetListBuilder {
    private final SetListBuilder setListBuilder;
    ParamsBundle bundle;
    private final List<? extends ExerciseSet> compoundSetList;
    private final Roundable roundedWeightCalc;

    public CompoundSetListBuilder(ParamsBundle bundle) {
        this.bundle = bundle;
        setListBuilder = new SetListBuilder();
        compoundSetList = new ArrayList<>();
        roundedWeightCalc = new RoundedWeightCalc(bundle.isUseKg(), bundle.isRoundUp());
    }

    public List<? extends ExerciseSet> buildCompoundSet(){
        List<ExerciseSet> compoundList = new ArrayList<>();
        compoundList.addAll(buildWarmupSets());
        compoundList.addAll(buildWorkingSets());
        compoundList.addAll(buildAssistanceSets());
        return compoundList;
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
