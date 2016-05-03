package com.crookedqueue.simple531.Presenter;


import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetListBuildingUtils;
import com.crookedqueue.simple531.View.SettingsFragment;

public class SettingsPresenter {
    SettingsFragment fragment;

    public SettingsPresenter(SettingsFragment settingsFragment) {
        this.fragment = settingsFragment;
    }

    public void onDestroy(){
        //updates all the values in the db based on the current state of the views
        DbHelper dbHelper = DbHelper.getInstance(fragment.getContext());
        dbHelper.updateAssistanceWork(SetListBuildingUtils.mapIntToSetType(fragment.getSpinnerAssistanceType().getSelectedItemPosition()+5));
        dbHelper.updateIsUseFsl(fragment.getSwitchUseFsl().isChecked());
        dbHelper.updateIsUseRoundUp(fragment.getSwitchRoundUp().isChecked());
        dbHelper.updateIsUseKg(fragment.getRadioKg().isChecked());
    }


    public void initializeViews(){
        DbHelper dbHelper = DbHelper.getInstance(fragment.getContext());
        fragment.getSpinnerAssistanceType()
                .setSelection(SetListBuildingUtils.mapSetTypeToInt(dbHelper.retrieveAssistanceWork())-5); //5 is the offset from the working SetTypes
        if (dbHelper.retrieveIsUseKg()){
            fragment.getRadioKg().setChecked(true);
        }
        else {
            fragment.getRadioPound().setChecked(true);
        }
        fragment.getSwitchRoundUp()
                .setChecked(dbHelper.retrieveIsUseRoundUp());
        fragment.getSwitchUseFsl()
                .setChecked(dbHelper.retrieveIsUseFsl());
    }

}
