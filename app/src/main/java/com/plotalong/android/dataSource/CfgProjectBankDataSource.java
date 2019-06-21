package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class CfgProjectBankDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_PROJ_BANK = "cfg_proj_bank";
    public static final String COLUMN_PB_ID = "pb_id";
    public static final String COLUMN_PB_PROJECT_ID = "pb_proj_id";
    public static final String COLUMN_PB_BANK_ID = "pb_bank_id";
    public static final String COLUMN_PB_LOGO_URL = "pb_logo_url";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_CREATED_AT = "created_at";


    public static final String CREATE_TABLE_CFG_PROJ_BANK = "CREATE TABLE "
            + TABLE_NAME_CFG_PROJ_BANK + "("
            + COLUMN_PB_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PB_PROJECT_ID
            + " INTEGER,"
            + COLUMN_PB_BANK_ID
            + " INTEGER,"
            + COLUMN_PB_LOGO_URL
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_PROJ_BANK = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_PROJ_BANK;

    public CfgProjectBankDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}
