package com.crookedqueue.simple531.View;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.Presenter.ExportRecordsPresenter;
import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Needs refactoring to reduce repetition, can implement dexter interface.
 */
public class ExportRecordsFragment extends Fragment {
    @Bind(R.id.btn_export_maxes)
    Button exportMaxes;
    @Bind(R.id.btn_export_pr)
    Button exportPR;
    FragmentInterractor fragmentInterractor;
    ExportRecordsPresenter exportRecordsPresenter;
    private static final String TOOLBAR_LABEL = "Export Records";

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        fragmentInterractor = (FragmentInterractor) getActivity();
    }

    public ExportRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_export_records, container, false);
        ButterKnife.bind(this, view);
        fragmentInterractor.setToolbarTitle(TOOLBAR_LABEL);
        return view;
    }

    @OnClick(R.id.btn_export_maxes)
    public void exportMaxes(){
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        exportRecordsPresenter = new ExportRecordsPresenter();
                        if (exportRecordsPresenter.exportMaxes((AppCompatActivity) getActivity())){
                            Toast.makeText(getContext(), "File Created Succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "File Creation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getContext(), "Unable to use this feature without permission", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @OnClick(R.id.btn_export_pr)
    public void exportPersonalRecords(){
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        exportRecordsPresenter = new ExportRecordsPresenter();
                        if (exportRecordsPresenter.exportPersonalRecords((AppCompatActivity) getActivity())){
                            Toast.makeText(getContext(), "File Created Succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "File Creation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getContext(), "Unable to use this feature without permission", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

}
