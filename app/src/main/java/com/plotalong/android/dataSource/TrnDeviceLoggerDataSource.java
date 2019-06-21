package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class TrnDeviceLoggerDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_DEVICE_LOGGER = "trn_device_logger";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TRN_LOGGEDIN_USER_ID = "trn_loggedin_user_id";
    public static final String COLUMN_TRN_WHO = "trn_who";
    public static final String COLUMN_TRN_WHEN = "trn_when";
    public static final String COLUMN_TRN_WHAT = "trn_what";
    public static final String COLUMN_TRN_WHERE = "trn_where";
    public static final String COLUMN_TRN_CATEGORY = "trn_category";
    public static final String COLUMN_TRN_PROJ_ID = "trn_proj_id";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";


    public static final String CREATE_TABLE_TRN_DEVICE_LOGGER = "CREATE TABLE "
            + TABLE_NAME_TRN_DEVICE_LOGGER + "("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_TRN_LOGGEDIN_USER_ID
            + " INTEGER,"
            + COLUMN_TRN_WHO
            + " TEXT,"
            + COLUMN_TRN_WHEN
            + " TEXT,"
            + COLUMN_TRN_WHAT
            + " TEXT,"
            + COLUMN_TRN_WHERE
            + " TEXT,"
            + COLUMN_TRN_CATEGORY
            + " TEXT,"
            + COLUMN_TRN_PROJ_ID
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_TRN_DEVICE_LOGGER = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_DEVICE_LOGGER;

    public TrnDeviceLoggerDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}

