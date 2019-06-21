package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.commonModel.LocationModel;
import com.plotalong.android.model.commonModel.LocationParsingModel;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.LocationModelParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 11/9/17.
 */

public class TrnTraceDetailsDataSource extends DatabaseHelper {
    private Context context;
    public static String TABLE_NAME_TRACE_DETAILS = "trn_trace_details";
    public static String COLUMN_CLIENT_TRACE_ID = "trn_client_trace_id";
    public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";
    public static String TIME = "time";
    public static String ACCURACY = "accuracy";
    public static String ALTITUDE = "altitude";
    public static String TRACE_ID = "trace_id";
    public static String COLUMN_TRACE_SYNC_STATUS = "trace_sync_status";


    public static String CREATE_TABLE_TRACE_DETAILS = "CREATE TABLE "
            + TABLE_NAME_TRACE_DETAILS + "( "
            + COLUMN_CLIENT_TRACE_ID
            + " INTEGER PRIMARY KEY,"
            + TIME
            + " REAL,"
            + LATITUDE
            + " REAL,"
            + LONGITUDE
            + " REAL,"
            + ACCURACY
            + " REAL,"
            + ALTITUDE
            + " TEXT,"
            + TRACE_ID
            + " TEXT,"
            + COLUMN_TRACE_SYNC_STATUS
            + " TEXT"
            + ")";
    public static String DELETE_TABLE_LOCATION = "DROP TABLE IF EXISTS " + TABLE_NAME_TRACE_DETAILS;
    private SQLiteDatabase db;
    private String TAG = TrnTraceDetailsDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private String[] allColumns = {TIME, LATITUDE, LONGITUDE, ACCURACY, ALTITUDE, TRACE_ID,
            COLUMN_TRACE_SYNC_STATUS};

    public TrnTraceDetailsDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
        this.context = context;
    }

    public void insertLocationDetails(LocationModel mLocation) {
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(LATITUDE, mLocation.getLatitude());
        values.put(LONGITUDE, mLocation.getLongitude());
        values.put(TIME, DateUtil.getCurrentFormatDateAndTime());
        values.put(ACCURACY, mLocation.getAccuracy());
        values.put(ALTITUDE, mLocation.getAltitude());
        values.put(TRACE_ID, mLocation.getTraceId());
        values.put(COLUMN_TRACE_SYNC_STATUS, GlobalConstant.INSERTED);
        long insertedCount = db.insert(TABLE_NAME_TRACE_DETAILS, null, values);
        Log.e(TAG, "insertLocationDetails: " + insertedCount);
        db.close();
    }

    public List<LocationModel> getSiteVisitRecordList(String traceId) {
        List<LocationModel> list = new ArrayList<>();
        db = getDatabase();
        String whereClause = TRACE_ID + " = ?";
        String[] whereArgs = {traceId};
        Cursor cursor = db.query(TABLE_NAME_TRACE_DETAILS, allColumns, whereClause, whereArgs, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.e(TAG, "getSiteVisitRecordList: ");
            list.add(cursorToModel(cursor));
        }
        db.close();
        return list;
    }

    private LocationModel cursorToModel(Cursor cursor) {
        LocationModel location = new LocationModel();
        location.setTime(cursor.getLong(cursor.getColumnIndex(TIME)));
        location.setLatitude(cursor.getDouble(cursor.getColumnIndex(LATITUDE)));
        location.setLongitude(cursor.getDouble(cursor.getColumnIndex(LONGITUDE)));
        location.setAltitude(cursor.getDouble(cursor.getColumnIndex(ALTITUDE)));
        location.setAccuracy(cursor.getFloat(cursor.getColumnIndex(ACCURACY)));
        location.setTraceId(cursor.getString(cursor.getColumnIndex(TRACE_ID)));
        location.setTrace_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_TRACE_SYNC_STATUS)));
        return location;
    }

    public ArrayList<LocationParsingModel> getAllTraces() {
        Log.e(TAG, "getAllTraces: ");
        ArrayList<LocationModel> locationModelArrayList = new ArrayList<>();
        db = getDatabase();
        Cursor cursor = db.query(TABLE_NAME_TRACE_DETAILS, allColumns, null, null, null, null, null);//db.query(TABLE_NAME_TRACE_DETAILS,allColumns,null, null, null, null, null);
        if (cursor.getCount() > 0)
            while (cursor.moveToNext()) {
                locationModelArrayList.add(cursorToModel(cursor));
            }
        db.close();
        ArrayList<LocationParsingModel> parsingModelArrayList = new LocationModelParser(context).LocationLocalToServerParse(locationModelArrayList);
        return parsingModelArrayList;
    }

    public void deleteDirtyTrace(ArrayList<LocationParsingModel> locationParsingModelArrayList) {
        Log.e(TAG, "deleteDirtyTrace: ");
        db = getDatabase();
        for (int i = 0; i < locationParsingModelArrayList.size(); i++) {
            LocationParsingModel locationParsingModel = locationParsingModelArrayList.get(i);
            String whereClause = COLUMN_CLIENT_TRACE_ID + " = ? AND " + COLUMN_TRACE_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(locationParsingModel.getClinet_trace_id()), locationParsingModel.getTrace_sync_status()};
            int deletedId = db.delete(TABLE_NAME_TRACE_DETAILS, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyTrace: " + deletedId);
        }
        db.close();
    }
}
