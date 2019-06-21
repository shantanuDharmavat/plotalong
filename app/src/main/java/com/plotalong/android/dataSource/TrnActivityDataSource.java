package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.ActivityDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by shantanu on 27/11/17.
 */

public class TrnActivityDataSource extends DatabaseHelper {
    private static final String TABLE_TRN_ACTIVITY = "trn_activity";

    public static final String DELETE_TABLE_TRN_ACTIVITY = "DROP TABLE IF EXISTS " + TABLE_TRN_ACTIVITY;

    private static final String COLUMN_ACTI_ID = "acti_id";
    private static final String COLUMN_ACTI_KEY = "acti_key";
    private static final String COLUMN_ACTI_TYPE = "acti_type";
    private static final String COLUMN_ACTI_SESSION_ID = "acti_session_id";
    private static final String COLUMN_ACTI_CUSTOMER_ID = "acti_customer_id";
    private static final String COLUMN_ACTI_PLOT_ID = "acti_plot_id";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_UPDATED_AT = "updated_at";
    private static final String COlUMN_DELETED_AT = "deleted_at";

    public static final String CREATE_TABLE_DETAILS = "CREATE TABLE "
            + TABLE_TRN_ACTIVITY + "("
            + COLUMN_ACTI_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_ACTI_KEY
            + " TEXT,"
            + COLUMN_ACTI_TYPE
            + " TEXT,"
            + COLUMN_ACTI_SESSION_ID
            + " TEXT,"
            + COLUMN_ACTI_CUSTOMER_ID
            + " TEXT,"
            + COLUMN_ACTI_PLOT_ID
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COlUMN_DELETED_AT
            + " TEXT"
            + ")";

    public static final String allColumns[] =
            {COLUMN_ACTI_ID,
                    COLUMN_ACTI_KEY,
                    COLUMN_ACTI_TYPE,
                    COLUMN_ACTI_SESSION_ID,
                    COLUMN_ACTI_CUSTOMER_ID,
                    COLUMN_ACTI_PLOT_ID,
                    COLUMN_CREATED_AT,
                    COLUMN_UPDATED_AT,
                    COlUMN_DELETED_AT};

    public String TAG = getClass().getSimpleName();
    private SQLiteDatabase db;

    public TrnActivityDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertActivity(ArrayList<ActivityDataModel> activityDataModelArrayList) {
        Log.e(TAG, "insertActivity: ");
        db = getDatabase();
        ActivityDataModel activityDataModel;
        for (int i = 0; i < activityDataModelArrayList.size(); i++) {
            activityDataModel = activityDataModelArrayList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ACTI_KEY, activityDataModel.getActi_key());
            contentValues.put(COLUMN_ACTI_TYPE, activityDataModel.getActi_type());
            contentValues.put(COLUMN_ACTI_SESSION_ID, activityDataModel.getActi_session_id());
            contentValues.put(COLUMN_ACTI_CUSTOMER_ID, activityDataModel.getActi_customer_id());
            contentValues.put(COLUMN_ACTI_PLOT_ID, activityDataModel.getActi_plot_id());
            contentValues.put(COLUMN_CREATED_AT, activityDataModel.getCreated_at());
            contentValues.put(COLUMN_UPDATED_AT, activityDataModel.getUpdated_at());
            contentValues.put(COlUMN_DELETED_AT, activityDataModel.getDeleted_at());

            long insertCount = db.insert(TABLE_TRN_ACTIVITY, null, contentValues);
            Log.e(TAG, "insertActivity: insertCount: " + insertCount);

        }
        db.close();
    }

    public void insertActivity(ActivityDataModel activityDataModel) {
        Log.e(TAG, "insertActivity: ");
        db = getDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACTI_KEY, activityDataModel.getActi_key());
        contentValues.put(COLUMN_ACTI_TYPE, activityDataModel.getActi_type());
        contentValues.put(COLUMN_ACTI_SESSION_ID, activityDataModel.getActi_session_id());
        contentValues.put(COLUMN_ACTI_CUSTOMER_ID, activityDataModel.getActi_customer_id());
        contentValues.put(COLUMN_ACTI_PLOT_ID, activityDataModel.getActi_plot_id());
        contentValues.put(COLUMN_CREATED_AT, activityDataModel.getCreated_at());
        contentValues.put(COLUMN_UPDATED_AT, activityDataModel.getUpdated_at());
        contentValues.put(COlUMN_DELETED_AT, activityDataModel.getDeleted_at());

        long insertCount = db.insert(TABLE_TRN_ACTIVITY, null, contentValues);
        Log.e(TAG, "insertActivity: insertCount: " + insertCount);

        db.close();
    }

    public boolean isPlotAlreadyLiked(int cust_unique_id, int plotId) {
        Log.e(TAG, "isPlotAlreadyLiked: ");
        db = getDatabase();
        String where = COLUMN_ACTI_CUSTOMER_ID + " = ? AND " + COLUMN_ACTI_PLOT_ID + " = ? AND " + COLUMN_ACTI_TYPE + " = ? ";
        String[] whereArgs = {String.valueOf(cust_unique_id), String.valueOf(plotId), GlobalConstant.TRN_ACTIVITY_TYPE_LIKE};
        Cursor cursor = db.query(TABLE_TRN_ACTIVITY, allColumns, where, whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            db.close();
            cursor.close();
            return true;
        }
        db.close();
        cursor.close();
        return false;
    }
}
