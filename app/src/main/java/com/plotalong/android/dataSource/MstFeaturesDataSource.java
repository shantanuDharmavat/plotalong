package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.MstFeatureDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 15/11/17.
 */

public class MstFeaturesDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_FEATURES = "mst_features";
    public static final String COLUMN_CLIENT_MST_ID = "client_mst_id";
    public static final String COLUMN_FEAT_ID = "feat_id";
    public static final String COLUMN_FEAT_NAME = "feat_name";
    public static final String COLUMN_FEAT_DESCRIPTION = "feat_description";
    public static final String COLUMN_FEAT_IS_ACTIVE = "feat_isactive";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_FEAT_ICON = "feat_icon";
    public static final String COLUMN_DELETED_AT = "deleted_at";


    public static final String CREATE_TABLE_MST_FEATURES = "CREATE TABLE "
            + TABLE_NAME_MST_FEATURES + "("
            + COLUMN_CLIENT_MST_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_FEAT_ID
            + " INTEGER,"
            + COLUMN_FEAT_NAME
            + " TEXT,"
            + COLUMN_FEAT_DESCRIPTION
            + " TEXT,"
            + COLUMN_FEAT_IS_ACTIVE
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_FEAT_ICON
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_FEATURES = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_FEATURES;
    private String TAG = MstFeaturesDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstFeaturesDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertMstFeaturesOfServer(ArrayList<MstFeatureDataModel> mst_features) {
        Log.e(TAG, "insertMstFeaturesOfServer: ");
        db = getDatabase();
        MstFeatureDataModel mstFeatureDataModel;
        for (int i = 0; i < mst_features.size(); i++) {
            mstFeatureDataModel = mst_features.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_FEAT_ID, mstFeatureDataModel.getFeat_id());
            values.put(COLUMN_FEAT_NAME, mstFeatureDataModel.getFeat_name());
            values.put(COLUMN_FEAT_DESCRIPTION, mstFeatureDataModel.getFeat_description());
            values.put(COLUMN_FEAT_IS_ACTIVE, mstFeatureDataModel.getFeat_isactive());
            values.put(COLUMN_CREATED_AT, mstFeatureDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, mstFeatureDataModel.getUpdated_at());
            values.put(COLUMN_CREATED_BY, mstFeatureDataModel.getCreated_by());
            values.put(COLUMN_FEAT_ICON, mstFeatureDataModel.getFeat_icon());
            values.put(COLUMN_DELETED_AT, mstFeatureDataModel.getDeleted_at());

            String whereClause = COLUMN_FEAT_ID + " = ? ";
            String whereArgs[] = {String.valueOf(mstFeatureDataModel.getFeat_id())};
            long updatedCount = db.update(TABLE_NAME_MST_FEATURES, values, whereClause, whereArgs);
            Log.e(TAG, "insertMstFeaturesOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_MST_FEATURES, null, values);
                Log.e(TAG, "insertMstFeaturesOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }
}