package com.crookedqueue.simple531remake.Presenter;

import android.content.Context;
import android.widget.TextView;

import com.crookedqueue.simple531remake.Model.DataBaseClassModels.DbHelper;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType;
import com.crookedqueue.simple531remake.Model.DataBaseClassModels.MaxesContainer;
import com.crookedqueue.simple531remake.View.ManuallyEditMaxDialog;
import com.crookedqueue.simple531remake.View.MaxManagerFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType.*;

/**
 * This will sit at the heart of the update maxes fragment.  It has methods for managing maxes over the lifetime of the users use of the program.
 * It has to do the following
 * -retrieve most recent maxes from db
 * -insert maxes into db upon incrementation or update
 * -
 */
public class MaxManagerPresenter{
    private static final double upperIncrementImpVal = 5.00d;
    private static final double lowerIncrementImpVal = 10.00d;
    private static final double upperIncrementKgVal = 2.27d;
    private static final double lowerIncrementKgVal = 4.54d;
    MaxManagerFragment fragment;
    private int textViewPos = -1;

    public MaxManagerPresenter(MaxManagerFragment fragment) {
        this.fragment = fragment;
    }

    public void updateFromDialog(int textViewPos){
        //inflate the dialog
        //set this as listener
        //call show on dialog
        //check if double is null or 0.0d
        //if it isnt null, set as value of textview at textViewPos
        //if it isnt null, set to null
        //set dialog to null
        ManuallyEditMaxDialog dialog = new ManuallyEditMaxDialog();
        dialog.setDialogListener(this);
        dialog.show(fragment.getChildFragmentManager(), "Dialog");
        this.textViewPos = textViewPos;
    }

    public void updateMaxWithinDialog(double newMax){
        if (textViewPos != -1){
            TextView txtView = fragment.getTextViews().get(textViewPos);
            txtView.setText(String.valueOf(newMax));
            textViewPos = -1;
        }
    }


    public void incrementMaxes(boolean isUseKg) {
        MaxesContainer oldMaxes = buildContainerFromViews();
        MaxesContainer newMaxes;
        if (!isUseKg) {
            newMaxes = new MaxesContainer((oldMaxes.getSquatMax() + lowerIncrementImpVal),
                    oldMaxes.getBenchMax() + upperIncrementImpVal,
                    oldMaxes.getDeadliftMax() + lowerIncrementImpVal,
                    oldMaxes.getPressMax() + upperIncrementImpVal);
        } else {
            newMaxes = new MaxesContainer((oldMaxes.getSquatMax() + lowerIncrementKgVal),
                    oldMaxes.getBenchMax() + upperIncrementKgVal,
                    oldMaxes.getDeadliftMax() + lowerIncrementKgVal,
                    oldMaxes.getPressMax() + upperIncrementKgVal);
        }
        assignTextValues(newMaxes);
    }


    public void insertNewMaxes() {
        MaxesContainer container = buildContainerFromViews();
        DbHelper.getInstance(fragment.getContext())
                .insertNewMaxes(container);
    }

    public void assignInitialTxtValues(){
        MaxesContainer container = retriveCurrentMaxes(fragment.getContext());
        List<TextView> txtViews = fragment.getTextViews();
        if (container != null){
            assignTextValues(container);
        }
        else {
            for (TextView txtView : txtViews){
                txtView.setText(String.valueOf(0));
            }
        }
    }

    public void assignTextValues(MaxesContainer container){
        Map<LiftType, String> fixedMap = maxesToString(container);
        List<TextView> txtViews = fragment.getTextViews();
        txtViews.get(0).setText(fixedMap.get(SQUAT));
        txtViews.get(1).setText(fixedMap.get(BENCH_PRESS));
        txtViews.get(2).setText(fixedMap.get(DEADLIFT));
        txtViews.get(3).setText(fixedMap.get(OVERHEAD_PRESS));
    }

    public MaxesContainer retriveCurrentMaxes(Context context) {
        MaxesContainer container;
        container = DbHelper.getInstance(context).retrieveCurrentMaxes();
        return container != null ? container: new MaxesContainer(0,0,0,0);
    }

    public Map<LiftType, String> maxesToString(MaxesContainer container){
        Map<LiftType, String> preparedMap = new HashMap<>();
        preparedMap.put(SQUAT, String.valueOf(container.getSquatMax()));
        preparedMap.put(BENCH_PRESS, String.valueOf(container.getBenchMax()));
        preparedMap.put(DEADLIFT, String.valueOf(container.getDeadliftMax()));
        preparedMap.put(OVERHEAD_PRESS, String.valueOf(container.getPressMax()));
        return preparedMap;
    }

    public Map<LiftType, Double> stringsToMaxes(){
        List<TextView> textViews = fragment.getTextViews();
        Map<LiftType, Double> fixedMap = new HashMap<>();
        double squatMax = Double.parseDouble(textViews.get(0).getText().toString());
        double benchMax = Double.parseDouble(textViews.get(1).getText().toString());
        double deadliftMax = Double.parseDouble(textViews.get(2).getText().toString());
        double pressMax = Double.parseDouble(textViews.get(3).getText().toString());
        fixedMap.put(SQUAT, squatMax);
        fixedMap.put(BENCH_PRESS, benchMax);
        fixedMap.put(DEADLIFT, deadliftMax);
        fixedMap.put(OVERHEAD_PRESS, pressMax);
        return fixedMap;

    }

    public MaxesContainer buildContainerFromViews(){
        Map<LiftType, Double> fixedMap = stringsToMaxes();
        return new MaxesContainer(
                fixedMap.get(SQUAT),
                fixedMap.get(BENCH_PRESS),
                fixedMap.get(DEADLIFT),
                fixedMap.get(OVERHEAD_PRESS)
                );
    }
}
