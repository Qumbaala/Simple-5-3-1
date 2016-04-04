package com.crookedqueue.simple531remake.Model.ExerciseSetBuilding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qumbaala on 4/3/2016.
 */
public class CompoundSetListBuilder {
    private final SetListBuilder setListBuilder;
    ParamsBundle bundle;
    private final List<? extends ExerciseSet> compoundSetList;

    public CompoundSetListBuilder(ParamsBundle bundle) {
        this.bundle = bundle;
        setListBuilder = new SetListBuilder();
        compoundSetList = new ArrayList<>();
    }

    public List<? extends ExerciseSet> buildCompoundSet(){
        List<ExerciseSet> compoundList = new ArrayList<>();
        compoundList.addAll(buildWarmupSets());
        compoundList.addAll(buildWorkingSets());
        compoundList.addAll(buildAssistanceSets());
        return compoundList;
    }

    private List<? extends ExerciseSet> buildWarmupSets(){
        return setListBuilder.buildSetList(LiftType.WARMUP_SET, SetType.WARMUP, bundle.maxWeight);
    }

    private List<? extends ExerciseSet> buildWorkingSets(){
        List workingSetList = setListBuilder.buildSetList(bundle.liftType, bundle.workingSetType, bundle.maxWeight);
        if (bundle.isUseFsl){
            workingSetList.add(workingSetList.get(0));
        }
        return workingSetList;
    }


    private List<? extends ExerciseSet> buildAssistanceSets(){
        return setListBuilder.buildSetList(bundle.liftType, bundle.assistanceSetType, bundle.maxWeight);
    }
}
