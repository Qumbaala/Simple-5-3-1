package com.crookedqueue.simple531.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.CompoundSetListBuilder;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ParamsBundle;
import com.crookedqueue.simple531.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by jason on 3/13/2017.
 * This class creates the entire cycle and writes it to CSV in the Documents folder of external storage
 * It uses compound set list builder, cycling over every possible cycle day in the following order:
 * All Day Ones -> All Day Twos -> All Day Three -> All Deload
 * Each day will have a label equal to its StringLabel
 * <p>
 * Can be stateless, utility class, but not static because static context is bad apparently
 */

public class CSVPresenter {
    private static final String FILE_LABEL = "Simple531";
    private static final String DATE = new SimpleDateFormat("MM-yyyy", Locale.getDefault()).format(new Date());
    private final String fileName = FILE_LABEL + "-" + DATE + ".csv";
    AppCompatActivity activity;
    String[] blankArray = new String[]{""};

    public CSVPresenter(AppCompatActivity activity) {

        this.activity = activity;
    }

    /**
     * Step Through Each Day, building the compound sets, then writing them to file
     * If the file is built and saved, return true
     */
    public boolean writeCSV(int numCycles, double upperIncrement, double lowerIncrement, boolean appendTable) {
        DbHelper dbHelper = DbHelper.getInstance(activity);
        //this is for setting access only
        ParamsBundle settingsBundle = ParamsBundle.getSettingsBundle(dbHelper);
        //recycled for all four lifts
        CompoundSetListBuilder setBuilder = new CompoundSetListBuilder(settingsBundle);
        //must init file outside the loop
        File csvFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csvFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //Write the headers to the file
        writer.writeNext(new String[]{"Lift Name", "Weight", "Reps"});

        //rerun this for number of cycles being generated
        //must increment maxes, so add max + (i*increment weight) -> this way it uses unaltered init maxes first wave
        for (int i = 0; i < numCycles; i++) {
            //one map each for the four days in the cycle
            Map<Integer, List<ExerciseSet>> squatMap = setBuilder.buildAllFourWeeksCompoundSets(LiftLabel.SQUAT, (dbHelper.retrieveCurrentMaxes().getSquatMax() + (i * lowerIncrement)));
            Map<Integer, List<ExerciseSet>> benchMap = setBuilder.buildAllFourWeeksCompoundSets(LiftLabel.BENCH_PRESS, (dbHelper.retrieveCurrentMaxes().getBenchMax() + (i * upperIncrement)));
            Map<Integer, List<ExerciseSet>> pressMap = setBuilder.buildAllFourWeeksCompoundSets(LiftLabel.PRESS, (dbHelper.retrieveCurrentMaxes().getPressMax() +  (i * upperIncrement)));
            Map<Integer, List<ExerciseSet>> deadliftMap = setBuilder.buildAllFourWeeksCompoundSets(LiftLabel.DEADLIFT, dbHelper.retrieveCurrentMaxes().getDeadliftMax() +  (i * lowerIncrement));
            //write wave number to file
            writer.writeNext(blankArray);
            writer.writeNext(new String[]{"", "WAVE " + (i + 1), ""});
            //loop each map, write each week to file, with labelling
            for (int x = 0; x < 4; x++) {
                //write blank lineh
                writer.writeNext(blankArray);
                writer.writeNext(new String[]{"", "WEEK " + (x + 1), ""});
                //Squat sets
                for (ExerciseSet set : squatMap.get(x)) {
                    writer.writeNext(set.getSetInfoAsStringArray());
                }
                writer.writeNext(blankArray);
                //bench sets
                for (ExerciseSet set : benchMap.get(x)) {
                    writer.writeNext(set.getSetInfoAsStringArray());
                }
                writer.writeNext(blankArray);
                //press sets
                for (ExerciseSet set : pressMap.get(x)) {
                    writer.writeNext(set.getSetInfoAsStringArray());
                }
                writer.writeNext(blankArray);
                //deadlift
                for (ExerciseSet set : deadliftMap.get(x)) {
                    writer.writeNext(set.getSetInfoAsStringArray());
                }
            }
            //write a perc table to csv file
            String[] labels = new String[]{"", String.valueOf(dbHelper.retrieveCurrentMaxes().getSquatMax()), String.valueOf(dbHelper.retrieveCurrentMaxes().getBenchMax()), String.valueOf(dbHelper.retrieveCurrentMaxes().getDeadliftMax()), String.valueOf(dbHelper.retrieveCurrentMaxes().getPressMax())};
            //increment by 5%
            double incrementAmount = .05;
            //write headers
            if (appendTable) {
                writer.writeNext(blankArray);
                writer.writeNext(labels);
                for (int j = 1; j <= 30; j++) {
                    String[] row = new String[]{String.valueOf(j * incrementAmount * 100) + "%", String.valueOf(((j * incrementAmount) * dbHelper.retrieveCurrentMaxes().getSquatMax())), String.valueOf(((j * incrementAmount) * dbHelper.retrieveCurrentMaxes().getBenchMax())), String.valueOf(((j * incrementAmount) * dbHelper.retrieveCurrentMaxes().getDeadliftMax())), String.valueOf(((j * incrementAmount) * dbHelper.retrieveCurrentMaxes().getPressMax()))};
                    writer.writeNext(row);
                }
            }
        }

        //intent for sharing with other apps
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/csv");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(csvFile));
        activity.startActivity(Intent.createChooser(sharingIntent, "Open with..."));
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
