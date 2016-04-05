package com.crookedqueue.simple531remake.Model.DataBaseClassModels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crookedqueue.simple531remake.Model.ExerciseSetBuilding.ExerciseSet;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Build out completed sets queries in order to feed graphs.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper dbHelper = null;
    private static final String DATABASE_NAME = "Simple_5_3_1";
    private static final int DATABASE_VERSION = 3;

    private static final String MAXES_TABLE_NAME = "maxes_table";
    private static final String COLUMN_SQUAT_MAX = "_squat_max";
    private static final String COLUMN_BENCH_MAX = "_bench_max";
    private static final String COLUMN_DEADLIFT_MAX = "_deadlift_max";
    private static final String COLUMN_PRESS_MAX = "_press_max";
    private static final String COLUMN_MAXES_DATE = "_date";

    private static final String COMPLETED_SETS_TABLE_NAME = "Completed_Sets_Table";
    private static final String COLUMN_COMPLETED_SETS_LIFT_NAME = "_lift_name";
    private static final String COLUMN_COMPLETED_SETS_WEIGHT = "_set_weight";
    private static final String COLUMN_COMPLETED_SETS_REPS = "_set_reps";
    private static final String COLUMN_COMPLETED_SETS_DATE = "_date";

    private static final String PERSONAL_RECORDS_TABLE_NAME = "Personal_Records";
    private static final String COLUMN_PERSONAL_RECORDS_LIFT_NAME = "_lift_name";
    private static final String COLUMN_PERSONAL_RECORD_WEIGHT = "_record_weight";
    private static final String COLUMN_PERSONAL_RECORD_REPS = "_record_reps";
    private static final String COLUMN_PERSONAL_RECORD_NOTE = "_record_note";
    private static final String COLUMN_PERSONAL_RECORD_DATE = "_record_date";

    private Context context;



    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }

        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAXES_TABLE = "CREATE TABLE " + MAXES_TABLE_NAME + "(" + COLUMN_SQUAT_MAX + " REAL," + COLUMN_BENCH_MAX + " REAL," + COLUMN_DEADLIFT_MAX + " REAL," + COLUMN_PRESS_MAX + " REAL," + COLUMN_MAXES_DATE + " INTEGER PRIMARY KEY" + ")";
        String CREATE_COMPLETED_SETS_TABLE = "CREATE TABLE " + COMPLETED_SETS_TABLE_NAME + "(" + COLUMN_COMPLETED_SETS_LIFT_NAME + " TEXT," + COLUMN_COMPLETED_SETS_WEIGHT + " REAL," + COLUMN_COMPLETED_SETS_REPS + " INTEGER," + COLUMN_COMPLETED_SETS_DATE + " INTEGER PRIMARY KEY" + ")";
        String CREATE_PERSONAL_RECORDS_TABLE = "CREATE TABLE " + PERSONAL_RECORDS_TABLE_NAME + "(" + COLUMN_PERSONAL_RECORDS_LIFT_NAME + " TEXT," + COLUMN_PERSONAL_RECORD_WEIGHT + " REAL," + COLUMN_PERSONAL_RECORD_REPS + " INTEGER," + COLUMN_PERSONAL_RECORD_NOTE + " TEXT," + COLUMN_PERSONAL_RECORD_DATE + " INTEGER PRIMARY KEY" + ")";
        db.execSQL(CREATE_MAXES_TABLE);
        db.execSQL(CREATE_COMPLETED_SETS_TABLE);
        db.execSQL(CREATE_PERSONAL_RECORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (this.context != null){
            db.close();
            context.deleteDatabase(DATABASE_NAME);
            this.onCreate(db);
        }
    }

    public void insertCompletedSet(ExerciseSet set){
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPLETED_SETS_LIFT_NAME, set.getLabel());
        values.put(COLUMN_COMPLETED_SETS_WEIGHT, set.getRoundedWeight());
        values.put(COLUMN_COMPLETED_SETS_REPS, set.getReps());
        values.put(COLUMN_COMPLETED_SETS_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(COMPLETED_SETS_TABLE_NAME, null, values);
    }

    public void insertNewMaxes(MaxesContainer container){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SQUAT_MAX, container.getSquatMax());
        values.put(COLUMN_BENCH_MAX, container.getBenchMax());
        values.put(COLUMN_DEADLIFT_MAX, container.getDeadliftMax());
        values.put(COLUMN_PRESS_MAX, container.getPressMax());
        values.put(COLUMN_MAXES_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(MAXES_TABLE_NAME, null, values);
    }

    public void insertPersonalRecord(PersonalRecord record){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSONAL_RECORDS_LIFT_NAME, record.getLabel());
        values.put(COLUMN_PERSONAL_RECORD_WEIGHT, record.getWeight());
        values.put(COLUMN_PERSONAL_RECORD_REPS, record.getReps());
        values.put(COLUMN_PERSONAL_RECORD_NOTE, record.getNote());
        values.put(COLUMN_PERSONAL_RECORD_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
    }

    public MaxesContainer retrieveCurrentMaxes(){
        String query = "SELECT * FROM " + MAXES_TABLE_NAME + " WHERE " + COLUMN_MAXES_DATE + " = " + "(SELECT MAX(_date) FROM " + MAXES_TABLE_NAME + ")";
        MaxesContainer container = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            container = new MaxesContainer(cursor.getDouble(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3));
        }
        cursor.close();
        db.close();
        return container;
    }

    public List<PersonalRecord> retrieveAllPersonalRecords(){
        String query = "SELECT * FROM " + PERSONAL_RECORDS_TABLE_NAME;
        List<PersonalRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                PersonalRecord pr = new PersonalRecord(cursor.getString(0), cursor.getDouble(1), cursor.getInt(2), cursor.getString(3), cursor.getLong(4));
                records.add(pr);
            }
        }
        return records;
    }


}
