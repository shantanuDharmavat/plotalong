package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.TsaModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 1/6/17.
 */

public class TrnSalesActivityDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_SALES_ACTI = "trn_sales_acti";
    public static final String COLUMN_CLIENT_TSA_ID = "client_tsa_id";
    public static final String COLUMN_TSA_ID = "tsa_id";
    public static final String COLUMN_TSA_DEVL_ID = "tsa_devl_id";
    public static final String COLUMN_TSA_PROJ_ID = "tsa_proj_id";
    public static final String COLUMN_TSA_PHAS_ID = "tsa_phas_id";
    public static final String COLUMN_TSA_PLOT_ID = "tsa_plot_id";
    public static final String COLUMN_TSA_SPID = "tsa_spid";
    public static final String COLUMN_TSA_CONTACT_ID = "tsa_contact_id";
    public static final String COLUMN_TSA_ACTITYPE = "tsa_actitype";
    public static final String COLUMN_TSA_COMMENT = "tsa_comment";
    public static final String COLUMN_TSA_RATING = "tsa_rating";
    public static final String COLUMN_TSA_CREATED_AT = "created_at";
    public static final String COLUMN_TSA_CREATED_BY = "created_by";
    public static final String COLUMN_TSA_ACTI_ID = "tsa_acti_id";
    public static final String COLUMN_TSA_STATUS = "tsa_status";
    public static final String COLUMN_TSA_UPDATED_AT = "updated_at";

    public static final String CREATE_TABLE_TRN_SALES_ACTI = "CREATE TABLE "
            + TABLE_NAME_TRN_SALES_ACTI + "("
            + COLUMN_CLIENT_TSA_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_TSA_ID
            + " INTEGER,"
            + COLUMN_TSA_DEVL_ID
            + " INTEGER,"
            + COLUMN_TSA_PROJ_ID
            + " INTEGER,"
            + COLUMN_TSA_PHAS_ID
            + " INTEGER,"
            + COLUMN_TSA_PLOT_ID
            + " INTEGER,"
            + COLUMN_TSA_SPID
            + " INTEGER,"
            + COLUMN_TSA_CONTACT_ID
            + " INTEGER,"
            + COLUMN_TSA_ACTITYPE
            + " TEXT,"
            + COLUMN_TSA_COMMENT
            + " TEXT,"
            + COLUMN_TSA_RATING
            + " TEXT,"
            + COLUMN_TSA_CREATED_AT
            + " TEXT,"
            + COLUMN_TSA_CREATED_BY
            + " TEXT,"
            + COLUMN_TSA_ACTI_ID
            + " TEXT,"
            + COLUMN_TSA_STATUS
            + " TEXT,"
            + COLUMN_TSA_UPDATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_TRN_SALES_ACTI = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_SALES_ACTI;
    private static final String TAG = TrnSalesActivityDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public TrnSalesActivityDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    /***
     * This method is for insert user details into user table
     * @param tsaModelArrayList
     */
    public void insertTsaDetails(ArrayList<TsaModel> tsaModelArrayList) {
        Log.e(TAG, "insertTsaDetails: " + tsaModelArrayList.size());
        db = getDatabase();
        TsaModel tsaModel;
        for (int i = 0; i < tsaModelArrayList.size(); i++) {
            tsaModel = tsaModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_TSA_ID, tsaModel.getTsa_id());
            values.put(COLUMN_TSA_DEVL_ID, tsaModel.getTsa_devl_id());
            values.put(COLUMN_TSA_PROJ_ID, tsaModel.getTsa_proj_id());
            values.put(COLUMN_TSA_PHAS_ID, tsaModel.getTsa_phas_id());
            values.put(COLUMN_TSA_PLOT_ID, tsaModel.getTsa_plot_id());
            values.put(COLUMN_TSA_SPID, tsaModel.getTsa_spid());
            values.put(COLUMN_TSA_CONTACT_ID, tsaModel.getTsa_contact_id());
            values.put(COLUMN_TSA_ACTITYPE, tsaModel.getTsa_actitype());
            values.put(COLUMN_TSA_COMMENT, tsaModel.getTsa_comment());
            values.put(COLUMN_TSA_RATING, tsaModel.getTsa_rating());
            values.put(COLUMN_TSA_CREATED_AT, tsaModel.getCreated_at());
            values.put(COLUMN_TSA_CREATED_BY, tsaModel.getCreated_by());
            values.put(COLUMN_TSA_ACTI_ID, tsaModel.getTsa_acti_id());
            values.put(COLUMN_TSA_STATUS, tsaModel.getTsa_status());
            values.put(COLUMN_TSA_UPDATED_AT, tsaModel.getUpdated_at());

            db.insert(TABLE_NAME_TRN_SALES_ACTI, null, values);
        }
        Log.e(TAG, "insertTsaDetails: TSA INSERTED SUCCESSFULLY");
        db.close();
    }
}
