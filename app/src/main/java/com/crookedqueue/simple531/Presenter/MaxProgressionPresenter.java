package com.crookedqueue.simple531.Presenter;

import android.content.Context;

import com.crookedqueue.simple531.Model.DatabaseClassModels.DbHelper;
import com.crookedqueue.simple531.Model.DatabaseClassModels.MaxesContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 11/14/2016.
 */

public class MaxProgressionPresenter {
    Context context;
    DbHelper dbHelper;

    public MaxProgressionPresenter(Context context) {
        this.context = context;
        dbHelper = DbHelper.getInstance(context);
    }


}
