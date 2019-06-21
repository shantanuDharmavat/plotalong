package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kbhakade on 1/6/17.
 */

public class MstPlotDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_PLOT = "mst_plot";
    public static final String COLUMN_CLIENT_PLOT_ID = "plot_client_id";
    public static final String COLUMN_PLOT_ID = "plot_id";
    public static final String COLUMN_PLOT_NUMBER = "plot_number";
    public static final String COLUMN_PLOT_PHASE_ID = "plot_phase_id";
    public static final String COLUMN_PLOT_PROJECT_ID = "plot_project_id";
    public static final String COLUMN_PLOT_SECTOR = "plot_sector";
    public static final String COLUMN_PLOT_SIZE = "plot_size";
    public static final String COLUMN_PLOT_LENGTH = "plot_length";
    public static final String COLUMN_PLOT_WIDTH = "plot_width";
    public static final String COLUMN_PLOT_TYPE = "plot_type";
    public static final String COLUMN_PLOT_STATUS = "plot_status";
    public static final String COLUMN_PLOT_PRIORITY = "plot_priority";
    public static final String COLUMN_PLOT_CUSTOMER_ID = "plot_customer_id";
    public static final String COLUMN_PLOT_BOOKED_BY = "plot_booked_by";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_PLOT_COORDINATES = "plot_coordinates";
    public static final String COLUMN_PLOT_DESCRIPTION = "plot_description";
    public static final String COLUMN_PROD_ID = "prod_id";
    public static final String COLUMN_CATEGORY_NAME = "cat_name";
    public static final String COLUMN_CATEGORY_RATE = "cat_rate";


    public static final String CREATE_TABLE_MST_PLOT = "CREATE TABLE "
            + TABLE_NAME_MST_PLOT + "("
            + COLUMN_CLIENT_PLOT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PLOT_ID
            + " INTEGER,"
            + COLUMN_PLOT_NUMBER
            + " INTEGER,"
            + COLUMN_PLOT_PHASE_ID
            + " INTEGER,"
            + COLUMN_PLOT_PROJECT_ID
            + " INTEGER,"
            + COLUMN_PLOT_SIZE
            + " INTEGER,"
            + COLUMN_PLOT_LENGTH
            + " INTEGER,"
            + COLUMN_PLOT_WIDTH
            + " INTEGER,"
            + COLUMN_PLOT_PRIORITY
            + " INTEGER,"
            + COLUMN_PLOT_SECTOR
            + " TEXT,"
            + COLUMN_PLOT_TYPE
            + " TEXT,"
            + COLUMN_PLOT_STATUS
            + " TEXT,"
            + COLUMN_PLOT_CUSTOMER_ID
            + " INTEGER,"
            + COLUMN_PLOT_BOOKED_BY
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_PLOT_COORDINATES
            + " TEXT,"
            + COLUMN_PLOT_DESCRIPTION
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_PROD_ID
            + " INTEGER,"
            + COLUMN_CATEGORY_NAME
            + " TEXT,"
            + COLUMN_CATEGORY_RATE
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_PLOT = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_PLOT;
    private static final String TAG = MstPlotDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static String[] allColumns = {
            COLUMN_CLIENT_PLOT_ID,
            COLUMN_PLOT_ID,
            COLUMN_PLOT_NUMBER,
            COLUMN_PLOT_PHASE_ID,
            COLUMN_PLOT_PROJECT_ID,
            COLUMN_PLOT_SECTOR,
            COLUMN_PLOT_SIZE,
            COLUMN_PLOT_LENGTH,
            COLUMN_PLOT_WIDTH,
            COLUMN_PLOT_TYPE,
            COLUMN_PLOT_STATUS,
            COLUMN_PLOT_PRIORITY,
            COLUMN_PLOT_CUSTOMER_ID,
            COLUMN_PLOT_BOOKED_BY,
            COLUMN_CREATED_BY,
            COLUMN_UPDATED_BY,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT,
            COLUMN_PLOT_COORDINATES,
            COLUMN_PLOT_DESCRIPTION,
            COLUMN_PROD_ID,
            COLUMN_CATEGORY_NAME,
            COLUMN_CATEGORY_RATE
    };
    private final Context context;

    private SQLiteDatabase db;

    public MstPlotDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
        this.context = context;
    }

    /***
     * This method is for insert user details into user table
     *
     * @param plotModelArrayList
     */
    public void insertPlotDetailsOfServer(ArrayList<PlotModel> plotModelArrayList) {
        db = getDatabase();
        PlotModel plotModel;
        for (int i = 0; i < plotModelArrayList.size(); i++) {
            plotModel = plotModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLOT_ID, plotModel.getPlot_id());
            values.put(COLUMN_PLOT_NUMBER, plotModel.getPlot_number());
            values.put(COLUMN_PLOT_PHASE_ID, plotModel.getPlot_phase_id());
            values.put(COLUMN_PLOT_PROJECT_ID, plotModel.getPlot_project_id());
            values.put(COLUMN_PLOT_SECTOR, plotModel.getPlot_sector());
            values.put(COLUMN_PLOT_SIZE, plotModel.getPlot_size());
            values.put(COLUMN_PLOT_LENGTH, plotModel.getPlot_length());
            values.put(COLUMN_PLOT_WIDTH, plotModel.getPlot_width());
            values.put(COLUMN_PLOT_TYPE, plotModel.getPlot_type());
            values.put(COLUMN_PLOT_STATUS, plotModel.getPlot_status());
            values.put(COLUMN_PLOT_PRIORITY, plotModel.getPlot_priority());
            values.put(COLUMN_PLOT_CUSTOMER_ID, plotModel.getPlot_customer_id());
            values.put(COLUMN_PLOT_BOOKED_BY, plotModel.getPlot_booked_by());
            values.put(COLUMN_CREATED_BY, plotModel.getCreated_by());
            values.put(COLUMN_UPDATED_BY, plotModel.getUpdated_by());
            values.put(COLUMN_CREATED_AT, plotModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, plotModel.getUpdated_at());
            values.put(COLUMN_PLOT_COORDINATES, plotModel.getPlot_coordinates());
            values.put(COLUMN_PLOT_DESCRIPTION, plotModel.getPlot_description());
            values.put(COLUMN_PROD_ID, plotModel.getProd_id());
            values.put(COLUMN_CATEGORY_NAME, plotModel.getCat_name());
            values.put(COLUMN_CATEGORY_RATE, plotModel.getCat_rate());

            String whereClause = COLUMN_PLOT_ID + " = ? ";
            String whereArgs[] = {String.valueOf(plotModel.getPlot_id())};
            long updatedCount = db.update(TABLE_NAME_MST_PLOT, values, whereClause, whereArgs);
            Log.e(TAG, "insertPlotDetailsOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_MST_PLOT, null, values);
                Log.e(TAG, "insertPlotDetailsOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }

    private PlotModel cursorToModel(Cursor cursor) {
        PlotModel model = new PlotModel();
        model.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        model.setCreated_by(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_BY)));
//        model.setCustomer(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_CUSTOMER_ID)));
//        model.setFeatures(cursor.getS);
        model.setPlot_booked_by(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_BOOKED_BY)));
        model.setPlot_coordinates(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_COORDINATES)));
        model.setPlot_customer_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_CUSTOMER_ID)));
        model.setPlot_description(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_DESCRIPTION)));
        model.setPlot_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_ID)));
        model.setPlot_length(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_LENGTH)));
        model.setPlot_number(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_NUMBER)));
        model.setPlot_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PHASE_ID)));
        model.setPlot_priority(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PRIORITY)));
        model.setPlot_sector(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_SECTOR)));
        model.setPlot_type(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_TYPE)));
        model.setPlot_project_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PROJECT_ID)));
        model.setPlot_width(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_WIDTH)));
        model.setPlot_status(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_STATUS)));
