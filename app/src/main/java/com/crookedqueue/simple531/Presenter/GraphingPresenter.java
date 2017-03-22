package com.crookedqueue.simple531.Presenter;

import android.content.Context;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.MaxesContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 11/14/2016.
 */

public class GraphingPresenter {
    Context context;
    public final int[] yAxisConstants = new int[]{0,100,200,300,400,500,600,700,800,900,1000};
    DbHelper dbHelper;

    public GraphingPresenter(Context context) {
        this.context = context;
        dbHelper = DbHelper.getInstance(context);
    }

    public float[] getSquatMaxes(){
        List<Float> squatMaxes = new ArrayList<>();
        DbHelper db = DbHelper.getInstance(context);
        for(MaxesContainer container: db.retrieveAllMaxes()){
            squatMaxes.add((float)container.getSquatMax());
        }
        float[] squatFloats = new float[squatMaxes.size()];
        for (int i = 0; i < squatMaxes.size(); i++) {
            squatFloats[i] = squatMaxes.get(i);
        }
        return squatFloats;

    }

    public long[] getMaxDates(){
        DbHelper db = DbHelper.getInstance(context);
        List<MaxesContainer> maxesContainers = db.retrieveAllMaxes();
        long[] dates = new long[maxesContainers.size()];
        for (int i = 0; i < maxesContainers.size(); i++) {
            dates[i] = maxesContainers.get(i).getLongDate();
        }
        return dates;
    }

    public int[] getyAxisConstants() {
        return yAxisConstants;
    }
}
