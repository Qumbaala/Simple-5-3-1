package com.crookedqueue.simple531.View;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crookedqueue.simple531.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchDialog extends DialogFragment {
    @Bind(R.id.txt_stop_watch)
    TextView txtTime;
    long millisLeft;
    CountDownTimer timer;

    public StopwatchDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_stopwatch, null);
        ButterKnife.bind(this, view);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setTitle("Rest Timer");
        timer = initCountdownTimer(11000);
        builder.setPositiveButton("+30sec", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Dismiss", null);
        timer.start();
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog ad = (AlertDialog) getDialog();
        if (ad!=null){
            Button positiveButton = (Button) ad.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.cancel();
                    timer = initCountdownTimer(millisLeft+30000);
                    timer.start();
                }
            });
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        timer.cancel();
        ButterKnife.unbind(this);
    }

    public CountDownTimer initCountdownTimer(long millis) {

        return new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;
                txtTime.setText(String.format(Locale.getDefault(),"%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                );
            }

            @Override
            public void onFinish() {
                //if user changes activities before countdown timer finishes, and then it finishes while they're in that activity, app will crash.  This is quick and dirty.
                try {
                    dismiss();
                }
                catch(Exception e){
                    Toast.makeText(getContext(), "Rest timer is finished.", Toast.LENGTH_SHORT);
                }
            }
        };
    }

}
