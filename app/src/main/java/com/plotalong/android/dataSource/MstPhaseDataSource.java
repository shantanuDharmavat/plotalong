package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 15/11/17.
 */

public class MstPhaseDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_PHASE = "mst_phase";
    public static final String COLUMN_CLIENT_PHAS_ID = "client_phas_id";
    public static final String COLUMN_PHAS_ID = "phas_id";
    public static final String COLUMN_PHAS_PROJ_ID = "phas_proj_id";
    public static final String COLUMN_PHAS_DEVL_ID = "phas_devl_id";
    public static final String COLUMN_PHAS_KEY = "phas_key";
    public static final String COLUMN_PHAS_NAME = "phas_name";
    public static final String COLUMN_PHAS_DETAILS = "phas_details";
    public static final String COLUMN_PHAS_NORTH = "phas_north";
    public static final String COLUMN_PHAS_EAST = "phas_east";
    public static final String COLUMN_PHAS_WEST = "phas_west";
    public static final String COLUMN_PHAS_SOUTH = "phas_south";
    public static final String COLUMN_PHAS_BOUNDARIES = "phas_boundaries";
    public static final String COLUMN_PHAS_ROUTES = "phas_routes";
    public static final String COLUMN_PHAS_NEARBY = "phas_nearby";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_PHAS_UOM_ID = "phas_uom_id";
    public static final String COLUMN_PHAS_TYPE_ID = "phas_type_id";
    public static final String COLUMN_PHAS_LOGO = "phas_logo";
    public static final String COLUMN_PHAS_LAUNCH_DATE = "phas_launch_date";
    public static final String COLUMN_PHAS_RERA_CODE = "phas_rera_code";
    public static final String COLUMN_PHAS_COORDINATES = "phas_coordinates";
    public static final String COLUMN_PHAS_RERA_RENEW_DATE = "phas_rera_renew_date";
    public static final String COLUMN_DELETED_AT = "deleted_at";


    public static final String CREATE_TABLE_MST_PHASE = "CREATE TABLE "
            + TABLE_NAME_MST_PHASE + "("
            + COLUMN_CLIENT_PHAS_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PHAS_ID
            + " INTEGER,"
            + COLUMN_PHAS_PROJ_ID
            + " INTEGER,"
            + COLUMN_PHAS_DEVL_ID
            + " INTEGER,"
            + COLUMN_PHAS_KEY
            + " TEXT,"
            + COLUMN_PHAS_NAME
            + " TEXT,"
            + COLUMN_PHAS_DETAILS
            + " TEXT,"
            + COLUMN_PHAS_NORTH
            + " TEXT,"
            + COLUMN_PHAS_EAST
            + " TEXT,"
            + COLUMN_PHAS_WEST
            + " TEXT,"
            + COLUMN_PHAS_SOUTH
            + " TEXT,"
            + COLUMN_PHAS_BOUNDARIES
            + " TEXT,"
            + COLUMN_PHAS_ROUTES
            + " TEXT,"
            + COLUMN_PHAS_NEARBY
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_PHAS_UOM_ID
            + " TEXT,"
            + COLUMN_PHAS_TYPE_ID
            + " TEXT,"
            + COLUMN_PHAS_LOGO
            + " TEXT,"
            + COLUMN_PHAS_LAUNCH_DATE
            + " TEXT,"
            + COLUMN_PHAS_RERA_CODE
            + " TEXT,"
            + COLUMN_PHAS_COORDINATES
            + " TEXT,"
            + COLUMN_PHAS_RERA_RENEW_DATE
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_PHASE = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_PHASE;

    public static final String allColumns[] = {COLUMN_CLIENT_PHAS_ID, COLUMN_PHAS_ID, COLUMN_PHAS_PROJ_ID,
            COLUMN_PHAS_DEVL_ID, COLUMN_PHAS_KEY, COLUMN_PHAS_NAME, COLUMN_PHAS_DETAILS, COLUMN_PHAS_NORTH,
            COLUMN_PHAS_EAST, COLUMN_PHAS_WEST, COLUMN_PHAS_SOUTH, COLUMN_PHAS_BOUNDARIES, COLUMN_PHAS_ROUTES,
            COLUMN_PHAS_NEARBY, COLUMN_CREATED_BY, COLUMN_UPDATED_BY, COLUMN_CREATED_AT, COLUMN_UPDATED_AT,
            COLUMN_PHAS_UOM_ID, COLUMN_PHAS_TYPE_ID, COLUMN_PHAS_LOGO, COLUMN_PHAS_LAUNCH_DATE,
            COLUMN_PHAS_RERA_CODE, COLUMN_PHAS_COORDINATES, COLUMN_PHAS_RERA_RENEW_DATE, COLUMN_DELETED_AT};
    private static final String TAG = MstPhaseDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstPhaseDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public ArrayList<PhaseDataModel> getAllPhase() {
        Log.e(TAG, "getAllProjectPhase: ");
        ArrayList<PhaseDataModel> phaseDataModelArrayList = new ArrayList<>();
        db = getDatabase();
        Cursor cursor = db.query(TABLE_NAME_MST_PHASE, allColumns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            phaseDataModelArrayList.add(cursorToObject(cursor));
        }
        cursor.close();
        return phaseDataModelArrayList;
    }

    private PhaseDataModel cursorToObject(Cursor cursor) {
        Log.e(TAG, "cursorToObject: ");
        PhaseDataModel phaseDataModel = new PhaseDataModel();
        phaseDataModel.setPhas_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PHAS_ID)));
        phaseDataModel.setPhas_proj_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PHAS_PROJ_ID)));
        phaseDataModel.setPhas_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PHAS_DEVL_ID)));
        phaseDataModel.setPhas_key(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_KEY)));
        phaseDataModel.setPhas_name(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_NAME)));
        phaseDataModel.setPhas_details(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_DETAILS)));
        phaseDataModel.setPhas_north(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_NORTH)));
        phaseDataModel.setPhas_east(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_EAST)));
        phaseDataModel.setPhas_west(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_WEST)));
        phaseDataModel.setPhas_south(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_SOUTH)));
        phaseDataModel.setPhas_boundaries(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_BOUNDARIES)));
        phaseDataModel.setPhas_routes(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_ROUTES)));
        phaseDataModel.setPhas_nearby(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_NEARBY)));
        phaseDataModel.setCreated_by(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_BY)));
        phaseDataModel.setUpdated_by(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_BY)));
        phaseDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        phaseDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        phaseDataModel.setPhas_uom_id(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_UOM_ID)));
        phaseDataModel.setPhas_type_id(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_TYPE_ID)));
        phaseDataModel.setPhas_logo(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_LOGO)));
        phaseDataModel.setPhas_launch_date(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_LAUNCH_DATE)));
        phaseDataModel.setPhas_rera_code(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_RERA_CODE)));
        phaseDataModel.setPhas_coordinates(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_COORDINATES)));
        phaseDataModel.setPhas_rera_renew_date(cursor.getString(cursor.getColumnIndex(COLUMN_PHAS_RERA_RENEW_DATE)));
        phaseDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));

        return phaseDataModel;
    }

    public void insertPhasesOfServer(ArrayList<PhaseDataModel> phaseDataModelArrayList) {
        Log.e(TAG, "insertProjectsOfServer: ");
        db = getDatabase();
        PhaseDataModel phaseDataModel;
        for (int i = 0; i < phaseDataModelArrayList.size(); i++) {
            phaseDataModel = phaseDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_PHAS_ID, phaseDataModel.getPhas_id());
            values.put(COLUMN_PHAS_PROJ_ID, phaseDataModel.getPhas_proj_id());
            values.put(COLUMN_PHAS_DEVL_ID, phaseDataModel.getPhas_devl_id());
            values.put(COLUMN_PHAS_KEY, phaseDataModel.getPhas_key());
            values.put(COLUMN_PHAS_NAME, phaseDataModel.getPhas_name());
            values.put(COLUMN_PHAS_DETAILS, phaseDataModel.getPhas_details());
            values.put(COLUMN_PHAS_NORTH, phaseDataModel.getPhas_north());
            values.put(COLUMN_PHAS_EAST, phaseDataModel.getPhas_east());
            values.put(COLUMN_PHAS_WEST, phaseDataModel.getPhas_west());
            values.put(COLUMN_PHAS_SOUTH, phaseDataModel.getPhas_south());
            values.put(COLUMN_PHAS_BOUNDARIES, phaseDataModel.getPhas_boundaries());
            values.put(COLUMN_PHAS_ROUTES, phaseDataModel.getPhas_routes());
            values.put(COLUMN_PHAS_NEARBY, phaseDataModel.getPhas_nearby());
            values.put(COLUMN_CREATED_BY, phaseDataModel.getCreated_by());
            values.put(COLUMN_UPDATED_BY, phaseDataModel.getUpdated_by());
            values.put(COLUMN_CREATED_AT, phaseDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, phaseDataModel.getUpdated_at());
            values.put(COLUMN_PHAS_UOM_ID, phaseDataModel.getPhas_uom_id());
            values.put(COLUMN_PHAS_TYPE_ID, phaseDataModel.getPhas_type_id());
            values.put(COLUMN_PHAS_LOGO, phaseDataModel.getPhas_logo());
            values.put(COLUMN_PHAS_LAUNCH_DATE, phaseDataModel.getPhas_launch_date());
            values.put(COLUMN_PHAS_RERA_CODE, phaseDataModel.getPhas_rera_code());
            values.put(COLUMN_PHAS_COORDINATES, phaseDataModel.getPhas_coordinates());
            values.put(COLUMN_PHAS_RERA_RENEW_DATE, phaseDataModel.getPhas_rera_renew_date());
            values.put(COLUMN_DELETED_AT, phaseDataModel.getDeleted_at());

            String whereClause = COLUMN_PHAS_ID + " = ? ";
            String whereArgs[] = {String.valueOf(phaseDataModel.getPhas_id())};
            long updatedCount = db.update(TABLE_NAME_MST_PHASE, values, whereClause, whereArgs);
            Log.e(TAG, "insertProjectsOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_MST_PHASE, null, values);
                Log.e(TAG, "insertProjectsOfServer: insertedCount = " + insertedCount);
            }
        }
        Log.e(TAG, "insertProjectsOfServer: PROJECT_PHASE INSERTED SUCCESSFULLY");
        db.close();
    }
}