//        model.setTsa(cursor.getString(cursor.getColumnIndex(COLUMN_)));
        model.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        model.setUpdated_by(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_BY)));
        model.setPlot_size(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_SIZE)));
        model.setProd_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROD_ID)));
        model.setCat_name(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
        model.setCat_rate(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_RATE)));
        return model;
    }

    public List<PlotModel> getPlots() {
        List<PlotModel> modelList = new ArrayList<>();
        db = getDatabase();
        Cursor cursor = db.query(TABLE_NAME_MST_PLOT, allColumns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursorToPlot(cursor) != null) {
                modelList.add(cursorToPlot(cursor));
            }
        }
        return modelList;
    }

    private PlotModel cursorToPlot(Cursor cursor) {
        PlotModel model = new PlotModel();
        model.setPlot_size(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_SIZE)));
        model.setUpdated_by(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_BY)));
        model.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        model.setPlot_status(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_STATUS)));
        model.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        model.setCreated_by(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_BY)));
        model.setPlot_booked_by(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_BOOKED_BY)));
        model.setPlot_coordinates(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_COORDINATES)));
        model.setPlot_description(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_DESCRIPTION)));
        model.setPlot_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_ID)));
        model.setPlot_length(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_LENGTH)));
        model.setPlot_number(cursor.getColumnName(cursor.getColumnIndex(COLUMN_PLOT_NUMBER)));
        model.setPlot_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PHASE_ID)));
        model.setPlot_priority(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PRIORITY)));
        model.setPlot_customer_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_CUSTOMER_ID)));
        model.setPlot_project_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_PROJECT_ID)));
        model.setPlot_sector(cursor.getString(cursor.getColumnIndex(COLUMN_PLOT_SECTOR)));
        model.setPlot_width(cursor.getInt(cursor.getColumnIndex(COLUMN_PLOT_WIDTH)));
        model.setProd_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROD_ID)));
        model.setCat_name(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
        model.setCat_rate(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_RATE)));
        return model;
    }

    public ArrayList<PlotModel> getAllPlots(int proj_phas_id) {
        Log.e(TAG, "getAllPlots: ");
        db = getDatabase();
        String selection = COLUMN_PLOT_PHASE_ID + " = ?";
        String selectionArgs[] = {String.valueOf(proj_phas_id)};
        ArrayList<PlotModel> plotModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_MST_PLOT, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                plotModelArrayList.add(cursorToModel(cursor));
            }
        }
        return plotModelArrayList;
    }

    public ArrayList<PlotModel> getAllPlotsOf(ArrayList<Integer> integerArrayList) {
        Log.e(TAG, "getAllPlotsOf: ");
        ArrayList<PlotModel> plotModelArrayList = new ArrayList<>();
        db = getDatabase();
        for (int i = 0; i < integerArrayList.size(); i++) {
            String selection = COLUMN_PLOT_ID + " = ?";
            String[] selectionArgs = {String.valueOf(integerArrayList.get(i))};
            Cursor cursor = db.query(TABLE_NAME_MST_PLOT, allColumns, selection, selectionArgs, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    plotModelArrayList.add(cursorToModel(cursor));
                }
            }
            cursor.close();
        }
        db.close();
        return plotModelArrayList;
    }

    public void updatePlotStatus(PlotModel plotModel, String status) {
        Log.e(TAG, "updatePlotStatus: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLOT_STATUS, status);
        String selection = COLUMN_PLOT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(plotModel.getPlot_id())};
        long response = db.update(TABLE_NAME_MST_PLOT, values, selection, selectionArgs);
        db.close();
        Log.e(TAG, "updatePlotStatus: response: " + response);
    }

    public PlotModel getPlotFromId(String plotId) {
        Log.e(TAG, "getPlotFromId: ");
        db = getDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_MST_PLOT + " WHERE " + COLUMN_PLOT_ID + " = " + plotId, null);

        Log.e(TAG, "getPlotFromId: " + cursor.getCount());
        if (cursor.moveToNext())
            return cursorToModel(cursor);

        return null;
    }

    public boolean isPlotAvailable(int plot_id) {
        db = getDatabase();
        String where = COLUMN_PLOT_ID + " = ? AND " + COLUMN_PLOT_STATUS + " = ? ";
        String[] whereArgs = {String.valueOf(plot_id), GlobalConstant.PLOT_STATUS_AVAILABLE};
        Cursor cursor = db.query(TABLE_NAME_MST_PLOT, allColumns, where, whereArgs, null, null, null);
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