package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class CompoundSetListBuilder {
    ParamsBundle bundle;

    private final SetListBuilder setListBuilder;
    List<ExerciseSet> compoundSetList;

    public CompoundSetListBuilder(ParamsBundle bundle) {
        this.bundle = bundle;
        setListBuilder = new SetListBuilder(bundle.isUseKg(), bundle.isRoundUp());
        compoundSetList = new ArrayList<>();
        ;
    }

    public List<ExerciseSet> buildCompoundSets(){
        compoundSetList.addAll(buildWarmupSets());
        compoundSetList.addAll(buildWorkingSets());
        compoundSetList.addAll(buildAssistanceSets());
        return compoundSetList;
    }

    private List<ExerciseSet> buildWarmupSets(){
        return setListBuilder.buildSetList(LiftLabel.WARM$UP, SetType.WARMUP, bundle.getMaxWeight());
    }

    private List<ExerciseSet> buildWorkingSets(){
        List workingSetList = setListBuilder.buildSetList(bundle.getLiftLabel(), bundle.getWorkingSetType(), bundle.getMaxWeight());
        if (bundle.isUseFsl){
            workingSetList.add(workingSetList.get(0));
        }
        return workingSetList;
    }


    private List<ExerciseSet> buildAssistanceSets(){
        return setListBuilder.buildSetList(LiftLabel.ASSISTANCE, bundle.getAssistanceSetType(), bundle.getMaxWeight());
    }
}
