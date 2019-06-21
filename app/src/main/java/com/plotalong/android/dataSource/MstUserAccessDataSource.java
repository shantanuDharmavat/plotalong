package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class MstUserAccessDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_USER_ACCESS = "mst_useraccess";
    public static final String COLUMN_ACCS_ID = "accs_id";
    public static final String COLUMN_ACCS_LEVEL = "accs_level";
    public static final String COLUMN_ACCS_CREATED_AT = "created_at";
    public static final String COLUMN_ACCS_UPDATED_AT = "updated_at";


    public static final String CREATE_TABLE_MST_USER_ACCESS = "CREATE TABLE "
            + TABLE_NAME_MST_USER_ACCESS + "("
            + COLUMN_ACCS_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_ACCS_LEVEL
            + " TEXT,"
            + COLUMN_ACCS_CREATED_AT
            + " TEXT,"
            + COLUMN_ACCS_UPDATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_USER_ACCESS = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_USER_ACCESS;

    public MstUserAccessDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}
