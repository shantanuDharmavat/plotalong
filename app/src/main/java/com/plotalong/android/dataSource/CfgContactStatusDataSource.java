package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 23/6/17.
 */

public class CfgContactStatusDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_CONTACT_STATUS = "cfg_contact_status";
    public static final String COLUMN_CFG_CON_STATUS_ID = "cfg_con_status_id";
    public static final String COLUMN_CFG_CON_STATUS_DEVL_ID = "cfg_con_status_devl_id";
    public static final String COLUMN_CFG_CON_STATUS_CON_ID = "cfg_con_status_con_id";
    public static final String COLUMN_CFG_CON_STATUS_PROJECT_ID = "cfg_con_status_project_id";
    public static final String COLUMN_CFG_CON_STATUS = "cfg_con_status";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String CREATE_TABLE_CFG_CONTACT_STATUS = "CREATE TABLE "
            + TABLE_NAME_CFG_CONTACT_STATUS + "("
            + COLUMN_CFG_CON_STATUS_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CFG_CON_STATUS_DEVL_ID
            + " INTEGER,"
            + COLUMN_CFG_CON_STATUS_CON_ID
            + " INTEGER,"
            + COLUMN_CFG_CON_STATUS_PROJECT_ID
            + " INTEGER,"
            + COLUMN_CFG_CON_STATUS
            + " INTEGER,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_CONTACT_STATUS = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_CONTACT_STATUS;

    public CfgContactStatusDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}
