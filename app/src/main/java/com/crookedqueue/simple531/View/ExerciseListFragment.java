package com.crookedqueue.simple531.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crookedqueue.simple531.Presenter.ExerciseListAdapter;
import com.crookedqueue.simple531.Presenter.ExerciseListPresenter;
import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExerciseListFragment extends Fragment {
    public static final String LIFT_LABEL = "Lift Label";
    public static final String SET_TYPE = "Set Type";
    private int liftLabel;
    private int setType;
    private static final String TOOLBAR_LABEL = "Exercise List";
    @Bind(R.id.fab_stopwatch) FloatingActionButton fabStopwatch;
    @Bind(R.id.fab_record_pr) FloatingActionButton fabPR;
    @Bind(R.id.recycler_exercise_list) RecyclerView recycler;
    @Bind(R.id.fam_tools) FloatingActionMenu fam;
    FragmentInterractor fragInterractor;
    ExerciseListAdapter adapter;
    ExerciseListPresenter presenter;


    public ExerciseListFragment() {
        // Required empty public constructor
    }

    public static ExerciseListFragment newInstance(int liftLabel, int setType) {
        ExerciseListFragment fragment = new ExerciseListFragment();
        Bundle args = new Bundle();
        args.putInt(LIFT_LABEL, liftLabel);
        args.putInt(SET_TYPE, setType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragInterractor = (FragmentInterractor) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            liftLabel = getArguments().getInt(LIFT_LABEL);
            setType = getArguments().getInt(SET_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_list, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        presenter = new ExerciseListPresenter(this, liftLabel, setType);
        presenter.setFamAnimations();
        adapter = new ExerciseListAdapter(presenter.getSetList());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return view;
    }


    @OnClick(R.id.fab_stopwatch)
    public void buildStopWatch(){
        fam.close(true);
        presenter.buildStopWatchDialog();
    }

    @OnClick(R.id.fab_record_pr)
    public void buildPersonalRecord(){
        fam.close(true);
        presenter.buildPersonalRecordDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
        ButterKnife.unbind(this);
    }

    public FloatingActionMenu getFam() {
        return fam;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public ExerciseListAdapter getAdapter() {
        return adapter;
    }
}
