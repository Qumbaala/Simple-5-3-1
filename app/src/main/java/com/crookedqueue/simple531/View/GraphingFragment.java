package com.crookedqueue.simple531.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crookedqueue.simple531.Presenter.GraphingPresenter;
import com.crookedqueue.simple531.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphingFragment extends Fragment {
    @Bind(R.id.squat_chart)
    GraphView squatGraph;
    GraphingPresenter presenter;
    public GraphingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphing, container, false);
        ButterKnife.bind(this, view);
        presenter = new GraphingPresenter(getContext());
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                new DataPoint[]{
                        new DataPoint(0, 100),
                        new DataPoint(1, 200),
                        new DataPoint(2, 200),
                        new DataPoint(3, 300),
                        new DataPoint(4, 400),
                        new DataPoint(5, 500),
                        new DataPoint(6, 600),
                        new DataPoint(7, 700),
                        new DataPoint(8, 100),
                        new DataPoint(9, 900),
                }
        );
        squatGraph.getViewport().setYAxisBoundsManual(true);
        squatGraph.getViewport().setMinY(0);
        squatGraph.getViewport().setMaxY(1000);
        squatGraph.getViewport().setXAxisBoundsManual(true);
        squatGraph.getViewport().setMinX(0);
        squatGraph.getViewport().setMaxX(3);

        //squatGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getContext()));
        //squatGraph.getGridLabelRenderer().setNumHorizontalLabels(3);
        squatGraph.getGridLabelRenderer().setHumanRounding(true);
        squatGraph.getViewport().setScrollable(true);
        squatGraph.getViewport().setScrollableY(true);
        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        series.setAnimated(true);
        squatGraph.addSeries(series);
        return view;
    }



}
