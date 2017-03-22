package com.crookedqueue.simple531.Model.ExerciseSetBuilding;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qumbaala on 4/3/2016.
 * Builds all the sets for a given day based on params passed in by the params bundle.
 */
public class CompoundSetListBuilder {
    ParamsBundle bundle;

    private final SetListBuilder setListBuilder;
    List<ExerciseSet> compoundSetList;


    public CompoundSetListBuilder(ParamsBundle bundle) {
        this.bundle = bundle;
        setListBuilder = new SetListBuilder(bundle.isUseKg(), bundle.isRoundUp());
        compoundSetList = new ArrayList<>();
    }

    //builds all the sets for a single day in the cycle
    public List<ExerciseSet> buildCompoundSets(){
        compoundSetList.addAll(buildWarmupSets());
        compoundSetList.addAll(buildWorkingSets());
        compoundSetList.addAll(buildAssistanceSets());
        return compoundSetList;
    }

    //alternate for building files
    public List<ExerciseSet> buildCompoundSets(LiftLabel label, SetType type, Double maxWeight){
        List<ExerciseSet> tempSet = new ArrayList<>();
        tempSet.addAll(buildWarmupSets(maxWeight));
        tempSet.addAll(buildWorkingSets(label, type, maxWeight));
        tempSet.addAll(buildAssistanceSets(maxWeight));
        return tempSet;
    }

    //builds all four weeks worth of compound sets for a given lift
    public Map<Integer, List<ExerciseSet>> buildAllFourWeeksCompoundSets(LiftLabel label, Double maxWeight){
        SetType[] setTypes = new SetType[]{SetType.WORKING_SET_WEEK1, SetType.WORKING_SET_WEEK2, SetType.WORKING_SET_WEEK3, SetType.WORKING_SET_WEEK4};
        Map<Integer, List<ExerciseSet>> setMap = new HashMap<>();
        //build weeks 1-4
        for (int i = 0; i < setTypes.length; i++)
        {
            List<ExerciseSet> tempSet = buildCompoundSets(label, setTypes[i], maxWeight);
            setMap.put(i, tempSet);
        }
        return setMap;
    }


    private List<ExerciseSet> buildWarmupSets(){
        return setListBuilder.buildSetList(LiftLabel.WARM$UP, SetType.WARMUP, bundle.getMaxWeight());
    }

    //alt to no-arg for building files, does not use bundled weight
    private List<ExerciseSet> buildWarmupSets(Double maxWeight)
    {
        return setListBuilder.buildSetList(LiftLabel.WARM$UP, SetType.WARMUP, maxWeight);
    }

    private List<ExerciseSet> buildWorkingSets(){
        List workingSetList = setListBuilder.buildSetList(bundle.getLiftLabel(), bundle.getWorkingSetType(), bundle.getMaxWeight());
        if (bundle.isUseFsl)
        {
            workingSetList.add(workingSetList.get(0));
        }
        return workingSetList;
    }

    //alt for building files, does not require use of bundled params, except isUseFsl
    private List<ExerciseSet> buildWorkingSets(LiftLabel label, SetType type, Double maxWeight){
        List workingSetList = setListBuilder.buildSetList(label, type, maxWeight);
        if (bundle.isUseFsl)
        {
            workingSetList.add(workingSetList.get(0));
        }
        return workingSetList;
    }

    private List<ExerciseSet> buildAssistanceSets(){
        return setListBuilder.buildSetList(LiftLabel.ASSISTANCE, bundle.getAssistanceSetType(), bundle.getMaxWeight());
    }

    //alt for building files, does not use bundle
    private List<ExerciseSet> buildAssistanceSets(Double maxWeight){
        return setListBuilder.buildSetList(LiftLabel.ASSISTANCE, bundle.getAssistanceSetType(), maxWeight);
    }
}
