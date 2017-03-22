package com.crookedqueue.simple531.View;


import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.Presenter.CSVPresenter;
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
 * A simple {@link Fragment} subclass.
 */
public class FileSaveFragment extends Fragment {
    @Bind(R.id.save_btn)
    Button saveBtn;
    @Bind(R.id.edit_num_cycles)
    EditText editNumCycles;
    @Bind(R.id.edit_upper_increment)
    EditText editUpperIncrement;
    @Bind(R.id.edit_lower_increment)
    EditText editLowerIncrement;
    @Bind(R.id.checkbox_append_perc_chart)
    CheckBox checkBoxAppendPercChart;

    FragmentInterractor fragInterractor;
    private static final String TOOLBAR_LABEL = "Export Cycle";

    public FileSaveFragment() {
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
        View view = inflater.inflate(R.layout.fragment_file_writer, container, false);
        ButterKnife.bind(this, view);
        fragInterractor.setToolbarTitle(TOOLBAR_LABEL);
        return view;
    }

    @OnClick(R.id.save_btn)
    public void saveCycle() {
        //getting permissions
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //validate input
                        if (validateInput()) {
                            int numCyles = Integer.valueOf(editNumCycles.getText().toString());
                            double upperIncrement = Double.valueOf(editUpperIncrement.getText().toString());
                            double lowerIncrement = Double.valueOf(editLowerIncrement.getText().toString());
                            //init
                            CSVPresenter csvPresenter = new CSVPresenter((AppCompatActivity) getContext());
                            if (csvPresenter.writeCSV(numCyles, upperIncrement, lowerIncrement, checkBoxAppendPercChart.isChecked())) {
                                Toast.makeText(getContext(), "File Created Succesfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "File Creation Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Please enter a valid number for all fields", Toast.LENGTH_SHORT).show();
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

    private boolean validateInput(){
        if (!TextUtils.isEmpty(editNumCycles.getText().toString()) && !TextUtils.isEmpty(editLowerIncrement.getText().toString()) && !TextUtils.isEmpty(editUpperIncrement.getText().toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}


