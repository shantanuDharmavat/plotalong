package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.AddressDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 22/6/17.
 */

public class CfgAddressDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_CFG_ADDRESS = "cfg_address";
    public static final String COLUMN_ADDRESS_CLIENT_ID = "cfg_client_addr_id";
    public static final String COLUMN_CFG_ADDRESS_ID = "cfg_addr_id";
    public static final String COLUMN_REFERENCE_ID = "ref_id";
    public static final String COLUMN_ADDRESS_DEVL_ID = "addr_devl_id";
    public static final String COLUMN_ADDRESS_LINE_ONE = "addr_line1";
    public static final String COLUMN_ADDRESS_LINE_TWO = "addr_line2";
    public static final String COLUMN_ADDRESS_LOCALITY = "addr_locality";
    public static final String COLUMN_ADDRESS_CITY = "addr_city";
    public static final String COLUMN_ADDRESS_DISTRICT = "addr_district";
    public static final String COLUMN_ADDRESS_TALUKA = "addr_taluka";
    public static final String COLUMN_ADDRESS_STATE = "addr_state";
    public static final String COLUMN_ADDRESS_PIN_CODE = "addr_pincode";
    public static final String COLUMN_ADDRESS_COUNTRY = "addr_country";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";
    public static final String COLUMN_ADDRESS_SYNC_STATUS = "addr_sync_status";


    public static final String CREATE_TABLE_CFG_ADDRESS = "CREATE TABLE "
            + TABLE_NAME_CFG_ADDRESS + "("
            + COLUMN_ADDRESS_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_CFG_ADDRESS_ID
            + " INTEGER,"
            + COLUMN_REFERENCE_ID
            + " INTEGER,"
            + COLUMN_ADDRESS_DEVL_ID
            + " INTEGER,"
            + COLUMN_ADDRESS_LINE_ONE
            + " TEXT,"
            + COLUMN_ADDRESS_LINE_TWO
            + " TEXT,"
            + COLUMN_ADDRESS_LOCALITY
            + " TEXT,"
            + COLUMN_ADDRESS_CITY
            + " TEXT,"
            + COLUMN_ADDRESS_DISTRICT
            + " TEXT,"
            + COLUMN_ADDRESS_TALUKA
            + " TEXT,"
            + COLUMN_ADDRESS_STATE
            + " TEXT,"
            + COLUMN_ADDRESS_PIN_CODE
            + " TEXT,"
            + COLUMN_ADDRESS_COUNTRY
            + " TEXT,"
            + COLUMN_TYPE
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT,"
            + COLUMN_ADDRESS_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_CFG_ADDRESS = "DROP TABLE IF EXISTS " + TABLE_NAME_CFG_ADDRESS;


    private static final String TAG = CfgAddressDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static String[] allColumns = {
            COLUMN_ADDRESS_CLIENT_ID, COLUMN_CFG_ADDRESS_ID, COLUMN_REFERENCE_ID,
            COLUMN_ADDRESS_DEVL_ID, COLUMN_ADDRESS_LINE_ONE, COLUMN_ADDRESS_LINE_TWO, COLUMN_ADDRESS_LOCALITY,
            COLUMN_ADDRESS_CITY, COLUMN_CREATED_AT, COLUMN_UPDATED_AT, COLUMN_DELETED_AT,
            COLUMN_ADDRESS_DISTRICT, COLUMN_ADDRESS_TALUKA, COLUMN_ADDRESS_STATE, COLUMN_ADDRESS_PIN_CODE,
            COLUMN_ADDRESS_COUNTRY, COLUMN_TYPE, COLUMN_ADDRESS_SYNC_STATUS
    };

    private SQLiteDatabase db;

    public CfgAddressDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }


    public void insertAddressOfServer(ArrayList<AddressDataModel> addressDataModelArrayList) {
        Log.e(TAG, "insertAddressOfServer: ");
        db = getDatabase();
        AddressDataModel addressDataModel;
        for (int i = 0; i < addressDataModelArrayList.size(); i++) {
            addressDataModel = addressDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_CFG_ADDRESS_ID, addressDataModel.getCfg_addr_id());
            values.put(COLUMN_REFERENCE_ID, addressDataModel.getRef_id());
            values.put(COLUMN_ADDRESS_DEVL_ID, addressDataModel.getAddr_devl_id());
            values.put(COLUMN_ADDRESS_LINE_ONE, addressDataModel.getAddr_line1());
            values.put(COLUMN_ADDRESS_LINE_TWO, addressDataModel.getAddr_line2());
            values.put(COLUMN_ADDRESS_LOCALITY, addressDataModel.getAddr_locality());
            values.put(COLUMN_ADDRESS_CITY, addressDataModel.getAddr_city());
            values.put(COLUMN_ADDRESS_DISTRICT, addressDataModel.getAddr_district());
            values.put(COLUMN_ADDRESS_TALUKA, addressDataModel.getAddr_taluka());
            values.put(COLUMN_ADDRESS_STATE, addressDataModel.getAddr_state());
            values.put(COLUMN_ADDRESS_PIN_CODE, addressDataModel.getAddr_pincode());
            values.put(COLUMN_ADDRESS_COUNTRY, addressDataModel.getAddr_country());
            values.put(COLUMN_TYPE, addressDataModel.getType());
            values.put(COLUMN_CREATED_AT, addressDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, addressDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, addressDataModel.getDeleted_at());
            values.put(COLUMN_ADDRESS_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_CFG_ADDRESS_ID + " = ? ";
            String whereArgs[] = {String.valueOf(addressDataModel.getCfg_addr_id())};
            long updatedCount = db.update(TABLE_NAME_CFG_ADDRESS, values, whereClause, whereArgs);
            if (updatedCount <= 0) {
                db.insert(TABLE_NAME_CFG_ADDRESS, null, values);
            }
        }
        db.close();
    }

    public ArrayList<AddressDataModel> getUnsyncAddresses(String lastSyncTime) {
        Log.e(TAG, "getUnsyncAddresses: ");
        db = getDatabase();
        ArrayList<AddressDataModel> addressDataModelArrayList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME_CFG_ADDRESS + " WHERE " + COLUMN_UPDATED_AT + " >= '" + lastSyncTime + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                addressDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return addressDataModelArrayList;
    }

    private AddressDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");
        AddressDataModel addressDataModel = new AddressDataModel();
        addressDataModel.setCfg_addr_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ADDRESS_CLIENT_ID)));
        addressDataModel.setCfg_addr_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CFG_ADDRESS_ID)));
        addressDataModel.setRef_id(cursor.getInt(cursor.getColumnIndex(COLUMN_REFERENCE_ID)));
        addressDataModel.setAddr_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_REFERENCE_ID)));
        addressDataModel.setAddr_line1(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE_ONE)));
        addressDataModel.setAddr_line2(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LINE_TWO)));
        addressDataModel.setAddr_locality(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_LOCALITY)));
        addressDataModel.setAddr_city(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_CITY)));
        addressDataModel.setAddr_district(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_DISTRICT)));
        addressDataModel.setAddr_taluka(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_TALUKA)));
        addressDataModel.setAddr_state(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_STATE)));
        addressDataModel.setAddr_pincode(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_PIN_CODE)));
        addressDataModel.setAddr_country(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_COUNTRY)));
        addressDataModel.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
        addressDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        addressDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        addressDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));
        addressDataModel.setAddr_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_SYNC_STATUS)));
        return addressDataModel;
    }

    public ArrayList<AddressDataModel> getAllDirtyAddress() {
        Log.e(TAG, "getAllDirtyAddress: ");
        db = getDatabase();
        ArrayList<AddressDataModel> addressDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_ADDRESS_SYNC_STATUS + " = ? OR " + COLUMN_ADDRESS_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_CFG_ADDRESS, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                addressDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return addressDataModelArrayList;
    }

    public void deleteDirtyAddresses(ArrayList<AddressDataModel> addressDataModelArrayList) {
        Log.e(TAG, "deleteDirtyAddresses: ");
        db = getDatabase();
        for (int i = 0; i < addressDataModelArrayList.size(); i++) {
            AddressDataModel addressDataModel = addressDataModelArrayList.get(i);
            String whereClause = COLUMN_ADDRESS_CLIENT_ID + " = ? AND " + COLUMN_ADDRESS_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(addressDataModel.getCfg_addr_client_id()), addressDataModel.getAddr_sync_status()};
            int deletedId = db.delete(TABLE_NAME_CFG_ADDRESS, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyAddresses: " + deletedId);
        }
        db.close();
    }

    public AddressDataModel getAddressOfPhase(int phas_id) {
        Log.e(TAG, "getAddressOfPhase: ");
        db = getDatabase();
        AddressDataModel addressDataModel = null;
        String where = COLUMN_REFERENCE_ID + " = ? AND " + COLUMN_TYPE + " = ? ";
        String[] whereArgs = {String.valueOf(phas_id), GlobalConstant.ADDRESS_TYPE_PHAS};
        Cursor cursor = db.query(TABLE_NAME_CFG_ADDRESS, allColumns, where, whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            addressDataModel = cursorToModel(cursor);
            cursor.close();
        }
        return addressDataModel;
    }
}