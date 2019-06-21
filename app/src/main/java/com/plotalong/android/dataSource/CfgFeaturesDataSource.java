package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.CfgFeatureDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 22/6/17.
 */

public class CfgFeaturesDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_FEATURES = "cfg_features";
    public static final String COLUMN_CLIENT_CFG_ID = "client_cfg_id";
    public static final String COLUMN_CFG_ID = "cfg_id";
    public static final String COLUMN_CFG_FEATURE_ID = "cpf_feat_id";
    public static final String COLUMN_CFG_PROJECT_ID = "cpf_proj_id";
    public static final String COLUMN_CFG_PHAS_ID = "cpf_phas_id";
    public static final String COLUMN_CFG_PLOT_ID = "cpf_plot_id";
    public static final String COLUMN_CFG_DEVL_ID = "cpf_devl_id";
    public static final String COLUMN_CFG_IS_ACTIVE = "cpf_isactive";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";


    public static final String CREATE_TABLE_CFG_FEATURES = "CREATE TABLE "
            + TABLE_NAME_CFG_FEATURES + "("
            + COLUMN_CLIENT_CFG_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CFG_ID
            + " INTEGER,"
            + COLUMN_CFG_FEATURE_ID
            + " INTEGER,"
            + COLUMN_CFG_PROJECT_ID
            + " INTEGER,"
            + COLUMN_CFG_PHAS_ID
            + " INTEGER,"
            + COLUMN_CFG_PLOT_ID
            + " INTEGER,"
            + COLUMN_CFG_DEVL_ID
            + " INTEGER,"
            + COLUMN_CFG_IS_ACTIVE
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_FEATURES = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_FEATURES;
    private String TAG = CfgFeaturesDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public CfgFeaturesDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertCfgFeaturesOfServer(ArrayList<CfgFeatureDataModel> cfg_features) {
        Log.e(TAG, "insertCfgFeaturesOfServer: ");
        db = getDatabase();
        CfgFeatureDataModel cfgFeatureDataModel;
        for (int i = 0; i < cfg_features.size(); i++) {
            cfgFeatureDataModel = cfg_features.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_CFG_ID, cfgFeatureDataModel.getCpf_id());
            values.put(COLUMN_CFG_FEATURE_ID, cfgFeatureDataModel.getCpf_feat_id());
            values.put(COLUMN_CFG_PROJECT_ID, cfgFeatureDataModel.getCpf_proj_id());
            values.put(COLUMN_CFG_PHAS_ID, cfgFeatureDataModel.getCpf_phas_id());
            values.put(COLUMN_CFG_PLOT_ID, cfgFeatureDataModel.getCpf_plot_id());
            values.put(COLUMN_CFG_DEVL_ID, cfgFeatureDataModel.getCpf_devl_id());
            values.put(COLUMN_CFG_IS_ACTIVE, cfgFeatureDataModel.getCpf_isactive());
            values.put(COLUMN_CREATED_AT, cfgFeatureDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, cfgFeatureDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, cfgFeatureDataModel.getDeleted_at());

            String whereClause = COLUMN_CFG_ID + " = ? ";
            String whereArgs[] = {String.valueOf(cfgFeatureDataModel.getCpf_id())};
            long updatedCount = db.update(TABLE_NAME_CFG_FEATURES, values, whereClause, whereArgs);
            Log.e(TAG, "insertCfgFeaturesOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_CFG_FEATURES, null, values);
                Log.e(TAG, "insertCfgFeaturesOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }
}

