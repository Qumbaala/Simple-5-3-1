package com.crookedqueue.simple531.View;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.PersonalRecord;
import com.crookedqueue.simple531.Presenter.PersonalRecordPresenter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalRecordDialog extends DialogFragment {
    @Bind(R.id.spinner_lift_type)
    Spinner spinner;
    @Bind(R.id.edit_pr_weight)
    EditText editWeight;
    @Bind(R.id.edit_pr_reps)
    EditText editReps;
    @Bind(R.id.edit_pr_notes)
    EditText editNotes;
    PersonalRecordPresenter listener;


    public PersonalRecordDialog() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_personal_record, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setTitle("New Personal Record");
        builder.setPositiveButton("Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (inputIsValid()) {
                    PersonalRecord pr = new PersonalRecord(
                            spinner.getSelectedItemPosition(),
                            Double.parseDouble(editWeight.getText().toString()),
                            Integer.parseInt(editReps.getText().toString()),
                            editNotes.getText().toString()
                    );
                    DbHelper.getInstance(getContext()).insertPersonalRecord(pr);
                    Toast.makeText(getContext(), "Recorded personal record", Toast.LENGTH_SHORT).show();
                    if (listener!=null){
                        listener.notifyAdapter(); //this feels bad man
                    }
                }
                else {
                    Toast.makeText(getContext(), "Weight and rep values must be greater than zero", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ButterKnife.unbind(this);
    }

    public boolean inputIsValid() {
        return (!TextUtils.isEmpty(editWeight.getText()) && !TextUtils.isEmpty(editReps.getText()))
                && (!editWeight.getText().toString().equals(String.valueOf(0)) && !editReps.getText().toString().equals(String.valueOf(0)));
    }

    public void setListener(PersonalRecordPresenter listener) {
        this.listener = listener;
    }
}
