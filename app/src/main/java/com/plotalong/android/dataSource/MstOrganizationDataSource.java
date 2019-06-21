package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class MstOrganizationDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_ORGANIZATION = "mst_organization";
    public static final String COLUMN_ORG_ID = "org_id";
    public static final String COLUMN_ORG_KEY = "org_key";
    public static final String COLUMN_ORG_NAME = "org_name";
    public static final String COLUMN_ORG_DESC = "org_desc";
    public static final String COLUMN_ORG_TYPE_ID = "org_type_id";
    public static final String COLUMN_ORG_DEVELOPER_ID = "org_developer_id";
    public static final String COLUMN_ORG_OWNER_ID = "org_owner_id";
    public static final String COLUMN_ORG_UOM_ID = "org_uom_id";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_ORG_LOGO = "org_logo";
    public static final String COLUMN_ORG_COORDS = "org_coords";
    public static final String COLUMN_ORG_RERA_CODE = "org_rera_code";
    public static final String COLUMN_ORG_RERA_RENEW_DATE = "org_rera_renew_date";
    public static final String COLUMN_ORG_LAUNCH_DATE = "org_launch_date";



    public static final String CREATE_TABLE_MST_ORGANIZATION = "CREATE TABLE "
            + TABLE_NAME_MST_ORGANIZATION + "("
            + COLUMN_ORG_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_ORG_KEY
            + " TEXT,"
            + COLUMN_ORG_NAME
            + " TEXT,"
            + COLUMN_ORG_DESC
            + " TEXT,"
            + COLUMN_ORG_TYPE_ID
            + " TEXT,"
            + COLUMN_ORG_DEVELOPER_ID
            + " INTEGER,"
            + COLUMN_ORG_OWNER_ID
            + " INTEGER,"
            + COLUMN_ORG_UOM_ID
            + " INTEGER,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_ORG_LOGO
            + " TEXT,"
            + COLUMN_ORG_COORDS
            + " TEXT,"
            + COLUMN_ORG_RERA_CODE
            + " TEXT,"
            + COLUMN_ORG_RERA_RENEW_DATE
            + " TEXT,"
            + COLUMN_ORG_LAUNCH_DATE
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_ORGANIZATION = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_ORGANIZATION;

    public MstOrganizationDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}
