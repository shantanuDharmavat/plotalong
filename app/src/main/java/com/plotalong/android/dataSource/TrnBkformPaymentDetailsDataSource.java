package com.plotalong.android.dataSource;

import android.content.Context;

/**
 * Created by kbhakade on 22/6/17.
 */

public class TrnBkformPaymentDetailsDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_TRN_BKFORM_PAYMENT_DETAILS = "trn_bkform_payment_details";
    public static final String COLUMN_BK_ID = "bk_id";
    public static final String COLUMN_BK_FORM_ID = "bk_form_id";
    public static final String COLUMN_BK_AMOUNT = "bk_amount";
    public static final String COLUMN_BK_PAYMENT_MODE = "bk_payment_mode";
    public static final String COLUMN_BK_DRAWER_BANK = "bk_drawer_bank";
    public static final String COLUMN_BK_DRAWER_BRANCH = "bk_drawer_branch";
    public static final String COLUMN_BK_DRAWER_NAME = "bk_drawer_name";
    public static final String COLUMN_BK_DRAWER_PAN = "bk_drawer_PAN";
    public static final String COLUMN_BK_INSTRUMENT_DATE = "bk_instrument_date";
    public static final String COLUMN_BK_INSTRUMENT_NUMBER = "bk_instrument_number";
    public static final String COLUMN_BK_TRANSFER_TRN_ID = "bk_transfer_trn_id";
    public static final String COLUMN_CREATED_BY = "created_by";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_UPDATED_BY = "updated_by";


    public static final String CREATE_TABLE_TRN_BKFORM_PAYMENT_DETAILS = "CREATE TABLE "
            + TABLE_NAME_TRN_BKFORM_PAYMENT_DETAILS + "("
            + COLUMN_BK_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_BK_FORM_ID
            + " TEXT,"
            + COLUMN_BK_AMOUNT
            + " TEXT,"
            + COLUMN_BK_PAYMENT_MODE
            + " TEXT,"
            + COLUMN_BK_DRAWER_BANK
            + " TEXT,"
            + COLUMN_BK_DRAWER_BRANCH
            + " TEXT,"
            + COLUMN_BK_DRAWER_NAME
            + " TEXT,"
            + COLUMN_BK_DRAWER_PAN
            + " TEXT,"
            + COLUMN_BK_INSTRUMENT_DATE
            + " TEXT,"
            + COLUMN_BK_INSTRUMENT_NUMBER
            + " TEXT,"
            + COLUMN_BK_TRANSFER_TRN_ID
            + " TEXT,"
            + COLUMN_CREATED_BY
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_BY
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_TRN_BKFORM_PAYMENT_DETAILS = "DROP TABLE IF EXISTS " + TABLE_NAME_TRN_BKFORM_PAYMENT_DETAILS;

    public TrnBkformPaymentDetailsDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }
}