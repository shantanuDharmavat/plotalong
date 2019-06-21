package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 23/6/17.
 */

public class CfgContentDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_CONTENT = "cfg_content";
    public static final String COLUMN_CONT_CLIENT_ID = "cont_client_id";
    public static final String COLUMN_CONT_ID = "cont_id";
    public static final String COLUMN_CONT_DEVC_ID = "cont_devc_id";
    public static final String COLUMN_CONT_CUST_ID = "cont_cust_id";
    public static final String COLUMN_CONT_DEVLID = "cont_devl_id";
    public static final String COLUMN_CONT_PROJECT_ID = "cont_project_id";
    public static final String COLUMN_CONT_PHASE_ID = "cont_phase_id";
    public static final String COLUMN_CONT_PLOT_ID = "cont_plot_id";
    public static final String COLUMN_CONT_FILE_TYPE = "cont_file_type";
    public static final String COLUMN_CONT_FILE_LOCATION = "cont_file_location";
    public static final String COLUMN_CONT_DESCRIPTION = "cont_description";
    public static final String COLUMN_CONT_APP_TAG = "cont_app_tag";
    public static final String COLUMN_CONT_COMMAND_TAG = "cont_command_tag";
    public static final String COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE = "cont_filefolderstructure_device";
    public static final String COLUMN_CONT_ORDER = "cont_order";
    public static final String COLUMN_CONT_LATITUDE = "cont_latitude";
    public static final String COLUMN_CONT_LONGITUDE = "cont_longitude";
    public static final String COLUMN_CONT_STATUS = "cont_status";
    public static final String COLUMN_DEVICE_CFG = "device_cfg";
    public static final String COLUMN_CONT_IS_DELETED = "cont_is_deleted";
    public static final String COLUMN_CONT_TYPE = "cont_type";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_CONT_SYNC_STATUS = "cont_sync_status";


    public static final String CREATE_TABLE_CFG_CONTENT = "CREATE TABLE "
            + TABLE_NAME_CFG_CONTENT + "("
            + COLUMN_CONT_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CONT_ID
            + " INTEGER,"
            + COLUMN_CONT_DEVC_ID
            + " TEXT,"
            + COLUMN_CONT_DEVLID
            + " INTEGER,"
            + COLUMN_CONT_PROJECT_ID
            + " INTEGER,"
            + COLUMN_CONT_CUST_ID
            + " INTEGER,"
            + COLUMN_CONT_PHASE_ID
            + " INTEGER,"
            + COLUMN_CONT_PLOT_ID
            + " INTEGER,"
            + COLUMN_CONT_FILE_TYPE
            + " TEXT,"
            + COLUMN_CONT_FILE_LOCATION
            + " TEXT,"
            + COLUMN_CONT_DESCRIPTION
            + " TEXT,"
            + COLUMN_CONT_APP_TAG
            + " TEXT,"
            + COLUMN_CONT_COMMAND_TAG
            + " INTEGER,"
            + COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE
            + " TEXT,"
            + COLUMN_CONT_ORDER
            + " INTEGER,"
            + COLUMN_CONT_LATITUDE
            + " TEXT,"
            + COLUMN_CONT_LONGITUDE
            + " TEXT,"
            + COLUMN_CONT_STATUS
            + " INTEGER,"
            + COLUMN_DEVICE_CFG
            + " INTEGER,"
            + COLUMN_CONT_IS_DELETED
            + " INTEGER,"
            + COLUMN_CONT_TYPE
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_CONT_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_CONTENT = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_CONTENT;
    private static String[] allColumns = {
            COLUMN_CONT_CLIENT_ID,
            COLUMN_CONT_ID,
            COLUMN_CONT_DEVC_ID,
            COLUMN_CONT_DEVLID,
            COLUMN_CONT_PROJECT_ID,
            COLUMN_CONT_PHASE_ID,
            COLUMN_CONT_CUST_ID,
            COLUMN_CONT_PLOT_ID,
            COLUMN_CONT_FILE_TYPE,
            COLUMN_CONT_FILE_LOCATION,
            COLUMN_CONT_DESCRIPTION,
            COLUMN_CONT_APP_TAG,
            COLUMN_CONT_COMMAND_TAG,
            COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE,
            COLUMN_CONT_ORDER,
            COLUMN_CONT_LATITUDE,
            COLUMN_CONT_LONGITUDE,
            COLUMN_CONT_STATUS,
            COLUMN_DEVICE_CFG,
            COLUMN_CONT_IS_DELETED,
            COLUMN_CONT_TYPE,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT,
            COLUMN_CONT_SYNC_STATUS
    };
    private String TAG = getClass().getSimpleName();
    private SQLiteDatabase db;

    public CfgContentDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertContent(ArrayList<ContentDataModel> contentDataModelArrayList) {
        Log.e(TAG, "insertContent: ");
        db = getDatabase();
        ContentDataModel contentDataModel;
        for (int i = 0; i < contentDataModelArrayList.size(); i++) {
            contentDataModel = contentDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_CONT_ID, contentDataModel.getCont_id());
            values.put(COLUMN_CONT_DEVC_ID, contentDataModel.getCont_devc_id());
            values.put(COLUMN_CONT_DEVLID, contentDataModel.getCont_devl_id());
            values.put(COLUMN_CONT_CUST_ID, contentDataModel.getCont_cust_id());
            values.put(COLUMN_CONT_PROJECT_ID, contentDataModel.getCont_project_id());
            values.put(COLUMN_CONT_PHASE_ID, contentDataModel.getCont_phase_id());
            values.put(COLUMN_CONT_PLOT_ID, contentDataModel.getCont_plot_id());
            values.put(COLUMN_CONT_FILE_TYPE, contentDataModel.getCont_file_type());
            values.put(COLUMN_CONT_FILE_LOCATION, contentDataModel.getCont_file_location());
            values.put(COLUMN_CONT_DESCRIPTION, contentDataModel.getCont_description());
            values.put(COLUMN_CONT_APP_TAG, contentDataModel.getCont_app_tag());
            values.put(COLUMN_CONT_COMMAND_TAG, contentDataModel.getCont_command_tag());
            values.put(COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE, contentDataModel.getCont_filefolderstructure_device());
            values.put(COLUMN_CONT_ORDER, contentDataModel.getCont_order());
            values.put(COLUMN_CONT_LATITUDE, contentDataModel.getCont_latitude());
            values.put(COLUMN_CONT_LONGITUDE, contentDataModel.getCont_longitude());
            values.put(COLUMN_CONT_STATUS, contentDataModel.getCont_status());
            values.put(COLUMN_DEVICE_CFG, contentDataModel.getDevice_cfg());
            values.put(COLUMN_CONT_IS_DELETED, contentDataModel.getCont_is_deleted());
            values.put(COLUMN_CONT_TYPE, contentDataModel.getCont_type());
            values.put(COLUMN_CREATED_AT, contentDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, contentDataModel.getUpdated_at());
            values.put(COLUMN_CONT_SYNC_STATUS, GlobalConstant.STRING_OK);

            String where = COLUMN_CONT_ID + " = ? ";
            String whereArgs[] = {String.valueOf(contentDataModel.getCont_id())};
            long updateCount = db.update(TABLE_NAME_CFG_CONTENT, values, where, whereArgs);
            Log.e(TAG, "insertContent: update count = " + updateCount);
            if (updateCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_CFG_CONTENT, null, values);
                Log.e(TAG, "insertContent: inserted count = " + insertedCount);
            }
            Log.e(TAG, "insertContent: CUSTOMER INSERTED SUCCESSFULLY");
        }
        db.close();
    }

    public ArrayList<ContentDataModel> getAllGeoGalleryModelsPlotId(int plotId) {
        Log.e(TAG, "getAllGeoGalleryModelsPlotId: ");
        db = getDatabase();
        String selection = COLUMN_CONT_PLOT_ID + " = ?";
        String selectionArgs[] = {String.valueOf(plotId)};
        ArrayList<ContentDataModel> contentDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLE_NAME_CFG_CONTENT
                        + " WHERE "
                        + COLUMN_CONT_PLOT_ID
                        + " = "
                        + plotId
                , null);

        while (cursor.moveToNext()) {
            contentDataModelArrayList.add(cursorToModel(cursor));
        }
        return contentDataModelArrayList;
    }

    public ArrayList<ContentDataModel> getAllGeoGalleryModelsPhasId(int phasId) {
        Log.e(TAG, "getAllGeoGalleryModelsPlotId: ");
        db = getDatabase();
        String selection = COLUMN_CONT_PLOT_ID + " = ?";
        String selectionArgs[] = {String.valueOf(phasId)};
        ArrayList<ContentDataModel> contentDataModelArrayList = new ArrayList<>();
//        Cursor cursor = db.query(TABLE_NAME_CFG_CONTENT,allColumns,selection,selectionArgs,null,null,null);
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLE_NAME_CFG_CONTENT
                        + " WHERE "
                        + COLUMN_CONT_PHASE_ID
                        + " = "
                        + phasId
                , null);

        while (cursor.moveToNext()) {
            contentDataModelArrayList.add(cursorToModel(cursor));
        }
        return contentDataModelArrayList;
    }

    private ContentDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");

        ContentDataModel model = new ContentDataModel();
        model.setCont_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_CLIENT_ID)));
        model.setCont_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_ID)));
        model.setCont_cust_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_CUST_ID)));
        model.setCont_devc_id(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_DEVC_ID)));
        model.setCont_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_DEVLID)));
        model.setCont_project_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_PROJECT_ID)));
        model.setCont_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_PHASE_ID)));
        model.setCont_plot_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_PLOT_ID)));
        model.setCont_file_type(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_FILE_TYPE)));
        model.setCont_file_location(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_FILE_LOCATION)));
        model.setCont_description(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_DESCRIPTION)));
        model.setCont_app_tag(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_APP_TAG)));
        model.setCont_command_tag(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_COMMAND_TAG)));
        model.setCont_filefolderstructure_device(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE)));
        model.setCont_order(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_ORDER)));
        model.setCont_latitude(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_LATITUDE)));
        model.setCont_longitude(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_LONGITUDE)));
        model.setCont_status(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_STATUS)));
        model.setDevice_cfg(cursor.getInt(cursor.getColumnIndex(COLUMN_DEVICE_CFG)));
        model.setCont_is_deleted(cursor.getInt(cursor.getColumnIndex(COLUMN_CONT_IS_DELETED)));
        model.setCont_type(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_TYPE)));
        model.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        model.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        model.setCont_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_CONT_SYNC_STATUS)));

        return model;
    }

    public ArrayList<ContentDataModel> getAllContents() {
        Log.e(TAG, "getAllContents: ");
        db = getDatabase();
        ArrayList<ContentDataModel> contentDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_CFG_CONTENT, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                contentDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return contentDataModelArrayList;
    }

    public ArrayList<ContentDataModel> getAllUnsyncContents(String lastSyncTime) {
        Log.e(TAG, "getAllUnsyncContents: ");
        db = getDatabase();
        ArrayList<ContentDataModel> contentDataModelArrayList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME_CFG_CONTENT + " WHERE " + COLUMN_UPDATED_AT + " >= '" + lastSyncTime + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                contentDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return contentDataModelArrayList;
    }

    public ArrayList<ContentDataModel> getAllDirtyContent() {
        Log.e(TAG, "getAllDirtyContent: ");
        db = getDatabase();
        ArrayList<ContentDataModel> contentDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_CONT_SYNC_STATUS + " = ? OR " + COLUMN_CONT_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_CFG_CONTENT, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                contentDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return contentDataModelArrayList;
    }

    public void deleteDirtyContents(ArrayList<ContentDataModel> contentDataModelArrayList) {
        Log.e(TAG, "deleteDirtyContents: ");
        db = getDatabase();
        for (int i = 0; i < contentDataModelArrayList.size(); i++) {
            ContentDataModel contentDataModel = contentDataModelArrayList.get(i);
            String whereClause = COLUMN_CONT_CLIENT_ID + " = ? AND " + COLUMN_CONT_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(contentDataModel.getCont_client_id()), contentDataModel.getCont_sync_status()};
            int deletedId = db.delete(TABLE_NAME_CFG_CONTENT, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyContents: " + deletedId);
        }
        db.close();
    }

    public void insertProjectPhotoPath(ContentDataModel contentDataModel) {
        Log.e(TAG, "insertProjectPhotoPath: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONT_ID, contentDataModel.getCont_id());
        values.put(COLUMN_CONT_DEVC_ID, contentDataModel.getCont_devc_id());
        values.put(COLUMN_CONT_DEVLID, contentDataModel.getCont_devl_id());
        values.put(COLUMN_CONT_CUST_ID, contentDataModel.getCont_cust_id());
        values.put(COLUMN_CONT_PROJECT_ID, contentDataModel.getCont_project_id());
        values.put(COLUMN_CONT_PHASE_ID, contentDataModel.getCont_phase_id());
        values.put(COLUMN_CONT_PLOT_ID, contentDataModel.getCont_plot_id());
        values.put(COLUMN_CONT_FILE_TYPE, contentDataModel.getCont_file_type());
        values.put(COLUMN_CONT_FILE_LOCATION, contentDataModel.getCont_file_location());
        values.put(COLUMN_CONT_DESCRIPTION, contentDataModel.getCont_description());
        values.put(COLUMN_CONT_APP_TAG, contentDataModel.getCont_app_tag());
        values.put(COLUMN_CONT_COMMAND_TAG, contentDataModel.getCont_command_tag());
        values.put(COLUMN_CONT_FILEFOLDERSTRUCTURE_DEVICE, contentDataModel.getCont_filefolderstructure_device());
        values.put(COLUMN_CONT_ORDER, contentDataModel.getCont_order());
        values.put(COLUMN_CONT_LATITUDE, contentDataModel.getCont_latitude());
        values.put(COLUMN_CONT_LONGITUDE, contentDataModel.getCont_longitude());
        values.put(COLUMN_CONT_STATUS, contentDataModel.getCont_status());
        values.put(COLUMN_DEVICE_CFG, contentDataModel.getDevice_cfg());
        values.put(COLUMN_CONT_IS_DELETED, contentDataModel.getCont_is_deleted());
        values.put(COLUMN_CONT_TYPE, contentDataModel.getCont_type());
        values.put(COLUMN_CREATED_AT, contentDataModel.getCreated_at());
        values.put(COLUMN_UPDATED_AT, contentDataModel.getUpdated_at());
        values.put(COLUMN_CONT_SYNC_STATUS, GlobalConstant.INSERTED);

        long insertedId = db.insert(TABLE_NAME_CFG_CONTENT, null, values);
        Log.e(TAG, "insertProjectPhotoPath: " + insertedId);
    }

    public ArrayList<ContentDataModel> getGeoContent() {
        String selection = COLUMN_CONT_TYPE + " = ? AND " + COLUMN_CONT_FILE_TYPE + " = ?";
        String[] selectionArgs = {"geogallery", "jpg"};
        ArrayList<ContentDataModel> list = new ArrayList<>();
        db = getDatabase();
        Cursor cursor = db.query(TABLE_NAME_CFG_CONTENT, allColumns, selection, selectionArgs, null, null, null);

//        Cursor cursor = db.rawQuery("SELECT * FROM "
//                + TABLE_NAME_CFG_CONTENT
//                + " WHERE "
//                + COLUMN_CONT_TYPE
//                + " = 'geogallery' "
//                + " AND "
//                + COLUMN_CONT_FILE_TYPE
//                + " = 'jpg'",null);
//

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(cursorToModel(cursor));
            }
        }
        return list;
    }
}
