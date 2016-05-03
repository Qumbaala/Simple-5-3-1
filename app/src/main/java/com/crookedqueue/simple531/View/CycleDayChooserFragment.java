package com.crookedqueue.simple531.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.crookedqueue.simple531.View.ExerciseListFragment.LIFT_LABEL;
import static com.crookedqueue.simple531.View.ExerciseListFragment.SET_TYPE;

public class CycleDayChooserFragment extends Fragment {

    FragmentInterractor fragInterractor;
    private static final String TOOLBAR_LABEL = "Cycle Day";

    public CycleDayChooserFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cycle_day_chooser, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_squat_one, R.id.btn_squat_two, R.id.btn_squat_three, R.id.btn_squat_four, R.id.btn_bench_one,
            R.id.btn_bench_two, R.id.btn_bench_three, R.id.btn_bench_four, R.id.btn_deadlift_one,
            R.id.btn_deadlift_two, R.id.btn_deadlift_three, R.id.btn_deadlift_four,
            R.id.btn_press_one, R.id.btn_press_two, R.id.btn_press_three, R.id.btn_press_four})
    public void launchIntent(Button v){
        Intent intent  = new Intent(getContext(), ExerciseActivity.class);
        int liftLabel = -1;
        int setType = -1;
        switch (v.getId()){
            case R.id.btn_squat_one:
                liftLabel = 0;
                setType = 1;
                break;
            case R.id.btn_squat_two:
                liftLabel = 0;
                setType = 2;
                break;
            case R.id.btn_squat_three:
                liftLabel = 0;
                setType = 3;
                break;
            case R.id.btn_squat_four:
                liftLabel = 0;
                setType = 4;
                break;
            case R.id.btn_bench_one:
                liftLabel = 1;
                setType = 1;
                break;
            case R.id.btn_bench_two:
                liftLabel = 1;
                setType = 2;
                break;
            case R.id.btn_bench_three:
                liftLabel = 1;
                setType = 3;
                break;
            case R.id.btn_bench_four:
                liftLabel = 1;
                setType = 4;
                break;
            case R.id.btn_deadlift_one:
                liftLabel = 2;
                setType = 1;
                break;
            case R.id.btn_deadlift_two:
                liftLabel = 2;
                setType = 2;
                break;
            case R.id.btn_deadlift_three:
                liftLabel = 2;
                setType = 3;
                break;
            case R.id.btn_deadlift_four:
                liftLabel = 2;
                setType = 4;
                break;
            case R.id.btn_press_one:
                liftLabel = 3;
                setType = 1;
                break;
            case R.id.btn_press_two:
                liftLabel = 3;
                setType = 2;
                break;
            case R.id.btn_press_three:
                liftLabel = 3;
                setType = 3;
                break;
            case R.id.btn_press_four:
                liftLabel = 3;
                setType = 4;
                break;
        }
        intent.putExtra(LIFT_LABEL, liftLabel);
        intent.putExtra(SET_TYPE, setType);
        getActivity().startActivity(intent);
    }
}
