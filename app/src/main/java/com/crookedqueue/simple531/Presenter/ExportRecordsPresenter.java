package com.crookedqueue.simple531.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.MaxesContainer;
import com.crookedqueue.simple531.Model.DatabaseClassModels.PersonalRecord;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jason on 3/20/2017.
 */

public class ExportRecordsPresenter {
    private String[] maxHeaderHeaders = new String[]{"Date", "Squat Max", "Bench Max", "Deadlift Max", "Press Max"};
    private String[] personalRecordsHeaders = new String[]{"Date", "Lift", "Weight", "Reps", "Note"};
    private  String MAX_FILE_NAME = "Simple531_Maxes_" + DATE + ".csv";
    private  String PR_FILE_NAME = "Simple531_Personal_Records_" + DATE + ".csv";
    private static final String DATE = new SimpleDateFormat("MM-yyyy", Locale.getDefault()).format(new Date());
    public ExportRecordsPresenter() {
    }

    public boolean exportMaxes(AppCompatActivity activity){
        DbHelper helper = DbHelper.getInstance(activity);
        List<MaxesContainer> maxContainers = helper.retrieveAllMaxes();
        File maxFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), MAX_FILE_NAME);
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(maxFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //write headers to the file
        writer.writeNext(maxHeaderHeaders);

        for (MaxesContainer container :
                maxContainers) {
            writer.writeNext(container.getMaxContainerStringArray());
        }

        //share intent to export data
        //intent for sharing with other apps
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/csv");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(maxFile));
        activity.startActivity(Intent.createChooser(sharingIntent, "Open with..."));
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean exportPersonalRecords(AppCompatActivity activity){
        DbHelper helper = DbHelper.getInstance(activity);
        List<PersonalRecord> maxContainers = helper.retrieveAllPersonalRecords();
        File prFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), PR_FILE_NAME);
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(prFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //write headers to the file
        writer.writeNext(personalRecordsHeaders);

        for (PersonalRecord pr :
                maxContainers) {
            writer.writeNext(pr.getPersonalRecordAsStringArray());
        }

        //share intent to export data
        //intent for sharing with other apps
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/csv");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(prFile));
        activity.startActivity(Intent.createChooser(sharingIntent, "Open with..."));
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
