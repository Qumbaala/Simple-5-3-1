package com.crookedqueue.simple531.Presenter;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.CompoundSetListBuilder;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ParamsBundle;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetListBuildingUtils;
import com.crookedqueue.simple531.R;
import com.crookedqueue.simple531.View.ExerciseListFragment;
import com.crookedqueue.simple531.View.PersonalRecordDialog;
import com.crookedqueue.simple531.View.StopwatchDialog;

import java.util.List;

public class ExerciseListPresenter {
    ParamsBundle bundle;
    CompoundSetListBuilder compoundSetBuilder;
    ExerciseListFragment frag;

    public ExerciseListPresenter(ExerciseListFragment frag, int liftLabel, int setType) {
        this.frag = frag;
        DbHelper dbHelper = DbHelper.getInstance(frag.getContext());
        bundle = new ParamsBundle(
                SetListBuildingUtils.mapIntToLiftLabel(liftLabel),
                SetListBuildingUtils.mapIntToSetType(setType),
                dbHelper.retrieveAssistanceWork(),
                dbHelper.retrieveIsUseKg(),
                dbHelper.retrieveIsUseRoundUp(),
                dbHelper.retrieveIsUseFsl(),
                dbHelper.retrieveCurrentMaxes().getMaxFromLiftLabel(SetListBuildingUtils.mapIntToLiftLabel(liftLabel))
        );
        compoundSetBuilder = new CompoundSetListBuilder(bundle);
    }

    public void buildPersonalRecordDialog(){
        PersonalRecordDialog dialog = new PersonalRecordDialog();
        dialog.show(frag.getFragmentManager(), "Personal Record Dialog");
    }

    public void buildStopWatchDialog() {
        DialogFragment dialog = new StopwatchDialog();
        dialog.show(frag.getFragmentManager(), "Rest Timer Dialog");
    }

    public void setFamAnimations(){
        frag.getFam().setMenuButtonHideAnimation(AnimationUtils.loadAnimation(frag.getContext(), R.anim.fab_hide));
        frag.getFam().setMenuButtonShowAnimation(AnimationUtils.loadAnimation(frag.getContext(), R.anim.fab_show));
        frag.getRecycler().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && frag.getFam().getVisibility() == View.VISIBLE) {
                    frag.getFam().hideMenu(true);
                    frag.getFabStopwatch().setClickable(false);
                    frag.getFabPR().setClickable(false);
                } else if (dy < 0 && frag.getFam().getVisibility() != View.VISIBLE) {
                    frag.getFam().close(false);
                    frag.getFam().showMenu(true);
                    frag.getFabStopwatch().setClickable(true);
                    frag.getFabPR().setClickable(true);
                }
            }
        });
    }

    public List<ExerciseSet> getSetList() {
        return compoundSetBuilder.buildCompoundSets();
    }
}
