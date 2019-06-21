package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 11/9/17.
 */

public class TrnSessionDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_SESSION = "trn_session";
    public static final String COLUMN_SESS_ID = "sess_id";
    public static final String COLUMN_SESS_KEY = "sess_key";
    public static final String COLUMN_SESS_START_TIMESTAMP = "sess_start_timestamp";
    public static final String COLUMN_SESS_START_LAT = "sess_start_lat";
    public static final String COLUMN_SESS_END_LAT = "sess_end_lat";
    public static final String COLUMN_SESS_START_LONG = "sess_start_long";
    public static final String COLUMN_SESS_END_LONG = "sess_end_long";
    public static final String COLUMN_SESS_TYPE = "sess_type";
    public static final String COLUMN_SESS_CUST_UNIQUE_ID = "sess_cust_unique_id";
    public static final String COLUMN_SESS_GROUP_ID = "sess_group_id";
    public static final String COLUMN_SESS_USER_ID = "sess_user_id";
    public static final String COLUMN_SESS_SPID = "sess_spid";
    public static final String COLUMN_SESS_PROJECT_ID = "sess_project_id";
    public static final String COLUMN_SESS_PHASE_ID = "sess_phase_id";
    public static final String COLUMN_SESS_CUSTOMER_FEEDBACK = "sess_customer_feedback";
    public static final String COLUMN_SESS_USER_FEEDBACK = "sess_user_feedback";
    public static final String COLUMN_SESS_IMAGE_LOCATION = "sess_image_location";
    public static final String COLUMN_SESS_CREATED_BY = "created_by";
    public static final String COLUMN_SESS_UPDATED_BY = "updated_by";
    public static final String COLUMN_SESS_CREATED_AT = "created_at";
    public static final String COLUMN_SESS_UPDATED_AT = "updated_at";
    public static final String COLUMN_SESS_DELEATED_AT = "deleted_at";
    public static final String COLUMN_SESS_TRACE_ID_FK = "trace_id_fk";
    public static final String COLUMN_SESS_SEND_FLAG = "sess_send_flag";
    public static final String COLUMN_SESS_SYNC_STATUS = "sess_sync_status";


    public static final String CREATE_TABLE_SESSION_DETAILS = "CREATE TABLE "
            + TABLE_NAME_TRN_SESSION + "("
            + COLUMN_SESS_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_SESS_KEY
            + " TEXT,"
            + COLUMN_SESS_START_TIMESTAMP
            + " TEXT,"
            + COLUMN_SESS_START_LAT
            + " INTEGER,"
            + COLUMN_SESS_END_LAT
            + " INTEGER,"
            + COLUMN_SESS_START_LONG
            + " TEXT,"
            + COLUMN_SESS_END_LONG
            + " TEXT,"
            + COLUMN_SESS_TYPE
            + " TEXT,"
            + COLUMN_SESS_CUST_UNIQUE_ID
            + " TEXT,"
            + COLUMN_SESS_GROUP_ID
            + " TEXT,"
            + COLUMN_SESS_USER_ID
            + " TEXT,"
            + COLUMN_SESS_SPID
            + " TEXT,"
            + COLUMN_SESS_PROJECT_ID
            + " TEXT,"
            + COLUMN_SESS_PHASE_ID
            + " TEXT,"
            + COLUMN_SESS_CUSTOMER_FEEDBACK
            + " TEXT,"
            + COLUMN_SESS_USER_FEEDBACK
            + " TEXT,"
            + COLUMN_SESS_IMAGE_LOCATION
            + " TEXT,"
            + COLUMN_SESS_CREATED_BY
            + " TEXT,"
            + COLUMN_SESS_UPDATED_BY
            + " TEXT,"
            + COLUMN_SESS_CREATED_AT
            + " TEXT,"
            + COLUMN_SESS_UPDATED_AT
            + " TEXT,"
            + COLUMN_SESS_DELEATED_AT
            + " TEXT,"
            + COLUMN_SESS_TRACE_ID_FK
            + " TEXT,"
            + COLUMN_SESS_SEND_FLAG
            + " TEXT,"
            + COLUMN_SESS_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_SESSION_DETAILS = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_SESSION;

    public static final String allColumns[] = {COLUMN_SESS_ID, COLUMN_SESS_KEY, COLUMN_SESS_START_TIMESTAMP,
            COLUMN_SESS_START_LAT, COLUMN_SESS_END_LAT, COLUMN_SESS_CUST_UNIQUE_ID, COLUMN_SESS_START_LONG, COLUMN_SESS_END_LONG,
            COLUMN_SESS_TYPE, COLUMN_SESS_GROUP_ID, COLUMN_SESS_USER_ID, COLUMN_SESS_SPID, COLUMN_SESS_PROJECT_ID,
            COLUMN_SESS_PHASE_ID, COLUMN_SESS_CUSTOMER_FEEDBACK, COLUMN_SESS_USER_FEEDBACK, COLUMN_SESS_IMAGE_LOCATION,
            COLUMN_SESS_CREATED_BY, COLUMN_SESS_UPDATED_BY, COLUMN_SESS_CREATED_AT, COLUMN_SESS_UPDATED_AT,
            COLUMN_SESS_DELEATED_AT, COLUMN_SESS_TRACE_ID_FK, COLUMN_SESS_SEND_FLAG, COLUMN_SESS_SYNC_STATUS};

    private static final String TAG = TrnSessionDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public TrnSessionDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertSession(ArrayList<SessionDataModel> sessionDataModelArrayList) {
        Log.e(TAG, "insertSession: ");
        db = getDatabase();
        SessionDataModel sessionDataModel;
        for (int i = 0; i < sessionDataModelArrayList.size(); i++) {
            sessionDataModel = sessionDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_SESS_KEY, sessionDataModel.getSess_key());
            values.put(COLUMN_SESS_START_TIMESTAMP, sessionDataModel.getSess_start_timestamp());
            values.put(COLUMN_SESS_START_LAT, sessionDataModel.getSess_start_lat());
            values.put(COLUMN_SESS_END_LAT, sessionDataModel.getSess_end_lat());
            values.put(COLUMN_SESS_START_LONG, sessionDataModel.getSess_start_long());
            values.put(COLUMN_SESS_END_LONG, sessionDataModel.getSess_end_long());
            values.put(COLUMN_SESS_TYPE, sessionDataModel.getSess_type());
            values.put(COLUMN_SESS_CUST_UNIQUE_ID, sessionDataModel.getSess_cust_unique_id());
            values.put(COLUMN_SESS_GROUP_ID, sessionDataModel.getSess_group_id());
            values.put(COLUMN_SESS_USER_ID, sessionDataModel.getSess_user_id());
            values.put(COLUMN_SESS_SPID, sessionDataModel.getSess_spid());
            values.put(COLUMN_SESS_PROJECT_ID, sessionDataModel.getSess_project_id());
            values.put(COLUMN_SESS_PHASE_ID, sessionDataModel.getSess_phase_id());
            values.put(COLUMN_SESS_CUSTOMER_FEEDBACK, sessionDataModel.getSess_customer_feedback());
            values.put(COLUMN_SESS_USER_FEEDBACK, sessionDataModel.getSess_user_feedback());
            values.put(COLUMN_SESS_IMAGE_LOCATION, sessionDataModel.getSess_image_location());
            values.put(COLUMN_SESS_CREATED_BY, sessionDataModel.getCreated_by());
            values.put(COLUMN_SESS_UPDATED_BY, sessionDataModel.getUpdated_by());
            values.put(COLUMN_SESS_CREATED_AT, sessionDataModel.getCreated_at());
            values.put(COLUMN_SESS_UPDATED_AT, sessionDataModel.getUpdated_at());
            values.put(COLUMN_SESS_DELEATED_AT, sessionDataModel.getDeleted_at());
            values.put(COLUMN_SESS_TRACE_ID_FK, sessionDataModel.getSess_trac_id_fk());
            values.put(COLUMN_SESS_SEND_FLAG, sessionDataModel.getSess_end_flag());
            values.put(COLUMN_SESS_SYNC_STATUS, GlobalConstant.INSERTED);

            String whereClause = COLUMN_SESS_KEY + " = ? ";
            String whereArgs[] = {String.valueOf(sessionDataModel.getSess_id())};
            long updatedCount = db.update(TABLE_NAME_TRN_SESSION, values, whereClause, whereArgs);
            Log.e(TAG, "insertSession: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_TRN_SESSION, null, values);
                Log.e(TAG, "insertSession: insertedCount = " + insertedCount);
            }
        }
        Log.e(TAG, "insertCustomerGroup: CUSTOMER GROUP INSERTED SUCCESSFULLY");
        db.close();
    }

    public void closeSession(SessionDataModel sessionDataModel) {
        Log.e(TAG, "closeSession: Session Key = " + sessionDataModel.getSess_key());
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SESS_END_LAT, sessionDataModel.getSess_end_lat());
        values.put(COLUMN_SESS_END_LONG, sessionDataModel.getSess_end_long());
        values.put(COLUMN_SESS_SEND_FLAG, sessionDataModel.getSess_end_flag());

        String whereClause = COLUMN_SESS_KEY + " = ? ";
        String whereArgs[] = {sessionDataModel.getSess_key()};
        long updatedCount = db.update(TABLE_NAME_TRN_SESSION, values, whereClause, whereArgs);
        Log.e(TAG, "closeSession: Updated Count = " + updatedCount);
    }

    public void updateSession(String groupId, String traceId) {
        Log.e(TAG, "updateSession: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SESS_TRACE_ID_FK, traceId);
        String whereClause = COLUMN_SESS_GROUP_ID + " = ?";
        String[] whereArgs = {groupId};
        int updatedId = db.update(TABLE_NAME_TRN_SESSION, values, whereClause, whereArgs);
        Log.e(TAG, "updateSession: " + updatedId);
    }

    public SessionDataModel getSessionOfCustomer(String customerTempGroupId) {
        Log.e(TAG, "getSessionOfCustomer: ");
        SessionDataModel sessionDataModel = new SessionDataModel();
        db = getDatabase();
        String selection = COLUMN_SESS_GROUP_ID + " = ?";
        String selectionArgs[] = {String.valueOf(customerTempGroupId)};
        Cursor cursor = db.query(TABLE_NAME_TRN_SESSION, allColumns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            sessionDataModel = cursorToModel(cursor);
        }
        return sessionDataModel;
    }

    private SessionDataModel cursorToModel(Cursor cursor) {
        SessionDataModel sessionDataModel = new SessionDataModel();
        sessionDataModel.setSess_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_ID)));
        sessionDataModel.setSess_end_flag(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_SEND_FLAG)));
        sessionDataModel.setCreated_by(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_CREATED_BY)));
        sessionDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_UPDATED_AT)));
        sessionDataModel.setSess_key(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_KEY)));
        sessionDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_CREATED_AT)));
        sessionDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_DELEATED_AT)));
        sessionDataModel.setSess_customer_feedback(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_CUSTOMER_FEEDBACK)));
        sessionDataModel.setSess_start_lat(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_START_LAT)));
        sessionDataModel.setSess_start_long(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_START_LONG)));
        sessionDataModel.setSess_end_lat(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_END_LAT)));
        sessionDataModel.setSess_end_long(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_END_LONG)));
        sessionDataModel.setSess_group_id(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_GROUP_ID)));
        sessionDataModel.setSess_cust_unique_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_CUST_UNIQUE_ID)));
        sessionDataModel.setSess_image_location(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_IMAGE_LOCATION)));
        sessionDataModel.setSess_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_PHASE_ID)));
        sessionDataModel.setSess_project_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_PROJECT_ID)));
        sessionDataModel.setSess_user_id(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_USER_ID)));
        sessionDataModel.setSess_spid(cursor.getInt(cursor.getColumnIndex(COLUMN_SESS_SPID)));
        sessionDataModel.setSess_start_timestamp(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_START_TIMESTAMP)));
        sessionDataModel.setUpdated_by(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_UPDATED_BY)));
        sessionDataModel.setSess_trac_id_fk(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_TRACE_ID_FK)));
        sessionDataModel.setSess_type(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_TYPE)));
        sessionDataModel.setSess_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_SESS_SYNC_STATUS)));

        return sessionDataModel;
    }



    public ArrayList<SessionDataModel> getSessionsFromGroupId(String sessionId) {
        db = getDatabase();
        ArrayList<SessionDataModel> sessionDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_SESS_GROUP_ID + " = ? ";
        String[] arguments = {sessionId};
        Cursor cursor = db.query(TABLE_NAME_TRN_SESSION,allColumns,selection,arguments,null,null,null);
        if (cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                sessionDataModelArrayList.add(cursorToModel(cursor));
            }
        }else{
            cursor.close();
            db.close();
            return null;
        }
        cursor.close();
        db.close();
        return sessionDataModelArrayList;
    }

    public void updateSessionMapScreenShot(String customerGroupId, String sessionKey, String path, String traceId) {
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SESS_IMAGE_LOCATION, path);
        contentValues.put(COLUMN_SESS_TRACE_ID_FK, traceId);
        String where = COLUMN_SESS_KEY +" = ? AND " +COLUMN_SESS_GROUP_ID +" = ? ";
        String[] whereArgs = {sessionKey,customerGroupId};
        int updatedId = db.update(TABLE_NAME_TRN_SESSION,contentValues,where,whereArgs);
        db.close();
        Log.e(TAG, "updateSessionMapScreenShot: "+updatedId);
    }

    public ArrayList<SessionDataModel> getAllDirtySessions() {
        Log.e(TAG, "getAllDirtySessions: ");
        db = getDatabase();
        ArrayList<SessionDataModel> sessionDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_SESS_SYNC_STATUS + " = ? OR " + COLUMN_SESS_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_TRN_SESSION, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                sessionDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return sessionDataModelArrayList;
    }

    public void insertSessionOfServer(ArrayList<SessionDataModel> sessionDataModelArrayList) {
        Log.e(TAG, "insertSessionOfServer: ");
        db = getDatabase();
        SessionDataModel sessionDataModel;
        for (int i = 0; i < sessionDataModelArrayList.size(); i++) {
            sessionDataModel = sessionDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_SESS_KEY, sessionDataModel.getSess_key());
            values.put(COLUMN_SESS_START_TIMESTAMP, sessionDataModel.getSess_start_timestamp());
            values.put(COLUMN_SESS_START_LAT, sessionDataModel.getSess_start_lat());
            values.put(COLUMN_SESS_END_LAT, sessionDataModel.getSess_end_lat());
            values.put(COLUMN_SESS_START_LONG, sessionDataModel.getSess_start_long());
            values.put(COLUMN_SESS_END_LONG, sessionDataModel.getSess_end_long());
            values.put(COLUMN_SESS_TYPE, sessionDataModel.getSess_type());
            values.put(COLUMN_SESS_CUST_UNIQUE_ID, sessionDataModel.getSess_cust_unique_id());
            values.put(COLUMN_SESS_GROUP_ID, sessionDataModel.getSess_group_id());
            values.put(COLUMN_SESS_USER_ID, sessionDataModel.getSess_user_id());
            values.put(COLUMN_SESS_SPID, sessionDataModel.getSess_spid());
            values.put(COLUMN_SESS_PROJECT_ID, sessionDataModel.getSess_project_id());
            values.put(COLUMN_SESS_PHASE_ID, sessionDataModel.getSess_phase_id());
            values.put(COLUMN_SESS_CUSTOMER_FEEDBACK, sessionDataModel.getSess_customer_feedback());
            values.put(COLUMN_SESS_USER_FEEDBACK, sessionDataModel.getSess_user_feedback());
            values.put(COLUMN_SESS_IMAGE_LOCATION, sessionDataModel.getSess_image_location());
            values.put(COLUMN_SESS_CREATED_BY, sessionDataModel.getCreated_by());
            values.put(COLUMN_SESS_UPDATED_BY, sessionDataModel.getUpdated_by());
            values.put(COLUMN_SESS_CREATED_AT, sessionDataModel.getCreated_at());
            values.put(COLUMN_SESS_UPDATED_AT, sessionDataModel.getUpdated_at());
            values.put(COLUMN_SESS_DELEATED_AT, sessionDataModel.getDeleted_at());
            values.put(COLUMN_SESS_TRACE_ID_FK, sessionDataModel.getSess_trac_id_fk());
            values.put(COLUMN_SESS_SEND_FLAG, sessionDataModel.getSess_end_flag());
            values.put(COLUMN_SESS_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_SESS_KEY + " = ? ";
            String whereArgs[] = {String.valueOf(sessionDataModel.getSess_id())};
            long updatedCount = db.update(TABLE_NAME_TRN_SESSION, values, whereClause, whereArgs);
            Log.e(TAG, "insertSession: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_TRN_SESSION, null, values);
                Log.e(TAG, "insertSession: insertedCount = " + insertedCount);
            }
        }
        Log.e(TAG, "insertCustomerGroup: CUSTOMER GROUP INSERTED SUCCESSFULLY");
        db.close();
    }

    public void deleteDirtySessions(ArrayList<SessionDataModel> sessionDataModelArrayList) {
        Log.e(TAG, "deleteDirtySessions: ");
        db = getDatabase();
        for (int i = 0; i < sessionDataModelArrayList.size(); i++) {
            SessionDataModel sessionDataModel = sessionDataModelArrayList.get(i);
            String whereClause = COLUMN_SESS_ID + " = ? AND " + COLUMN_SESS_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(sessionDataModel.getSess_id()), sessionDataModel.getSess_sync_status()};
            int deletedId = db.delete(TABLE_NAME_TRN_SESSION, whereClause, whereArgs);
            Log.e(TAG, "sessionDataModelArrayList: " + deletedId);
        }
        db.close();
    }

    public String getSessionKeyFromTraceId(String traceId){
        db = getDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_SESS_KEY + " FROM " + TABLE_NAME_TRN_SESSION + " WHERE " + COLUMN_SESS_TRACE_ID_FK + " = " + traceId,null);
        String string ="";
        if (cursor.moveToNext())
            string = cursor.getString(cursor.getColumnIndex(COLUMN_SESS_KEY));
        Log.e(TAG, "getSessionKeyFromTraceId: SESSION KEY: " + string );
        return string;
    }

}