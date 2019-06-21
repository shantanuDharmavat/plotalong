package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class TrnBookingFormDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_BOOKING_FORM = "trn_booking_form";
    public static final String COLUMN_TBF_ID = "tbf_id";
    public static final String COLUMN_TBF_FORM_ID = "tbf_form_id";
    public static final String COLUMN_TBF_TSA_ID = "tbf_tsa_id";
    public static final String COLUMN_TBF_RATE = "tbf_rate";
    public static final String COLUMN_TBF_SP_COMMENTS = "tbf_sp_comments";
    public static final String COLUMN_TBF_BOOKING_DATE = "tbf_booking_date";
    public static final String COLUMN_TBF_BOOKING_AMOUNT = "tbf_booking_amount";
    public static final String COLUMN_TBF_BOOKING_TERMS = "tbf_booking_terms";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_BY = "updated_by";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    public static final String CREATE_TABLE_TRN_BOOKING_FORM = "CREATE TABLE "
            + TABLE_NAME_TRN_BOOKING_FORM + "("
            + COLUMN_TBF_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_TBF_FORM_ID
            + " TEXT,"
            + COLUMN_TBF_TSA_ID
            + " TEXT,"
            + COLUMN_TBF_RATE
            + " TEXT,"
            + COLUMN_TBF_SP_COMMENTS
            + " TEXT,"
            + COLUMN_TBF_BOOKING_DATE
            + " TEXT,"
            + COLUMN_TBF_BOOKING_AMOUNT
            + " TEXT,"
            + COLUMN_TBF_BOOKING_TERMS
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " INTEGER,"
            + COLUMN_UPDATED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_TRN_BOOKING_FORM = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_BOOKING_FORM;

    public TrnBookingFormDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}
