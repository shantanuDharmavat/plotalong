package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 23/6/17.
 */

public class TrnVisitDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_VISIT = "trn_visit";
    public static final String COLUMN_TRV_ID = "trv_id";
    public static final String COLUMN_TRV_CUST_UNIQUE_ID = "trv_cust_unique_id";
    public static final String COLUMN_TRV_CONTACT_ID = "trv_contact_id";
    public static final String COLUMN_TRV_NOTES = "trv_notes";
    public static final String COLUMN_TRV_SCHEDULE_DATE = "trv_schedule_date";
    public static final String COLUMN_TRV_STATUS = "trv_status";
    public static final String COLUMN_TRV_SPID = "trv_spid";
    public static final String COLUMN_TRV_DEVL_ID = "trv_devl_id";
    public static final String COLUMN_TRV_PROJ_ID = "trv_proj_id";
    public static final String COLUMN_TRV_PHAS_ID = "trv_phas_id";
    public static final String COLUMN_TRV_END_DATETIME = "trv_end_datetime";
    public static final String COLUMN_TRV_START_DATETIME = "trv_start_datetime";

    public static final String CREATE_TABLE_TRN_VISIT = "CREATE TABLE "
            + TABLE_NAME_TRN_VISIT + "("
            + COLUMN_TRV_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_TRV_CUST_UNIQUE_ID
            + " INTEGER,"
            + COLUMN_TRV_CONTACT_ID
            + " INTEGER,"
            + COLUMN_TRV_NOTES
            + " TEXT,"
            + COLUMN_TRV_SCHEDULE_DATE
            + " TEXT,"
            + COLUMN_TRV_STATUS
            + " TEXT,"
            + COLUMN_TRV_SPID
            + " INTEGER,"
            + COLUMN_TRV_DEVL_ID
            + " INTEGER,"
            + COLUMN_TRV_PROJ_ID
            + " INTEGER,"
            + COLUMN_TRV_PHAS_ID
            + "INTEGER,"
            + COLUMN_TRV_END_DATETIME
            + "TEXT,"
            + COLUMN_TRV_START_DATETIME
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_TRN_VISIT = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_VISIT;

    public TrnVisitDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}