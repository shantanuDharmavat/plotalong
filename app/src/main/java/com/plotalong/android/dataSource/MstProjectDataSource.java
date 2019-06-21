package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.ProjectDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 7/6/17.
 */

public class MstProjectDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_PROJECT = "mst_project";
    public static final String COLUMN_CLIENT_PROJ_ID = "client_proj_id";
    public static final String COLUMN_PROJ_ID = "proj_id";
    public static final String COLUMN_PROJ_KEY = "proj_key";
    public static final String COLUMN_PROJ_NAME = "proj_name";
    public static final String COLUMN_PROJ_DESC = "proj_desc";
    public static final String COLUMN_PROJ_TYPE_ID = "proj_type_id";
    public static final String COLUMN_PROJ_DEVELOPER_ID = "proj_developer_id";
    public static final String COLUMN_PROJ_OWNER_ID = "proj_owner_id";
    public static final String COLUMN_PROJ_UOM_ID = "proj_uom_id";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_PROJ_LOGO = "proj_logo";
    public static final String COLUMN_PROJ_COORDS = "proj_coords";
    public static final String COLUMN_PROJ_RERA_CODE = "proj_rera_code";
    public static final String COLUMN_PROJ_RERA_RENEW_DATE = "proj_rera_renew_date";
    public static final String COLUMN_PROJ_LAUNCH_DATE = "proj_launch_date";
    public static final String COLUMN_DELETED_AT = "deleted_at";


    public static final String CREATE_TABLE_MST_PROJECT = "CREATE TABLE "
            + TABLE_NAME_MST_PROJECT + "("
            + COLUMN_CLIENT_PROJ_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PROJ_ID
            + " INTEGER,"
            + COLUMN_PROJ_KEY
            + " TEXT,"
            + COLUMN_PROJ_NAME
            + " TEXT,"
            + COLUMN_PROJ_DESC
            + " TEXT,"
            + COLUMN_PROJ_TYPE_ID
            + " TEXT,"
            + COLUMN_PROJ_DEVELOPER_ID
            + " INTEGER,"
            + COLUMN_PROJ_OWNER_ID
            + " INTEGER,"
            + COLUMN_PROJ_UOM_ID
            + " INTEGER,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_PROJ_LOGO
            + " TEXT,"
            + COLUMN_PROJ_COORDS
            + " TEXT,"
            + COLUMN_PROJ_RERA_CODE
            + " TEXT,"
            + COLUMN_PROJ_RERA_RENEW_DATE
            + " TEXT,"
            + COLUMN_PROJ_LAUNCH_DATE
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_PROJECT = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_PROJECT;

    public static final String allColumns[] = {COLUMN_CLIENT_PROJ_ID, COLUMN_PROJ_ID, COLUMN_PROJ_KEY,
            COLUMN_PROJ_NAME, COLUMN_PROJ_DESC, COLUMN_PROJ_TYPE_ID, COLUMN_PROJ_DEVELOPER_ID,
            COLUMN_PROJ_OWNER_ID, COLUMN_PROJ_UOM_ID, COLUMN_CREATED_BY, COLUMN_UPDATED_BY, COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT, COLUMN_PROJ_LOGO, COLUMN_PROJ_COORDS, COLUMN_PROJ_RERA_CODE,
            COLUMN_PROJ_RERA_RENEW_DATE, COLUMN_PROJ_LAUNCH_DATE, COLUMN_DELETED_AT};
    private static final String TAG = MstProjectDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstProjectDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertProjectsOfServer(ArrayList<ProjectDataModel> projects) {
        Log.e(TAG, "insertProjectsOfServer: ");
        db = getDatabase();
        ProjectDataModel projectDataModel;
        for (int i = 0; i < projects.size(); i++) {
            projectDataModel = projects.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_PROJ_ID, projectDataModel.getProj_id());
            values.put(COLUMN_PROJ_KEY, projectDataModel.getProj_key());
            values.put(COLUMN_PROJ_NAME, projectDataModel.getProj_name());
            values.put(COLUMN_PROJ_DESC, projectDataModel.getProj_desc());
            values.put(COLUMN_PROJ_TYPE_ID, projectDataModel.getProj_type_id());
            values.put(COLUMN_PROJ_DEVELOPER_ID, projectDataModel.getProj_developer_id());
            values.put(COLUMN_PROJ_OWNER_ID, projectDataModel.getProj_owner_id());
            values.put(COLUMN_PROJ_UOM_ID, projectDataModel.getProj_uom_id());
            values.put(COLUMN_CREATED_BY, projectDataModel.getCreated_by());
            values.put(COLUMN_UPDATED_BY, projectDataModel.getUpdated_by());
            values.put(COLUMN_CREATED_AT, projectDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, projectDataModel.getUpdated_at());
            values.put(COLUMN_PROJ_LOGO, projectDataModel.getProj_logo());
            values.put(COLUMN_PROJ_COORDS, projectDataModel.getProj_coords());
            values.put(COLUMN_PROJ_RERA_CODE, projectDataModel.getProj_rera_code());
            values.put(COLUMN_PROJ_RERA_RENEW_DATE, projectDataModel.getProj_rera_renew_date());
            values.put(COLUMN_PROJ_LAUNCH_DATE, projectDataModel.getProj_launch_date());
            values.put(COLUMN_DELETED_AT, projectDataModel.getDeleted_at());

            String whereClause = COLUMN_PROJ_ID + " = ? ";
            String whereArgs[] = {String.valueOf(projectDataModel.getProj_id())};
            long updatedCount = db.update(TABLE_NAME_MST_PROJECT, values, whereClause, whereArgs);
            Log.e(TAG, "insertProjectsOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_MST_PROJECT, null, values);
                Log.e(TAG, "insertProjectsOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }
}