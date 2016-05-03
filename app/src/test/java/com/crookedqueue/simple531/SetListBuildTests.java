package com.crookedqueue.simple531;

import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetListBuilder;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetType;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qumbaala on 4/4/2016.
 */
public class SetListBuildTests {
    List<ExerciseSet> expectedSet = new ArrayList<>();
    double weight = 315.0d;

    @Test
    public void setListBuilderWarmupTest() {
        ExerciseSet exerciseSet = new ExerciseSet("Warmup Set", weight * .4, 5);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        SetListBuilder setListBuilder = new SetListBuilder();
        List<? extends ExerciseSet> actualSet = setListBuilder.buildSetList(LiftLabel.WARM$UP, SetType.WARMUP, weight);
        assertEquals(expectedSet.get(1).getLabel(), actualSet.get(0).getLabel());
        assertEquals(expectedSet.get(1).getReps(), actualSet.get(0).getReps());
        assertEquals(expectedSet.get(1).getRoundedWeight(), actualSet.get(0).getRoundedWeight(), 0);
    }

    @Test
    public void setListBuilderWorkingSetTest() {
        ExerciseSet exerciseSet = new ExerciseSet("Bench Press", weight * .65, 5);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        SetListBuilder setListBuilder = new SetListBuilder();
        List<? extends ExerciseSet> actualSet = setListBuilder.buildSetList(LiftLabel.BENCH_PRESS, SetType.WORKING_SET_WEEK1, weight);
        assertEquals(expectedSet.get(0).getLabel(), actualSet.get(0).getLabel());
        assertEquals(expectedSet.get(0).getReps(), actualSet.get(0).getReps());
        assertEquals(expectedSet.get(0).getRoundedWeight(), actualSet.get(0).getRoundedWeight(), 0);
    }

    @Test
    public void setListBuilderAssistanceSetTest() {
        ExerciseSet exerciseSet = new ExerciseSet("Boring But Big", weight * .3, 10);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        expectedSet.add(exerciseSet);
        SetListBuilder setListBuilder = new SetListBuilder();
        List<? extends ExerciseSet> actualSet = setListBuilder.buildSetList(LiftLabel.BORING_BUT_BIG, SetType.BORING_BUT_BIG_ASCENDING, weight);
        assertEquals(expectedSet.get(0).getLabel(), actualSet.get(0).getLabel());
        assertEquals(expectedSet.get(0).getReps(), actualSet.get(0).getReps());
        assertEquals(expectedSet.get(0).getRoundedWeight(), actualSet.get(0).getRoundedWeight(), 0);
    }

}
