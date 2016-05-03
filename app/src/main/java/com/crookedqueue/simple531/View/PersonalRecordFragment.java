package com.crookedqueue.simple531.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.PersonalRecordAdapter;
import com.crookedqueue.simple531.Presenter.PersonalRecordPresenter;
import com.crookedqueue.simple531.R;
import com.github.clans.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalRecordFragment extends Fragment {
    @Bind(R.id.recycler_personal_record_list)
    RecyclerView recycler;
    @Bind(R.id.fab_record_pr_within)
    FloatingActionButton fab;
    PersonalRecordPresenter presenter;
    FragmentInterractor fragInterractor;
    PersonalRecordAdapter adapter;
    private static final String TOOLBAR_LABEL = "Personal Records";

    public PersonalRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragInterractor = (FragmentInterractor) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_record, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        presenter = new PersonalRecordPresenter(this);
        presenter.setAnimator();
        adapter = new PersonalRecordAdapter(presenter.getPersonalRecordList());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return view;
    }

    @OnClick(R.id.fab_record_pr_within)
    public void insertPersonalRecord() {
        presenter.buildPersonalRecordDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
        ButterKnife.unbind(this);
    }

    public PersonalRecordAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public FloatingActionButton getFab() {
        return fab;
    }
}
