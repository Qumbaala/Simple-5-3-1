package com.crookedqueue.simple531.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.MaxManagerPresenter;
import com.crookedqueue.simple531.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaxManagerFragment extends Fragment{
    @Bind(R.id.btn_reset)
    Button btnReset;
    @Bind(R.id.btn_increment_all)
    Button btnIncrementAll;
    @Bind(R.id.btn_record_maxes)
    Button btnRecordMaxes;
    @Bind({R.id.btn_edit_squat, R.id.btn_edit_bench, R.id.btn_edit_deadlift, R.id.btn_edit_press})
    List<ImageButton> editLiftbtns;

    @Bind(R.id.txt_squat_max)
    TextView txtSquatMax;
    @Bind(R.id.txt_bench_max)
    TextView txtBenchMax;
    @Bind(R.id.txt_deadlift_max)
    TextView txtDeadliftMax;
    @Bind(R.id.txt_press_max)
    TextView txtPressMax;
    private static final String TOOLBAR_LABEL = "Edit Maxes";
    FragmentInterractor fragInterractor;
    MaxManagerPresenter presenter;


    public MaxManagerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_max_manager, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        presenter = new MaxManagerPresenter(this);
        presenter.assignInitialTxtValues();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
        ButterKnife.unbind(this);
    }

    public List<TextView> getTextViews() {
        List<TextView> textViews = new ArrayList<>();
        textViews.add(txtSquatMax);
        textViews.add(txtBenchMax);
        textViews.add(txtDeadliftMax);
        textViews.add(txtPressMax);
        return textViews;
    }

    @OnClick(R.id.btn_increment_all)
    public void incrementAll() {
        presenter.incrementMaxes();
    }

    @OnClick(R.id.btn_reset)
    public void resetAll() {
        presenter.assignInitialTxtValues();
    }

    @OnClick(R.id.btn_record_maxes)
    public void recordMaxes() {
        presenter.insertNewMaxes();
    }


    @OnClick({R.id.btn_edit_squat, R.id.btn_edit_bench, R.id.btn_edit_deadlift, R.id.btn_edit_press})
    public void onBtnClick(ImageButton v) {
        switch (v.getId()) {
            case R.id.btn_edit_squat:
                presenter.updateFromDialog(0);
                break;
            case R.id.btn_edit_bench:
                presenter.updateFromDialog(1);
                break;
            case R.id.btn_edit_deadlift:
                presenter.updateFromDialog(2);
                break;
            case R.id.btn_edit_press:
                presenter.updateFromDialog(3);
                break;
        }
    }
}


