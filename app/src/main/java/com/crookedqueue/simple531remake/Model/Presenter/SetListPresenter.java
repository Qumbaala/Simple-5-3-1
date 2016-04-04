package com.crookedqueue.simple531remake.Model.Presenter;

import android.view.View;

import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.CompoundSetListBuilder;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.ParamsBundle;

import java.util.List;

/**
 * Created by qumbaala on 4/4/2016.
 */
public class SetListPresenter<T extends View> {
    T t;
    ParamsBundle bundle;
    CompoundSetListBuilder compoundSetBuilder;

    public SetListPresenter(ParamsBundle bundle, T t) {
        this.bundle = bundle;
        this.t = t;
        compoundSetBuilder = new CompoundSetListBuilder(bundle);
    }

    public List<? extends ExerciseSet> getSetList(){
        return compoundSetBuilder.buildCompoundSet();
    }
}
