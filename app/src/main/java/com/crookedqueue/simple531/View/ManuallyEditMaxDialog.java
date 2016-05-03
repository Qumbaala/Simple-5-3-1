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
import android.widget.ImageButton;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Presenter.MaxManagerPresenter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManuallyEditMaxDialog extends DialogFragment {

    @Bind(R.id.edit_weight)
    EditText editWeight;
    @Bind(R.id.edit_reps)
    EditText editReps;
    @Bind(R.id.btn_check_maxes)
    ImageButton btnCheckMaxes;
    MaxManagerPresenter listener;

    public ManuallyEditMaxDialog() {
        super();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_manually_edit_max, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setTitle("Manually Edit Max");
        builder.setMessage(R.string.max_formula_text);
        builder.setPositiveButton("Set Max",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (inputIsValid()) {
                    double max = getMaxFromDialog(Double.parseDouble(editWeight.getText().toString()),
                            Integer.parseInt(editReps.getText().toString()),
                            DbHelper.getInstance(getContext()).retrieveIsUseKg());

                    if (listener != null) {
                        listener.updateMaxWithinDialog(max);
                    } else {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
            }


        });
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ButterKnife.unbind(this);
    }


    public void setDialogListener(MaxManagerPresenter listener) {
        this.listener = listener;
    }

    public double getMaxFromDialog(double weight, int rep, boolean isUseKg) {
        double trainingMax = 0d;
        //this can all be replaced with objects from the model that we will create later...too much repetition
        if (rep == 1 && !isUseKg) {
            trainingMax = (5 * Math.round((weight * .9) / 5));
        } else if (rep != 1 && !isUseKg) {
            trainingMax = (5 * (Math.round(((weight * rep * .0333 + weight) / 5) * .9)));
        } else if (rep == 1 && isUseKg) {
            trainingMax = (2.5 * Math.round((weight * .9) / 2.5));
        } else if (rep != 1 && isUseKg) {
            trainingMax = (2.5 * (Math.round(((weight * rep * .0333 + weight) / 2.5) * .9)));
        }
        return trainingMax;
    }

    public boolean inputIsValid() {
        return (!TextUtils.isEmpty(editWeight.getText()) && !TextUtils.isEmpty(editReps.getText())) && (!editWeight.getText().toString().equals(String.valueOf(0)) && !editReps.getText().toString().equals(String.valueOf(0)));
    }

    @OnClick(R.id.btn_check_maxes)
    public void toastMax() {
        if (inputIsValid()) {
            double max = getMaxFromDialog(Double.parseDouble(editWeight.getText().toString()),
                    Integer.parseInt(editReps.getText().toString()), DbHelper.getInstance(getContext()).retrieveIsUseKg());

            Toast.makeText(getContext(), String.valueOf(max), Toast.LENGTH_SHORT).show();
        }
    }

}
