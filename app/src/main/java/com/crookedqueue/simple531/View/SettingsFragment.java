package com.crookedqueue.simple531.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.SettingsPresenter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {
    @Bind(R.id.radio_units_pounds)
    RadioButton radioPound;
    @Bind(R.id.radio_units_kilograms)
    RadioButton radioKg;
    @Bind(R.id.spinner_assistance_type)
    Spinner spinnerAssistanceType;
    @Bind(R.id.switch_round_up)
    Switch switchRoundUp;
    @Bind(R.id.switch_use_fsl)
    Switch switchUseFsl;
    FragmentInterractor fragInterractor;
    private static final String TOOLBAR_LABEL = "Edit Cycle Settings";

    SettingsPresenter presenter;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragInterractor = (FragmentInterractor) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        presenter = new SettingsPresenter(this);
        presenter.initializeViews();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
        ButterKnife.unbind(this);
    }

    public RadioButton getRadioPound() {
        return radioPound;
    }

    public RadioButton getRadioKg() {
        return radioKg;
    }

    public Spinner getSpinnerAssistanceType() {
        return spinnerAssistanceType;
    }

    public Switch getSwitchRoundUp() {
        return switchRoundUp;
    }

    public Switch getSwitchUseFsl() {
        return switchUseFsl;
    }
}
