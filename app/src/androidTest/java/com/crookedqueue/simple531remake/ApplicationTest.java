package com.crookedqueue.simple531remake;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.mock.MockContext;

import com.crookedqueue.simple531remake.Model.DataBaseClassModels.DbHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    MockContext mockContext = new MockContext();
    DbHelper dbHelper = DbHelper.getInstance(this.getContext());

}