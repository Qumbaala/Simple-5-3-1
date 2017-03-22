package com.crookedqueue.simple531.Model.DatabaseClassModels;

import com.crookedqueue.simple531.Model.ExerciseSetBuilding.LiftLabel;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes in aggregate data and converts it to data points for graphing.
 */

public class SeriesCreator {
    public SeriesCreator() {
    }

    public List<String> getDatesFromMaxContainers(DbHelper helper) {
        List<MaxesContainer> maxContainers = helper.retrieveAllMaxes();
        List<String> dateList = new ArrayList<>();
        for (MaxesContainer container : maxContainers) {
            dateList.add(container.getFormattedDate());
        }
        return dateList;
    }

    public List<Entry> createMaxProgressionDataPoints(LiftLabel liftLabel, DbHelper dbHelper) {
        List<MaxesContainer> containers = dbHelper.retrieveAllMaxes();
        List<Entry> dataPoints = new ArrayList<>();
        float i = 0;
        //must add at least once piece of information or else crashes
        dataPoints.add(new Entry(i, 0, 0));
        for (MaxesContainer container : containers) {
            dataPoints.add(new Entry(i + 1, (float) container.getMaxFromLiftLabel(liftLabel), container.getFormattedDate()));
            i++;
        }
        return dataPoints;
    }

    public List<Entry> createPersonalRecordDataPoints(LiftLabel liftLabel, DbHelper dbHelper){
        List<PersonalRecord> personalRecords = dbHelper.retrieveAllPersonalRecords();
        List<Entry> dataPoints = new ArrayList<>();
        float i = 0;
        //must add at least once piece of information or else crashes
        dataPoints.add(new Entry(i, 0, 0));
        for (PersonalRecord personalRecord : personalRecords){
            if (personalRecord.getLiftLabel() == liftLabel) {
                dataPoints.add(new Entry((float) personalRecord.getReps(), (float) personalRecord.getWeight(), personalRecord.getDateAndNote()));
            }
        }
        return dataPoints;
    }
}
