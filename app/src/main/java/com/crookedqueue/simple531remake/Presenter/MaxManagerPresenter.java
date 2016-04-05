package com.crookedqueue.simple531remake.Presenter;

import android.content.Context;

import com.crookedqueue.simple531remake.Model.DataBaseClassModels.DbHelper;
import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType;
import com.crookedqueue.simple531remake.Model.DataBaseClassModels.MaxesContainer;

import java.util.HashMap;
import java.util.Map;

import static com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.LiftType.*;

/**
 * This will sit at the heart of the update maxes fragment.  It has methods for managing maxes over the lifetime of the users use of the program.
 * It has to do the following
 * -retrieve most recent maxes from db
 * -insert maxes into db upon incrementation or update
 * -
 */
public class MaxManagerPresenter {
    private static final double upperIncrementImpVal = 5.00d;
    private static final double lowerIncrementImpVal = 10.00d;
    private static final double upperIncrementKgVal = 2.27d;
    private static final double lowerIncrementKgVal = 4.54d;

    public MaxManagerPresenter() {

    }

    public MaxesContainer incrementAndInsertMaxes(Context context, boolean isUseKg) {
        MaxesContainer oldMaxes = retriveCurrentMaxes(context);
        MaxesContainer newMaxes;
        if (!isUseKg) {
            newMaxes = new MaxesContainer((oldMaxes.getSquatMax() + lowerIncrementImpVal),
                    oldMaxes.getBenchMax() + upperIncrementImpVal,
                    oldMaxes.getDeadliftMax() + lowerIncrementImpVal,
                    oldMaxes.getPressMax() + upperIncrementImpVal);
        } else {
            newMaxes = new MaxesContainer((oldMaxes.getSquatMax() + lowerIncrementKgVal),
                    oldMaxes.getBenchMax() + upperIncrementKgVal,
                    oldMaxes.getDeadliftMax() + lowerIncrementKgVal,
                    oldMaxes.getPressMax() + upperIncrementKgVal);
        }
        insertNewMaxes(context, newMaxes);
        return newMaxes;
    }

    private void insertNewMaxes(Context context, MaxesContainer newMaxes) {
        MaxesContainer container = new MaxesContainer(newMaxes.getSquatMax(), newMaxes.getBenchMax(),
                newMaxes.getDeadliftMax(), newMaxes.getPressMax());
        DbHelper.getInstance(context)
                .insertNewMaxes(container);
    }

    public MaxesContainer retriveCurrentMaxes(Context context) {
        MaxesContainer container;
        container = DbHelper.getInstance(context)
                .retrieveCurrentMaxes();
        return container;
    }

    public Map<LiftType, String> maxesToString(MaxesContainer container){
        Map<LiftType, String> preparedMap = new HashMap<>();
        preparedMap.put(SQUAT, String.valueOf(container.getSquatMax()));
        preparedMap.put(BENCH_PRESS, String.valueOf(container.getBenchMax()));
        preparedMap.put(DEADLIFT, String.valueOf(container.getDeadliftMax()));
        preparedMap.put(OVERHEAD_PRESS, String.valueOf(container.getPressMax()));
        return preparedMap;
    }
}
