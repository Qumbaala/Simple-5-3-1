package com.crookedqueue.simple531.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.MaxListAdapter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaxListFragment extends Fragment {

    @Bind(R.id.recycler_max_list)
    RecyclerView recycler;
    MaxListAdapter adapter;
    FragmentInterractor fragInterractor;
    private final static String TOOLBAR_LABEL= "Max List";


    public MaxListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_max_list, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        adapter = new MaxListAdapter(DbHelper.getInstance(getContext()).retrieveAllMaxes());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
