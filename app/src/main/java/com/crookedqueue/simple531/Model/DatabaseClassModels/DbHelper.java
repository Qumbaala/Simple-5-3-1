package com.crookedqueue.simple531.Model.DatabaseClassModels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.crookedqueue.simple531.Model.ExerciseSetBuilding.ExerciseSet;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetListBuildingUtils;
import com.crookedqueue.simple531.Model.ExerciseSetBuilding.SetType;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Build out completed sets queries in order to feed graphs.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper dbHelper = null;
    private static final String DATABASE_NAME = "Simple_5_3_1";
    private static final int DATABASE_VERSION = 1;

    private static final String MAXES_TABLE_NAME = "maxes_table";
    private static final String COLUMN_SQUAT_MAX = "_squat_max";
    private static final String COLUMN_BENCH_MAX = "_bench_max";
    private static final String COLUMN_DEADLIFT_MAX = "_deadlift_max";
    private static final String COLUMN_PRESS_MAX = "_press_max";
    private static final String COLUMN_MAXES_DATE = "_date";

    private static final String COMPLETED_SETS_TABLE_NAME = "Completed_Sets_Table";
    private static final String COLUMN_COMPLETED_SETS_LIFT_LABEL_INT = "_lift_label_int";
    private static final String COLUMN_COMPLETED_SETS_WEIGHT = "_set_weight";
    private static final String COLUMN_COMPLETED_SETS_REPS = "_set_reps";
    private static final String COLUMN_COMPLETED_SETS_DATE = "_date";

    private static final String PERSONAL_RECORDS_TABLE_NAME = "Personal_Records";
    private static final String COLUMN_PERSONAL_RECORDS_LIFT_LABEL_INT = "pr_lift_label_int";
    private static final String COLUMN_PERSONAL_RECORD_WEIGHT = "_record_weight";
    private static final String COLUMN_PERSONAL_RECORD_REPS = "_record_reps";
    private static final String COLUMN_PERSONAL_RECORD_NOTE = "_record_note";
    private static final String COLUMN_PERSONAL_RECORD_DATE = "_record_date";

    private static final String CYCLE_SETTINGS_TABLE_NAME = "Cycle_Settings";
    private static final String COLUMN_ASSISTANCE_TYPE = "_assistance_type";
    private static final String COLUMN_IS_USE_FSL = "_use_fsl";
    private static final String COLUMN_IS_ROUND_UP = "_round_up";
    private static final String COLUMN_IS_KG = "_units";

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
        String CREATE_MAXES_TABLE = "CREATE TABLE " + MAXES_TABLE_NAME + "(" + COLUMN_SQUAT_MAX + " REAL DEFAULT 0," + COLUMN_BENCH_MAX + " REAL DEFAULT 0," + COLUMN_DEADLIFT_MAX + " REAL DEFAULT 0," + COLUMN_PRESS_MAX + " REAL DEFAULT 0," + COLUMN_MAXES_DATE + " INTEGER PRIMARY KEY DEFAULT " + System.currentTimeMillis() + ")";
        String CREATE_COMPLETED_SETS_TABLE = "CREATE TABLE " + COMPLETED_SETS_TABLE_NAME + "(" + COLUMN_COMPLETED_SETS_LIFT_LABEL_INT + " INTEGER," + COLUMN_COMPLETED_SETS_WEIGHT + " REAL," + COLUMN_COMPLETED_SETS_REPS + " INTEGER," + COLUMN_COMPLETED_SETS_DATE + " INTEGER PRIMARY KEY" + ")";
        String CREATE_PERSONAL_RECORDS_TABLE = "CREATE TABLE " + PERSONAL_RECORDS_TABLE_NAME + "(" + COLUMN_PERSONAL_RECORDS_LIFT_LABEL_INT + " INTEGER," + COLUMN_PERSONAL_RECORD_WEIGHT + " REAL," + COLUMN_PERSONAL_RECORD_REPS + " INTEGER," + COLUMN_PERSONAL_RECORD_NOTE + " TEXT," + COLUMN_PERSONAL_RECORD_DATE + " INTEGER PRIMARY KEY" + ")";
        String CREATE_SETTINGS_TABLE = "CREATE TABLE " + CYCLE_SETTINGS_TABLE_NAME + "(" + COLUMN_ASSISTANCE_TYPE + " INTEGER DEFAULT 5," + COLUMN_IS_USE_FSL + " INTEGER DEFAULT 0," + COLUMN_IS_ROUND_UP + " INTEGER DEFAULT 0," + COLUMN_IS_KG + " INTEGER DEFAULT 0" + ")";
        db.execSQL(CREATE_MAXES_TABLE);
        db.execSQL(CREATE_COMPLETED_SETS_TABLE);
        db.execSQL(CREATE_PERSONAL_RECORDS_TABLE);
        db.execSQL(CREATE_SETTINGS_TABLE);
        db.execSQL("INSERT INTO " + CYCLE_SETTINGS_TABLE_NAME + " DEFAULT VALUES");
        db.execSQL("INSERT INTO " + MAXES_TABLE_NAME + " DEFAULT VALUES");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (this.context != null) {
            db.close();
            context.deleteDatabase(DATABASE_NAME);
            this.onCreate(db);
        }
    }

    public void insertCompletedSet(ExerciseSet set) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPLETED_SETS_LIFT_LABEL_INT, set.getIntLiftLabel());
        values.put(COLUMN_COMPLETED_SETS_WEIGHT, set.getRoundedWeight());
        values.put(COLUMN_COMPLETED_SETS_REPS, set.getReps());
        values.put(COLUMN_COMPLETED_SETS_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(COMPLETED_SETS_TABLE_NAME, null, values);
    }

    public void insertNewMaxes(MaxesContainer container) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SQUAT_MAX, container.getSquatMax());
        values.put(COLUMN_BENCH_MAX, container.getBenchMax());
        values.put(COLUMN_DEADLIFT_MAX, container.getDeadliftMax());
        values.put(COLUMN_PRESS_MAX, container.getPressMax());
        values.put(COLUMN_MAXES_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(MAXES_TABLE_NAME, null, values);
    }

    public void insertPersonalRecord(PersonalRecord record) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSONAL_RECORDS_LIFT_LABEL_INT, record.getIntLiftType()); //is mapped to lift label, must not change values
        values.put(COLUMN_PERSONAL_RECORD_WEIGHT, record.getWeight());
        values.put(COLUMN_PERSONAL_RECORD_REPS, record.getReps());
        values.put(COLUMN_PERSONAL_RECORD_NOTE, record.getNote());
        values.put(COLUMN_PERSONAL_RECORD_DATE, System.currentTimeMillis()); //must be retrieved as long else signed negative
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PERSONAL_RECORDS_TABLE_NAME, null, values);
    }

    public void deletePersonalRecord(PersonalRecord record){
        String cmd = "DELETE FROM " + PERSONAL_RECORDS_TABLE_NAME + " WHERE " + COLUMN_PERSONAL_RECORD_DATE + " = " + record.getLongDate();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(cmd);
        db.close();
    }

    public MaxesContainer retrieveCurrentMaxes() {
        String query = "SELECT * FROM " + MAXES_TABLE_NAME + " WHERE " + COLUMN_MAXES_DATE + " = " + "(SELECT MAX(_date) FROM " + MAXES_TABLE_NAME + ")";
        MaxesContainer container = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            container = new MaxesContainer(cursor.getDouble(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getLong(4));
        }
        cursor.close();
        db.close();
        return container;
    }

    public List<MaxesContainer> retriveAllMaxes(){
        String query = "SELECT * FROM " + MAXES_TABLE_NAME;
        List<MaxesContainer> maxes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            cursor.moveToNext(); //skipping default row to prevent people deleting it, causing null pointer and crash
            while (!cursor.isAfterLast()){
                MaxesContainer container = new MaxesContainer(cursor.getDouble(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getLong(4));
                maxes.add(container);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return maxes;
    }

    public void deleteMaxRecord(MaxesContainer maxContainer){
        String cmd = "DELETE FROM " + MAXES_TABLE_NAME + " WHERE " + COLUMN_MAXES_DATE + " = " + maxContainer.getLongDate();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(cmd);
        db.close();
    }

    public List<PersonalRecord> retrieveAllPersonalRecords() {
        String query = "SELECT * FROM " + PERSONAL_RECORDS_TABLE_NAME;
        List<PersonalRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                PersonalRecord pr = new PersonalRecord(cursor.getInt(0), cursor.getDouble(1), cursor.getInt(2), cursor.getString(3), cursor.getLong(4));
                records.add(pr);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return records;
    }

    public SetType retrieveAssistanceWork(){
        String query = "SELECT * FROM " + CYCLE_SETTINGS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            db.close();
            return SetListBuildingUtils.mapIntToSetType(cursor.getInt(0));
        }
        else{
            db.close();
            return null;
        }
    }

    public void updateAssistanceWork(SetType setType){
        int intToStore = SetListBuildingUtils.mapSetTypeToInt(setType);
        SQLiteDatabase db = this.getWritableDatabase();
        if (intToStore != -1){
            db.execSQL("UPDATE " + CYCLE_SETTINGS_TABLE_NAME + " SET " + COLUMN_ASSISTANCE_TYPE + " = " + intToStore);
        }
        else{
            Toast.makeText(context, "Unable to update assistance work.", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public boolean retrieveIsUseFsl(){
        String query = "SELECT * FROM " + CYCLE_SETTINGS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isUseFsl = false;
        if (cursor.moveToFirst()){
            isUseFsl = cursor.getInt(1) != 0;
        }
        else {
            Toast.makeText(context, "Unable to retrieve FSL setting", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return isUseFsl;
    }

    public void updateIsUseFsl(boolean isUseFsl){
        int intFromBool = isUseFsl ? 1:0;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + CYCLE_SETTINGS_TABLE_NAME + " SET " + COLUMN_IS_USE_FSL + " = " + intFromBool);
        db.close();
    }

    public boolean retrieveIsUseRoundUp(){
        String query = "SELECT * FROM " + CYCLE_SETTINGS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isUseRoundUp = false;
        if (cursor.moveToFirst()){
            isUseRoundUp = cursor.getInt(2) != 0;
        }
        else {
            Toast.makeText(context, "Unable to retrieve round-up setting", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return isUseRoundUp;
    }

    public void updateIsUseRoundUp(boolean isUseRoundUp){
        int intFromBool = isUseRoundUp ? 1:0;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + CYCLE_SETTINGS_TABLE_NAME + " SET " + COLUMN_IS_ROUND_UP + " = " + intFromBool);
        db.close();
    }

    public boolean retrieveIsUseKg(){
        String query = "SELECT * FROM " + CYCLE_SETTINGS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean isUseKg = false;
        if (cursor.moveToFirst()){
            isUseKg = cursor.getInt(3) != 0;
        }
        else {
            Toast.makeText(context, "Unable to retrieve units setting", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return isUseKg;
    }

    public void updateIsUseKg(boolean isUseKg){
        int intFromBool = isUseKg ? 1:0;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + CYCLE_SETTINGS_TABLE_NAME + " SET " + COLUMN_IS_KG + " = " + intFromBool);
        db.close();
    }

}
