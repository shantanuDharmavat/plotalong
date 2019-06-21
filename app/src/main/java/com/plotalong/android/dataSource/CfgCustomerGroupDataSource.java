package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 22/6/17.
 */

public class CfgCustomerGroupDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_CUST_GROUP = "cfg_cust_groups";
    public static final String COLUMN_CGC_CLIENT_ID = "cgc_client_id";
    public static final String COLUMN_CGC_ID = "cgc_id";
    public static final String COLUMN_CGC_CUST_ID = "cgc_cust_id";
    public static final String COLUMN_CGC_GROUP_ID = "cgc_group_id";
    public static final String COLUMN_CGC_PHAS_ID = "cgc_phas_id";
    public static final String COLUMN_CGC_STATUS = "cgc_status";
    public static final String COLUMN_CGC_MEMBER_TYPE = "cgc_member_type";
    public static final String COLUMN_CGC_CREATED_AT = "created_at";
    public static final String COLUMN_CGC_UPDATED_AT = "updated_at";
    public static final String COLUMN_CGC_DELETED_AT = "deleted_at";
    public static final String COLUMN_CGC_SYNC_STATUS = "cgc_sync_status";

    public static final String CREATE_TABLE_CFG_CUST_GROUP = "CREATE TABLE "
            + TABLE_NAME_CFG_CUST_GROUP + "("
            + COLUMN_CGC_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CGC_ID
            + " INTEGER,"
            + COLUMN_CGC_CUST_ID
            + " INTEGER,"
            + COLUMN_CGC_PHAS_ID
            + " INTEGER,"
            + COLUMN_CGC_GROUP_ID
            + " TEXT,"
            + COLUMN_CGC_STATUS
            + " TEXT,"
            + COLUMN_CGC_MEMBER_TYPE
            + " TEXT,"
            + COLUMN_CGC_CREATED_AT
            + " TEXT,"
            + COLUMN_CGC_UPDATED_AT
            + " TEXT,"
            + COLUMN_CGC_DELETED_AT
            + " TEXT,"
            + COLUMN_CGC_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_CUST_GROUP = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_CUST_GROUP;


    public static final String allColumns[] = {COLUMN_CGC_CLIENT_ID, COLUMN_CGC_ID, COLUMN_CGC_CUST_ID,
            COLUMN_CGC_GROUP_ID, COLUMN_CGC_PHAS_ID, COLUMN_CGC_STATUS, COLUMN_CGC_MEMBER_TYPE, COLUMN_CGC_CREATED_AT,
            COLUMN_CGC_UPDATED_AT, COLUMN_CGC_DELETED_AT, COLUMN_CGC_SYNC_STATUS};


    private static final String TAG = CfgCustomerGroupDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public CfgCustomerGroupDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public void insertCustomerGroup(CustomerGroupDataModel customerGroupDataModel) {
        Log.e(TAG, "insertCustomerGroup: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CGC_ID, customerGroupDataModel.getCgc_id());
        values.put(COLUMN_CGC_CUST_ID, customerGroupDataModel.getCgc_cust_id());
        values.put(COLUMN_CGC_GROUP_ID, customerGroupDataModel.getCgc_group_id());
        values.put(COLUMN_CGC_PHAS_ID, customerGroupDataModel.getCgc_phas_id());
        values.put(COLUMN_CGC_STATUS, customerGroupDataModel.getCgc_status());
        values.put(COLUMN_CGC_MEMBER_TYPE, customerGroupDataModel.getCgc_member_type());
        values.put(COLUMN_CGC_CREATED_AT, customerGroupDataModel.getCreated_at());
        values.put(COLUMN_CGC_UPDATED_AT, customerGroupDataModel.getUpdated_at());
        values.put(COLUMN_CGC_DELETED_AT, customerGroupDataModel.getDeleted_at());
        values.put(COLUMN_CGC_SYNC_STATUS, GlobalConstant.INSERTED);
        long insertedCount = db.insert(TABLE_NAME_CFG_CUST_GROUP, null, values);
        Log.e(TAG, "insertCustomerGroup: insertedCount = " + insertedCount);
        db.close();
    }

    public ArrayList<CustomerGroupDataModel> getAllCustGroupHavingOpps(int cust_unique_id) {
        Log.e(TAG, "getAllCustGroupHavingOpps: ");
        ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList = new ArrayList<>();
        db = getDatabase();
        String selection = COLUMN_CGC_CUST_ID + " = ? AND " + COLUMN_CGC_STATUS + " = ?";
        String selectionArgs[] = {String.valueOf(cust_unique_id), GlobalConstant.STRING_opps};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerGroupDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return customerGroupDataModelArrayList;
    }

    private CustomerGroupDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");
        CustomerGroupDataModel customerGroupDataModel = new CustomerGroupDataModel();
        customerGroupDataModel.setCgc_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CGC_CLIENT_ID)));
        customerGroupDataModel.setCgc_cust_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CGC_CUST_ID)));
        customerGroupDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_UPDATED_AT)));
        customerGroupDataModel.setCgc_group_id(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_GROUP_ID)));
        customerGroupDataModel.setCgc_phas_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CGC_PHAS_ID)));
        customerGroupDataModel.setCgc_member_type(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_MEMBER_TYPE)));
        customerGroupDataModel.setCgc_status(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_STATUS)));
        customerGroupDataModel.setCgc_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CGC_ID)));
        customerGroupDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_CREATED_AT)));
        customerGroupDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_DELETED_AT)));
        customerGroupDataModel.setCgc_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_CGC_SYNC_STATUS)));
        return customerGroupDataModel;
    }

    public CustomerGroupDataModel getHotLeadCustomerGroupId(int cust_unique_id) {
        Log.e(TAG, "getHotLeadCustomerGroupId: ");
        CustomerGroupDataModel customerGroupDataModel = null;
        db = getDatabase();
        String selection = COLUMN_CGC_CUST_ID + " = ? AND " + COLUMN_CGC_STATUS + " = ?";
        String selectionArgs[] = {String.valueOf(cust_unique_id), GlobalConstant.STRING_hotLead};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerGroupDataModel = cursorToModel(cursor);
            }
        }
        return customerGroupDataModel;
    }

    public CustomerGroupDataModel getWarmLeadCustomerGroupId(int cust_unique_id) {
        Log.e(TAG, "getWarmLeadCustomerGroupId: ");
        CustomerGroupDataModel customerGroupDataModel = null;
        db = getDatabase();
        String selection = COLUMN_CGC_CUST_ID + " = ? AND " + COLUMN_CGC_STATUS + " = ?";
        String selectionArgs[] = {String.valueOf(cust_unique_id), GlobalConstant.STRING_warmLead};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerGroupDataModel = cursorToModel(cursor);
            }
        }
        return customerGroupDataModel;
    }

    public ArrayList<String> getCustomerGroupIdFromCustomerId(int customerId) {
        Log.e(TAG, "getCustomerGroupIdFromCustomerId: customer id: " + customerId);
        db = getDatabase();
        ArrayList<String> groupArray = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM cfg_cust_groups WHERE cgc_cust_id = " + customerId, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String groupId = cursor.getString(cursor.getColumnIndex(COLUMN_CGC_GROUP_ID));
                groupArray.add(groupId);
            }
        }
        cursor.close();
        db.close();
        return groupArray;
    }

    public int getLastCgcId() {
        Log.e(TAG, "getLastCgcId: ");
        db = getDatabase();
        int lastId = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_CFG_CUST_GROUP + " ORDER BY " + COLUMN_CGC_ID + " DESC LIMIT 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            lastId = cursor.getInt(cursor.getColumnIndex(COLUMN_CGC_ID));
        }
        cursor.close();
        db.close();
        return lastId;
    }

    public void updateGroupWithSyncStatusAsUpdated(CustomerDataModel customerDataModel, PhaseDataModel phaseDataModel) {
        Log.e(TAG, "updateGroupWithSyncStatusAsUpdated: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CGC_PHAS_ID, phaseDataModel.getPhas_id());
        contentValues.put(COLUMN_CGC_STATUS, GlobalConstant.STRING_opps);
        contentValues.put(COLUMN_CGC_SYNC_STATUS, GlobalConstant.UPDATED);
        String whereClause = COLUMN_CGC_GROUP_ID + " = ?";
        String[] whereArgs = {customerDataModel.getCustomerTempGroupId()};
        long updatedRow = db.update(TABLE_NAME_CFG_CUST_GROUP, contentValues, whereClause, whereArgs);
        Log.e(TAG, "updateGroupWithSyncStatusAsUpdated: " + updatedRow);
    }

    public void updateGroupWithSyncStatusAsInserted(CustomerDataModel customerDataModel, PhaseDataModel phaseDataModel) {
        Log.e(TAG, "updateGroupWithSyncStatusAsInserted: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CGC_PHAS_ID, phaseDataModel.getPhas_id());
        contentValues.put(COLUMN_CGC_STATUS, GlobalConstant.STRING_opps);
        String whereClause = COLUMN_CGC_GROUP_ID + " = ?";
        String[] whereArgs = {customerDataModel.getCustomerTempGroupId()};
        long updatedRow = db.update(TABLE_NAME_CFG_CUST_GROUP, contentValues, whereClause, whereArgs);
        Log.e(TAG, "updateGroupWithSyncStatusAsInserted: " + updatedRow);
    }

    public ArrayList<CustomerGroupDataModel> getAllDirtyCustomerGroups() {
        Log.e(TAG, "getAllDirtyCustomerGroups: ");
        db = getDatabase();
        ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_CGC_SYNC_STATUS + " = ? OR " + COLUMN_CGC_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerGroupDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return customerGroupDataModelArrayList;
    }

    public void insertCustomerGroupOfServer(ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList) {
        Log.e(TAG, "insertCustomerGroup: " + customerGroupDataModelArrayList.size());
        db = getDatabase();
        CustomerGroupDataModel customerGroupDataModel;
        for (int i = 0; i < customerGroupDataModelArrayList.size(); i++) {
            customerGroupDataModel = customerGroupDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_CGC_ID, customerGroupDataModel.getCgc_id());
            values.put(COLUMN_CGC_CUST_ID, customerGroupDataModel.getCgc_cust_id());
            values.put(COLUMN_CGC_GROUP_ID, customerGroupDataModel.getCgc_group_id());
            values.put(COLUMN_CGC_PHAS_ID, customerGroupDataModel.getCgc_phas_id());
            values.put(COLUMN_CGC_STATUS, customerGroupDataModel.getCgc_status());
            values.put(COLUMN_CGC_MEMBER_TYPE, customerGroupDataModel.getCgc_member_type());
            values.put(COLUMN_CGC_CREATED_AT, customerGroupDataModel.getCreated_at());
            values.put(COLUMN_CGC_UPDATED_AT, customerGroupDataModel.getUpdated_at());
            values.put(COLUMN_CGC_DELETED_AT, customerGroupDataModel.getDeleted_at());
            values.put(COLUMN_CGC_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_CGC_ID + " = ? ";
            String whereArgs[] = {String.valueOf(customerGroupDataModel.getCgc_id())};
            int updatedCount = db.update(TABLE_NAME_CFG_CUST_GROUP, values, whereClause, whereArgs);
            Log.e(TAG, "insertCustomerGroup: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_CFG_CUST_GROUP, null, values);
                Log.e(TAG, "insertCustomerGroup: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }

    public void deleteDirtyGroups(ArrayList<CustomerGroupDataModel> customerGroupDataModels) {
        Log.e(TAG, "deleteDirtyGroups: ");
        db = getDatabase();
        for (int i = 0; i < customerGroupDataModels.size(); i++) {
            CustomerGroupDataModel customerGroupDataModel = customerGroupDataModels.get(i);
            String whereClause = COLUMN_CGC_CLIENT_ID + " = ? AND " + COLUMN_CGC_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(customerGroupDataModel.getCgc_client_id()), customerGroupDataModel.getCgc_sync_status()};
            int deletedId = db.delete(TABLE_NAME_CFG_CUST_GROUP, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyGroups: " + deletedId);
        }
        db.close();
    }

    public boolean isCustomerNewlyAdded(String groupId) {
        Log.e(TAG, "isCustomerNewlyAdded: ");
        db = getDatabase();
        String selection = COLUMN_CGC_GROUP_ID + " = ? AND " + COLUMN_CGC_SYNC_STATUS + " = ? ";
        String[] selectionArgs = {String.valueOf(groupId), GlobalConstant.INSERTED};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public ArrayList<CustomerGroupDataModel> getAllGroups(int cust_unique_id) {
        Log.e(TAG, "getAllGroups: ");
        ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList = new ArrayList<>();
        db = getDatabase();
        String where = COLUMN_CGC_CUST_ID + " = ? ";
        String[] whereArgs = {String.valueOf(cust_unique_id)};
        Cursor cursor = db.query(TABLE_NAME_CFG_CUST_GROUP, allColumns, where, whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                customerGroupDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return customerGroupDataModelArrayList;
    }
}
