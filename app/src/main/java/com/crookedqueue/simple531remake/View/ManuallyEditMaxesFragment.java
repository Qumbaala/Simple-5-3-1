package com.crookedqueue.simple531remake.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crookedqueue.simple531remake.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManuallyEditMaxesFragment extends Fragment {


    public ManuallyEditMaxesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manually_edit_maxes, container, false);
    }

}
