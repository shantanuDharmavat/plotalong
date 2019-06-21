package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.listener.UserLoginListener;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationResponseModel;

/**
 * Created by kbhakade on 5/7/17.
 */

public class MstUserDetailsDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_USER_DETAILS = "mst_user_details";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_EMAIL_ID = "user_email_id";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_FIRST_NAME = "user_first_name";
    public static final String COLUMN_USER_LAST_NAME = "user_last_name";
    public static final String COLUMN_USER_MOBILE = "user_mobile";
    public static final String COLUMN_DEVICE_IS_ACTIVE = "device_is_active";
    public static final String COLUMN_USER_IS_ACTIVE = "user_is_active";
    public static final String COLUMN_DEVL_ID = "devl_id";
    public static final String COLUMN_DEVC_API_KEY = "devc_api_key";
    public static final String COLUMN_AUTH_API_KEY = "auth_api_key";

    public static final String[] allColumn = {COLUMN_ID, COLUMN_USER_ID, COLUMN_USER_EMAIL_ID,
            COLUMN_USER_PASSWORD, COLUMN_USER_FIRST_NAME, COLUMN_USER_LAST_NAME, COLUMN_USER_MOBILE,
            COLUMN_DEVICE_IS_ACTIVE,COLUMN_USER_IS_ACTIVE,COLUMN_DEVL_ID, COLUMN_DEVC_API_KEY, COLUMN_AUTH_API_KEY};


    public static final String CREATE_TABLE_MST_USER_DETAILS = "CREATE TABLE "
            + TABLE_NAME_MST_USER_DETAILS + "("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_USER_ID
            + " INTEGER,"
            + COLUMN_USER_EMAIL_ID
            + " TEXT,"
            + COLUMN_USER_PASSWORD
            + " TEXT,"
            + COLUMN_USER_FIRST_NAME
            + " TEXT,"
            + COLUMN_USER_LAST_NAME
            + " TEXT,"
            + COLUMN_USER_MOBILE
            + " TEXT,"
            + COLUMN_DEVICE_IS_ACTIVE
            + " TEXT,"
            + COLUMN_USER_IS_ACTIVE
            + " TEXT,"
            + COLUMN_DEVL_ID
            + " INTEGER,"
            + COLUMN_DEVC_API_KEY
            + " TEXT,"
            + COLUMN_AUTH_API_KEY
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_USER_DETAILS = "DROP TABLE IF EXISTS " + CREATE_TABLE_MST_USER_DETAILS;
    private static final String TAG = MstUserDetailsDataSource.class.getSimpleName();
    private SQLiteDatabase db;

    public MstUserDetailsDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertUserDetails(RegistrationResponseModel registrationResponseModel, String encryptedPassword) {
        Log.e(TAG, "insertUserDetails: " + registrationResponseModel.toString());
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, registrationResponseModel.getData().getUser_id());
        values.put(COLUMN_USER_EMAIL_ID, registrationResponseModel.getData().getUser_email_id());
        values.put(COLUMN_USER_PASSWORD, encryptedPassword);
        values.put(COLUMN_USER_FIRST_NAME, registrationResponseModel.getData().getUser_first_name());
        values.put(COLUMN_USER_LAST_NAME, registrationResponseModel.getData().getUser_last_name());
        values.put(COLUMN_USER_MOBILE, registrationResponseModel.getData().getUser_mobile());
        values.put(COLUMN_DEVICE_IS_ACTIVE, registrationResponseModel.getDeviceIsActive());
        values.put(COLUMN_USER_IS_ACTIVE, registrationResponseModel.getUserIsActive());
        values.put(COLUMN_DEVL_ID, registrationResponseModel.getData().getDevl_id());
        values.put(COLUMN_DEVC_API_KEY, registrationResponseModel.getData().getDevc_api_key());
        values.put(COLUMN_AUTH_API_KEY, registrationResponseModel.getData().getAuth_api_key());

        String whereClause = COLUMN_USER_ID + " = ? ";
        String whereArgs[] = {String.valueOf(registrationResponseModel.getData().getUser_id())};
        long updatedCount = db.update(TABLE_NAME_MST_USER_DETAILS, values, whereClause, whereArgs);
        Log.e(TAG, "insertUserDetails: updatedCount = " + updatedCount);
        if (updatedCount <= 0) {
            long insertedCount = db.insert(TABLE_NAME_MST_USER_DETAILS, null, values);
            Log.e(TAG, "insertUserDetails: insertedCount = " + insertedCount);
        }
        db.close();
    }

    public void validateUser(UserLoginListener userLoginListener, String loginId, String encryptedPassword) {
        Log.e(TAG, "validateUser: ");
        db = getDatabase();
        String selection = COLUMN_USER_EMAIL_ID + " =? And " + COLUMN_USER_PASSWORD + " =? ";
        String[] selectionArgs = {loginId, encryptedPassword};
        Cursor cursor = db.query(TABLE_NAME_MST_USER_DETAILS, allColumn, selection, selectionArgs, null, null, null, null);
        if (cursor.getCount() <= 0) {
            userLoginListener.onLoginFail("User not available, Please connect device with network and try again !");
        } else {
            userLoginListener.onLoginSuccessFromLocal();
        }
        cursor.close();
        db.close();
    }

    public void deleteAllTablesExceptUserTable() {
        db = getDatabase();
        db.delete(CfgCustomerGroupDataSource.TABLE_NAME_CFG_CUST_GROUP,null,null);
        db.delete(CfgAddressDataSource.TABLE_NAME_CFG_ADDRESS,null,null);
        db.delete(CfgProjectBankDataSource.TABLE_NAME_CFG_PROJ_BANK,null,null);
        db.delete(CfgContentDataSource.TABLE_NAME_CFG_CONTENT,null,null);
        db.delete(CfgContactStatusDataSource.TABLE_NAME_CFG_CONTACT_STATUS,null,null);
        db.delete(MstCustomerDataSource.TABLE_NAME_MST_CUSTOMER,null,null);
        db.delete(MstPlotDataSource.TABLE_NAME_MST_PLOT,null,null);
        db.delete(MstProjectDataSource.TABLE_NAME_MST_PROJECT,null,null);
        db.delete(CfgAddressDataSource.TABLE_NAME_CFG_ADDRESS,null,null);
        db.delete(MstOrganizationDataSource.TABLE_NAME_MST_ORGANIZATION,null,null);
        db.delete(MstUserAccessDataSource.TABLE_NAME_MST_USER_ACCESS,null,null);
        db.delete(TrnSalesActivityDataSource.TABLE_NAME_TRN_SALES_ACTI,null,null);
        db.delete(TrnBkformPaymentDetailsDataSource.TABLE_NAME_TRN_BKFORM_PAYMENT_DETAILS,null,null);
        db.delete(TrnBookingFormDataSource.TABLE_NAME_TRN_BOOKING_FORM,null,null);
        db.delete(TrnDeviceLoggerDataSource.TABLE_NAME_TRN_DEVICE_LOGGER,null,null);
    }
}