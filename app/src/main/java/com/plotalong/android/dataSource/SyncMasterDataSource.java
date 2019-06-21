package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.syncMaster.SyncMasterDataModel;
import com.plotalong.android.util.GlobalConstant;

/**
 * Created by kbhakade on 17/11/17.
 */

public class SyncMasterDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_SYNC_MASTER = "sync_master";
    public static final String COLUMN_ENVL_ID = "envl_id";
    public static final String COLUMN_JSON_STRING = "json_string";
    public static final String COLUMN_LST = "lst";


    public static final String CREATE_TABLE_SYNC_MASTER = "CREATE TABLE "
            + TABLE_NAME_SYNC_MASTER + "("
            + COLUMN_ENVL_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_JSON_STRING
            + " TEXT,"
            + COLUMN_LST
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_SYNC_MASTER = "DROP TABLE IF EXISTS " + TABLE_NAME_SYNC_MASTER;
    public static String[] allColumns = {
            COLUMN_ENVL_ID,
            COLUMN_JSON_STRING,
            COLUMN_LST
    };
    private String TAG = SyncMasterDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public SyncMasterDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertJsonString(SyncMasterDataModel syncMasterDataModel) {
        Log.e(TAG, "insertCfgFeaturesOfServer: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JSON_STRING, syncMasterDataModel.getJson_string());
        values.put(COLUMN_LST, syncMasterDataModel.getLst());
        long insertedCount = db.insert(TABLE_NAME_SYNC_MASTER, null, values);
        Log.e(TAG, "insertCfgFeaturesOfServer: " + insertedCount);
        db.close();
    }

    public boolean checkIsTableEmpty() {
        Log.e(TAG, "checkTableIsEmpty: ");
        db = getDatabase();
        Cursor cursor = db.query(TABLE_NAME_SYNC_MASTER, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public String getRequestJson() {
        Log.e(TAG, "getRequestJson: ");
        db = getDatabase();
        String jsonString = null;
        Cursor cursor = db.query(TABLE_NAME_SYNC_MASTER, allColumns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            jsonString = cursor.getString(cursor.getColumnIndex(COLUMN_JSON_STRING));
            cursor.close();
        }
        db.close();
        return jsonString;
    }

    public void deleteRecord() {
        Log.e(TAG, "deleteRecord: ");
        db = getDatabase();
        int deleted = db.delete(TABLE_NAME_SYNC_MASTER, null, null);
        Log.e(TAG, "deleteRecord: " + deleted);
        db.close();
    }
}

