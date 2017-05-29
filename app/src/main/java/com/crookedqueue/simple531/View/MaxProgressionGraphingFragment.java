package com.crookedqueue.simple531.View;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.SeriesCreator;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.crookedqueue.simple531.Presenter.MaxProgressionPresenter;
import com.crookedqueue.simple531.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Needs to be refactored at a later date.
 */
public class MaxProgressionGraphingFragment extends Fragment implements OnChartValueSelectedListener, CompoundButton.OnCheckedChangeListener {
    @Bind(R.id.pr_chart)
    LineChart maxChart;
    @Bind(R.id.checkbox_squat_pr)
    CheckBox squatCheckBox;
    @Bind(R.id.checkbox_bench_pr)
    CheckBox benchCheckBox;
    @Bind(R.id.checkbox_deadlift_pr)
    CheckBox deadliftChecBox;
    @Bind(R.id.checkbox_press_pr)
    CheckBox pressCheckBox;
    MaxProgressionPresenter presenter;
    SeriesCreator seriesCreator;
    List<ILineDataSet> dataSets;
    LineDataSet benchData;
    LineDataSet squatData;
    LineDataSet deadliftData;
    LineDataSet pressData;
    LineData lineData;

    public MaxProgressionGraphingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphing, container, false);
        ButterKnife.bind(this, view);
        presenter = new MaxProgressionPresenter(getContext());
        seriesCreator = new SeriesCreator();

        //init data
        dataSets = new ArrayList<>();
        benchData = new LineDataSet(seriesCreator.createMaxProgressionDataPoints(LiftLabel.BENCH_PRESS, DbHelper.getInstance(getContext())), "Bench");
        squatData = new LineDataSet(seriesCreator.createMaxProgressionDataPoints(LiftLabel.SQUAT, DbHelper.getInstance(getContext())), "Squat");
        deadliftData = new LineDataSet(seriesCreator.createMaxProgressionDataPoints(LiftLabel.DEADLIFT, DbHelper.getInstance(getContext())), "Deadlift");
        pressData = new LineDataSet(seriesCreator.createMaxProgressionDataPoints(LiftLabel.PRESS, DbHelper.getInstance(getContext())), "Press");

        //style the graph
        maxChart.setOnChartValueSelectedListener(this);
        Description description = new Description();
        description.setText("Training Max Progression");
        maxChart.setDescription(description);
        maxChart.animateXY(500, 1000);
        maxChart.getAxisRight().setEnabled(false);
        XAxis xAxis = maxChart.getXAxis();
        xAxis.setGranularity(1f);
        maxChart.setPinchZoom(true);
        maxChart.setDoubleTapToZoomEnabled(false);


        //style squat line
        squatData.setColor(Color.RED);
        squatData.setCircleColor(Color.RED);
        //style bench line
        benchData.setColor(Color.BLUE);
        benchData.setCircleColor(Color.BLUE);
        //style deadlift line
        deadliftData.setColor(Color.GREEN);
        deadliftData.setCircleColor(Color.GREEN);
        //style press line
        pressData.setColor(Color.MAGENTA);
        pressData.setCircleColor(Color.MAGENTA);

        //set listener, move to presenter
        squatCheckBox.setOnCheckedChangeListener(this);
        benchCheckBox.setOnCheckedChangeListener(this);
        deadliftChecBox.setOnCheckedChangeListener(this);
        pressCheckBox.setOnCheckedChangeListener(this);
        return view;
    }


    //move this to presenter once we have it working
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), e.getData().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.checkbox_squat_pr) {
            if (b) {
                //draw line
                dataSets.add(squatData);

            } else {
                dataSets.remove(squatData);
            }
        }
        if (compoundButton.getId() == R.id.checkbox_bench_pr) {

            if (b) {
                dataSets.add(benchData);
            } else {
                dataSets.remove(benchData);
            }
        }
        if (compoundButton.getId() == R.id.checkbox_deadlift_pr) {

            if (b) {
                dataSets.add(deadliftData);
            } else {
                dataSets.remove(deadliftData);
            }
        }
        if (compoundButton.getId() == R.id.checkbox_press_pr) {

            if (b) {
                dataSets.add(pressData);
            } else {
                dataSets.remove(pressData);
            }
        }
            lineData = new LineData(dataSets);
            maxChart.setData(lineData);
            lineData.notifyDataChanged();
            maxChart.notifyDataSetChanged();
            lineData.setValueTextSize(9);
            maxChart.invalidate();
        }

    }

