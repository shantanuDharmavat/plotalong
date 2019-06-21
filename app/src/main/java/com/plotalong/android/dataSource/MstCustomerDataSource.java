package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 1/6/17.
 */

public class MstCustomerDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_MST_CUSTOMER = "mst_customer";
    public static final String COLUMN_CUSTOMER_CLIENT_ID = "cust_client_id";
    public static final String COLUMN_CUSTOMER_UNIQUE_ID = "cust_unique_id";
    public static final String COLUMN_CUSTOMER_KEY = "cust_key";
    public static final String COLUMN_CUSTOMER_DEVELOPER_ID = "cust_devl_id";
    public static final String COLUMN_CUSTOMER_SP_ID = "cust_spid";
    public static final String COLUMN_CUSTOMER_FIRST_NAME = "cust_first_name";
    public static final String COLUMN_CUSTOMER_LAST_NAME = "cust_last_name";
    public static final String COLUMN_CUSTOMER_MOBILE = "cust_mobile";
    public static final String COLUMN_CUSTOMER_LANDLINE = "cust_landline";
    public static final String COLUMN_CUSTOMER_ALTERNATE_CONTACT = "cust_alternate_contact";
    public static final String COLUMN_CUSTOMER_ADDRESS = "cust_address";
    public static final String COLUMN_CUSTOMER_EMAIL = "cust_email";
    public static final String COLUMN_CUSTOMER_REQUIREMENT = "cust_requirement";
    public static final String COLUMN_CUSTOMER_BUDGET = "cust_budget";
    public static final String COLUMN_CUSTOMER_PREFER_LOCATION = "cust_preferred_location";
    public static final String COLUMN_CUSTOMER_CREATED_AT = "created_at";
    public static final String COLUMN_CUSTOMER_UPDATED_AT = "updated_at";
    public static final String COLUMN_CUSTOMER_PROFILE_PIC = "cust_profile_pic";
    public static final String COLUMN_CUSTOMER_AREA = "cust_area";
    public static final String COLUMN_CUSTOMER_UID = "cust_uid";
    public static final String COLUMN_CUSTOMER_OCCUPATION = "cust_occupation";
    public static final String COLUMN_CUSTOMER_PAN_NO = "cust_pan_no";
    public static final String COLUMN_CUSTOMER_SON_OF = "cust_son_of";
    public static final String COLUMN_CUSTOMER_MARITAL_STATUS = "cust_marital_status";
    public static final String COLUMN_CUSTOMER_DOB = "cust_dob";
    public static final String COLUMN_CUSTOMER_SYNC_STATUS = "cust_sync_status";

    public static final String CREATE_TABLE_MST_CUSTOMER = "CREATE TABLE "
            + TABLE_NAME_MST_CUSTOMER + "("
            + COLUMN_CUSTOMER_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CUSTOMER_UNIQUE_ID
            + " INTEGER,"
            + COLUMN_CUSTOMER_KEY
            + " TEXT,"
            + COLUMN_CUSTOMER_DEVELOPER_ID
            + " INTEGER,"
            + COLUMN_CUSTOMER_SP_ID
            + " INTEGER,"
            + COLUMN_CUSTOMER_FIRST_NAME
            + " TEXT,"
            + COLUMN_CUSTOMER_LAST_NAME
            + " TEXT,"
            + COLUMN_CUSTOMER_MOBILE
            + " TEXT,"
            + COLUMN_CUSTOMER_LANDLINE
            + " TEXT,"
            + COLUMN_CUSTOMER_ALTERNATE_CONTACT
            + " TEXT,"
            + COLUMN_CUSTOMER_ADDRESS
            + " TEXT,"
            + COLUMN_CUSTOMER_EMAIL
            + " TEXT,"
            + COLUMN_CUSTOMER_REQUIREMENT
            + " TEXT,"
            + COLUMN_CUSTOMER_BUDGET
            + " TEXT,"
            + COLUMN_CUSTOMER_PREFER_LOCATION
            + " TEXT,"
            + COLUMN_CUSTOMER_CREATED_AT
            + " TEXT,"
            + COLUMN_CUSTOMER_UPDATED_AT
            + " TEXT,"
            + COLUMN_CUSTOMER_PROFILE_PIC
            + " TEXT,"
            + COLUMN_CUSTOMER_AREA
            + " TEXT,"
            + COLUMN_CUSTOMER_UID
            + " TEXT,"
            + COLUMN_CUSTOMER_OCCUPATION
            + " TEXT,"
            + COLUMN_CUSTOMER_PAN_NO
            + " TEXT,"
            + COLUMN_CUSTOMER_SON_OF
            + " TEXT,"
            + COLUMN_CUSTOMER_MARITAL_STATUS
            + " TEXT,"
            + COLUMN_CUSTOMER_DOB
            + " TEXT,"
            + COLUMN_CUSTOMER_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_MST_CUSTOMER = "DROP TABLE IF EXISTS " + TABLE_NAME_MST_CUSTOMER;
    public static final String allColumns[] = {COLUMN_CUSTOMER_UNIQUE_ID, COLUMN_CUSTOMER_CLIENT_ID, COLUMN_CUSTOMER_KEY, COLUMN_CUSTOMER_DEVELOPER_ID,
            COLUMN_CUSTOMER_SP_ID, COLUMN_CUSTOMER_FIRST_NAME, COLUMN_CUSTOMER_LAST_NAME, COLUMN_CUSTOMER_MOBILE,
            COLUMN_CUSTOMER_LANDLINE, COLUMN_CUSTOMER_ALTERNATE_CONTACT, COLUMN_CUSTOMER_ADDRESS, COLUMN_CUSTOMER_EMAIL,
            COLUMN_CUSTOMER_REQUIREMENT, COLUMN_CUSTOMER_BUDGET, COLUMN_CUSTOMER_PREFER_LOCATION, COLUMN_CUSTOMER_CREATED_AT,
            COLUMN_CUSTOMER_UPDATED_AT, COLUMN_CUSTOMER_PROFILE_PIC, COLUMN_CUSTOMER_AREA, COLUMN_CUSTOMER_UID,
            COLUMN_CUSTOMER_OCCUPATION, COLUMN_CUSTOMER_PAN_NO, COLUMN_CUSTOMER_SON_OF,
            COLUMN_CUSTOMER_MARITAL_STATUS, COLUMN_CUSTOMER_DOB, COLUMN_CUSTOMER_SYNC_STATUS};
    private static final String TAG = MstCustomerDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstCustomerDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    /***
     * This method is for insert user details into user table
     *
     * @param customerDataModelArrayList
     */
    public void insertCustomersOfServers(ArrayList<CustomerDataModel> customerDataModelArrayList) {
        Log.e(TAG, "insertCustomerDetails: " + customerDataModelArrayList.size());
        db = getDatabase();
        CustomerDataModel customerDataModel;
        for (int i = 0; i < customerDataModelArrayList.size(); i++) {
            customerDataModel = customerDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_CUSTOMER_UNIQUE_ID, customerDataModel.getCust_unique_id());
            values.put(COLUMN_CUSTOMER_KEY, customerDataModel.getCust_key());
            values.put(COLUMN_CUSTOMER_DEVELOPER_ID, customerDataModel.getCust_devl_id());
            values.put(COLUMN_CUSTOMER_SP_ID, customerDataModel.getCust_spid());
            values.put(COLUMN_CUSTOMER_FIRST_NAME, customerDataModel.getCust_first_name());
            values.put(COLUMN_CUSTOMER_LAST_NAME, customerDataModel.getCust_last_name());
            values.put(COLUMN_CUSTOMER_MOBILE, customerDataModel.getCust_mobile());
            values.put(COLUMN_CUSTOMER_LANDLINE, customerDataModel.getCust_landline());
            values.put(COLUMN_CUSTOMER_ALTERNATE_CONTACT, customerDataModel.getCust_alternate_contact());
            values.put(COLUMN_CUSTOMER_ADDRESS, customerDataModel.getCust_address());
            values.put(COLUMN_CUSTOMER_EMAIL, customerDataModel.getCust_email());
            values.put(COLUMN_CUSTOMER_REQUIREMENT, customerDataModel.getCust_requirement());
            values.put(COLUMN_CUSTOMER_BUDGET, customerDataModel.getCust_budget());
            values.put(COLUMN_CUSTOMER_PREFER_LOCATION, customerDataModel.getCust_preferred_location());
            values.put(COLUMN_CUSTOMER_CREATED_AT, customerDataModel.getCreated_at());
            values.put(COLUMN_CUSTOMER_UPDATED_AT, customerDataModel.getUpdated_at());
            values.put(COLUMN_CUSTOMER_PROFILE_PIC, customerDataModel.getCust_profile_pic());
            values.put(COLUMN_CUSTOMER_AREA, customerDataModel.getCust_area());
            values.put(COLUMN_CUSTOMER_UID, customerDataModel.getCust_uid());
            values.put(COLUMN_CUSTOMER_OCCUPATION, customerDataModel.getCust_occupation());
            values.put(COLUMN_CUSTOMER_PAN_NO, customerDataModel.getCust_pan_no());
            values.put(COLUMN_CUSTOMER_SON_OF, customerDataModel.getCust_son_of());
            values.put(COLUMN_CUSTOMER_MARITAL_STATUS, customerDataModel.getCust_marital_status());
            values.put(COLUMN_CUSTOMER_DOB, customerDataModel.getCust_dob());
            values.put(COLUMN_CUSTOMER_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_CUSTOMER_UNIQUE_ID + " = ? ";
            String whereArgs[] = {String.valueOf(customerDataModel.getCust_unique_id())};
            long updatedCount = db.update(TABLE_NAME_MST_CUSTOMER, values, whereClause, whereArgs);
            Log.e(TAG, "insertCustomerDetails: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_MST_CUSTOMER, null, values);
                Log.e(TAG, "insertCustomerDetails: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }

    public ArrayList<CustomerDataModel> getAllCustomer(int developerId) {
        Log.e(TAG, "getAllCustomer: " + developerId);
        db = getDatabase();
        String selection = COLUMN_CUSTOMER_DEVELOPER_ID + " = ?";
        String selectionArgs[] = {String.valueOf(developerId)};
        ArrayList<CustomerDataModel> customerDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_MST_CUSTOMER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return customerDataModelArrayList;
    }

    private CustomerDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");
        CustomerDataModel customerDataModel = new CustomerDataModel();
        customerDataModel.setCust_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_CLIENT_ID)));
        customerDataModel.setCust_unique_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_UNIQUE_ID)));
        customerDataModel.setCust_key(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_KEY)));
        customerDataModel.setCust_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_DEVELOPER_ID)));
        customerDataModel.setCust_spid(cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_SP_ID)));
        customerDataModel.setCust_first_name(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_FIRST_NAME)));
        customerDataModel.setCust_last_name(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_LAST_NAME)));
        customerDataModel.setCust_mobile(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_MOBILE)));
        customerDataModel.setCust_landline(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_LANDLINE)));
        customerDataModel.setCust_alternate_contact(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_ALTERNATE_CONTACT)));
        customerDataModel.setCust_address(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_ADDRESS)));
        customerDataModel.setCust_email(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_EMAIL)));
        customerDataModel.setCust_requirement(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_REQUIREMENT)));
        customerDataModel.setCust_budget(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_BUDGET)));
        customerDataModel.setCust_preferred_location(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_PREFER_LOCATION)));
        customerDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_CREATED_AT)));
        customerDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_UPDATED_AT)));
        customerDataModel.setCust_profile_pic(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_PROFILE_PIC)));
        customerDataModel.setCust_area(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_AREA)));
        customerDataModel.setCust_uid(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_UID)));
        customerDataModel.setCust_occupation(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_OCCUPATION)));
        customerDataModel.setCust_pan_no(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_PAN_NO)));
        customerDataModel.setCust_son_of(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_SON_OF)));
        customerDataModel.setCust_marital_status(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_MARITAL_STATUS)));
        customerDataModel.setCust_dob(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_DOB)));
        customerDataModel.setCustomerSelectedFlag(0);
        customerDataModel.setCust_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_SYNC_STATUS)));
        return customerDataModel;
    }

    public ArrayList<CustomerDataModel> getAllUnsyncCustomer(String lastSyncTime) {
        Log.e(TAG, "getAllUnsyncCustomer: ");
        db = getDatabase();
        ArrayList<CustomerDataModel> customerDataModelArrayList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME_MST_CUSTOMER + " WHERE " + COLUMN_CUSTOMER_UPDATED_AT + " >= '" + lastSyncTime + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return customerDataModelArrayList;
    }

    public ArrayList<CustomerDataModel> getAllCustomer() {
        Log.e(TAG, "getAllCustomer: ");
        db = getDatabase();
        ArrayList<CustomerDataModel> customerDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_MST_CUSTOMER, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return customerDataModelArrayList;
    }

    public ArrayList<CustomerDataModel> getAllDirtyCustomers() {
        Log.e(TAG, "getAllDirtyCustomers: ");
        db = getDatabase();
        ArrayList<CustomerDataModel> customerDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_CUSTOMER_SYNC_STATUS + " = ? OR " + COLUMN_CUSTOMER_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_MST_CUSTOMER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return customerDataModelArrayList;
    }

    public void deleteDirtyCustomers(ArrayList<CustomerDataModel> customerDataModelArrayList) {
        Log.e(TAG, "deleteDirtyCustomers: ");
        db = getDatabase();
        for (int i = 0; i < customerDataModelArrayList.size(); i++) {
            CustomerDataModel customerDataModel = customerDataModelArrayList.get(i);
            String whereClause = COLUMN_CUSTOMER_CLIENT_ID + " = ? AND " + COLUMN_CUSTOMER_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(customerDataModel.getCust_client_id()), customerDataModel.getCust_sync_status()};
            int deletedId = db.delete(TABLE_NAME_MST_CUSTOMER, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyCustomers: " + deletedId);
        }
        db.close();
    }

    public String insertCustomerAndReturnInsertedId(CustomerDataModel customerDataModel) {
        Log.e(TAG, "insertCustomerAndReturnInsertedId: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_UNIQUE_ID, customerDataModel.getCust_unique_id());
        values.put(COLUMN_CUSTOMER_KEY, customerDataModel.getCust_key());
        values.put(COLUMN_CUSTOMER_DEVELOPER_ID, customerDataModel.getCust_devl_id());
        values.put(COLUMN_CUSTOMER_SP_ID, customerDataModel.getCust_spid());
        values.put(COLUMN_CUSTOMER_FIRST_NAME, customerDataModel.getCust_first_name());
        values.put(COLUMN_CUSTOMER_LAST_NAME, customerDataModel.getCust_last_name());
        values.put(COLUMN_CUSTOMER_MOBILE, customerDataModel.getCust_mobile());
        values.put(COLUMN_CUSTOMER_LANDLINE, customerDataModel.getCust_landline());
        values.put(COLUMN_CUSTOMER_ALTERNATE_CONTACT, customerDataModel.getCust_alternate_contact());
        values.put(COLUMN_CUSTOMER_ADDRESS, customerDataModel.getCust_address());
        values.put(COLUMN_CUSTOMER_EMAIL, customerDataModel.getCust_email());
        values.put(COLUMN_CUSTOMER_REQUIREMENT, customerDataModel.getCust_requirement());
        values.put(COLUMN_CUSTOMER_BUDGET, customerDataModel.getCust_budget());
        values.put(COLUMN_CUSTOMER_PREFER_LOCATION, customerDataModel.getCust_preferred_location());
        values.put(COLUMN_CUSTOMER_CREATED_AT, customerDataModel.getCreated_at());
        values.put(COLUMN_CUSTOMER_UPDATED_AT, customerDataModel.getUpdated_at());
        values.put(COLUMN_CUSTOMER_PROFILE_PIC, customerDataModel.getCust_profile_pic());
        values.put(COLUMN_CUSTOMER_AREA, customerDataModel.getCust_area());
        values.put(COLUMN_CUSTOMER_UID, customerDataModel.getCust_uid());
        values.put(COLUMN_CUSTOMER_OCCUPATION, customerDataModel.getCust_occupation());
        values.put(COLUMN_CUSTOMER_PAN_NO, customerDataModel.getCust_pan_no());
        values.put(COLUMN_CUSTOMER_SON_OF, customerDataModel.getCust_son_of());
        values.put(COLUMN_CUSTOMER_MARITAL_STATUS, customerDataModel.getCust_marital_status());
        values.put(COLUMN_CUSTOMER_DOB, customerDataModel.getCust_dob());
        values.put(COLUMN_CUSTOMER_SYNC_STATUS, GlobalConstant.INSERTED);

        long insertedCount = db.insert(TABLE_NAME_MST_CUSTOMER, null, values);
        Log.e(TAG, "insertCustomerAndReturnInsertedId: " + insertedCount);
        db.close();
        return String.valueOf(insertedCount);
    }

    public void updateCustUniqueId(String customerInsertedId) {
        Log.e(TAG, "updateCustUniqueId: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_UNIQUE_ID, customerInsertedId);
        String where = COLUMN_CUSTOMER_CLIENT_ID + " = ? ";
        String[] whereArgs = {customerInsertedId};
        int updatedId = db.update(TABLE_NAME_MST_CUSTOMER, contentValues, where, whereArgs);
        Log.e(TAG, "updateCustUniqueId: " + updatedId);
        db.close();
    }
}