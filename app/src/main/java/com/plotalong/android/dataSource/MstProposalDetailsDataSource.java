package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.ProposalDetailsDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 11/9/17.
 */

public class MstProposalDetailsDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_PROPOSAL_DETAILS = "proposal_details";
    public static final String COLUMN_PRDT_CLIENT_ID = "prdt_client_id";
    public static final String COLUMN_PRDT_ID = "prdt_id";
    public static final String COLUMN_PRDT_PROPID = "prdt_propid";
    public static final String COLUMN_PRDT_CHARGE_ID = "prdt_charge_id";
    public static final String COLUMN_PRDT_BASE_PRICE = "prdt_base_price";
    public static final String COLUMN_PRDT_DISC_AMOUNT = "prdt_disc_amount";
    public static final String COLUMN_PRDT_SALE_PRICE = "prdt_sale_price";
    public static final String COLUMN_PRDT_RATE = "prdt_rate";
    public static final String COLUMN_PRDT_DISC_REASON = "prdt_disc_reason";
    public static final String COLUMN_PRDT_NOTES = "prdt_notes";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";
    public static final String COLUMN_PRDT_PROD_ID = "prdt_prod_id";
    public static final String COLUMN_PRDT_PROD_TYPE = "prdt_prod_type";
    public static final String COLUMN_PRDT_PARENT_ID = "prdt_parent_id";
    public static final String COLUMN_PRDT_DISC_ID = "prdt_disc_id";
    public static final String COLUMN_PRDT_SYNC_STATUS = "prdt_sync_status";


    public static final String CREATE_TABLE_PROPOSAL_DETAILS = "CREATE TABLE "
            + TABLE_NAME_PROPOSAL_DETAILS + "("
            + COLUMN_PRDT_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PRDT_ID
            + " INTEGER,"
            + COLUMN_PRDT_PROPID
            + " INTEGER,"
            + COLUMN_PRDT_CHARGE_ID
            + " TEXT,"
            + COLUMN_PRDT_BASE_PRICE
            + " TEXT,"
            + COLUMN_PRDT_DISC_AMOUNT
            + " TEXT,"
            + COLUMN_PRDT_SALE_PRICE
            + " TEXT,"
            + COLUMN_PRDT_RATE
            + " TEXT,"
            + COLUMN_PRDT_DISC_REASON
            + " TEXT,"
            + COLUMN_PRDT_NOTES
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_PRDT_PROD_ID
            + " INTEGER,"
            + COLUMN_PRDT_PROD_TYPE
            + " TEXT,"
            + COLUMN_PRDT_PARENT_ID
            + " INTEGER,"
            + COLUMN_PRDT_DISC_ID
            + " INTEGER,"
            + COLUMN_DELETED_AT
            + " TEXT,"
            + COLUMN_PRDT_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_PROPOSAL_DETAILS = "DROP TABLE IF EXISTS " + TABLE_NAME_PROPOSAL_DETAILS;

    public static final String allColumns[] = {COLUMN_PRDT_CLIENT_ID,
            COLUMN_PRDT_ID, COLUMN_PRDT_PROPID, COLUMN_PRDT_CHARGE_ID, COLUMN_PRDT_BASE_PRICE,
            COLUMN_PRDT_RATE, COLUMN_PRDT_DISC_AMOUNT, COLUMN_PRDT_SALE_PRICE,
            COLUMN_PRDT_DISC_REASON, COLUMN_PRDT_NOTES, COLUMN_CREATED_AT, COLUMN_UPDATED_AT,
            COLUMN_PRDT_PROD_ID, COLUMN_PRDT_PROD_TYPE, COLUMN_PRDT_PARENT_ID, COLUMN_PRDT_DISC_ID,
            COLUMN_DELETED_AT, COLUMN_PRDT_SYNC_STATUS};

    private static final String TAG = MstProposalDetailsDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstProposalDetailsDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }


    public int getLastProposalDetailId() {
        Log.e(TAG, "getLastProposalDetailId: ");
        db = getDatabase();
        int lastId = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_PROPOSAL_DETAILS + " ORDER BY " + COLUMN_PRDT_ID + " DESC LIMIT 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            lastId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_ID));
        }
        cursor.close();
        db.close();
        return lastId;
    }

    public String insertProducts(ProposalDetailsDataModel proposalDetailsDataModel) {
        Log.e(TAG, "insertProducts: ");
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRDT_ID, proposalDetailsDataModel.getPrdt_id());
        values.put(COLUMN_PRDT_PROPID, proposalDetailsDataModel.getPrdt_propid());
        values.put(COLUMN_PRDT_CHARGE_ID, proposalDetailsDataModel.getPrdt_charge_id());
        values.put(COLUMN_PRDT_BASE_PRICE, proposalDetailsDataModel.getPrdt_base_price());
        values.put(COLUMN_PRDT_DISC_AMOUNT, proposalDetailsDataModel.getPrdt_disc_amount());
        values.put(COLUMN_PRDT_SALE_PRICE, proposalDetailsDataModel.getPrdt_sale_price());
        values.put(COLUMN_PRDT_DISC_REASON, proposalDetailsDataModel.getPrdt_disc_reason());
        values.put(COLUMN_PRDT_NOTES, proposalDetailsDataModel.getPrdt_notes());
        values.put(COLUMN_CREATED_AT, proposalDetailsDataModel.getCreated_at());
        values.put(COLUMN_UPDATED_AT, proposalDetailsDataModel.getUpdated_at());
        values.put(COLUMN_DELETED_AT, proposalDetailsDataModel.getDeleted_at());
        values.put(COLUMN_PRDT_PROD_ID, proposalDetailsDataModel.getPrdt_prod_id());
        values.put(COLUMN_PRDT_PROD_TYPE, proposalDetailsDataModel.getPrdt_prod_type());
        values.put(COLUMN_PRDT_PARENT_ID, proposalDetailsDataModel.getPrdt_parent_id());
        values.put(COLUMN_PRDT_DISC_ID, proposalDetailsDataModel.getPrdt_disc_id());
        values.put(COLUMN_PRDT_RATE, proposalDetailsDataModel.getPrdt_rate());
        values.put(COLUMN_PRDT_SYNC_STATUS, GlobalConstant.INSERTED);

//        String whereClause = COLUMN_PRDT_CLIENT_ID + " = ? ";
//        String whereArgs[] = {String.valueOf(proposalDetailsDataModel.getPrdt_client_id())};
//        long updatedCount = db.update(TABLE_NAME_PROPOSAL_DETAILS, values, whereClause, whereArgs);
//        Log.e(TAG, "insertCustomerGroup: updatedCount = " + updatedCount);
//        if (updatedCount <= 0) {
        long insertedCount = db.insert(TABLE_NAME_PROPOSAL_DETAILS, null, values);
//            Log.e(TAG, "insertCustomerGroup: insertedCount = " + insertedCount);
//        }
        db.close();
        return String.valueOf(insertedCount);
    }

    public ArrayList<ProposalDetailsDataModel> getAllProposalDetails() {
        Log.e(TAG, "getAllProposalDetails: ");
        db = getDatabase();
        ArrayList<ProposalDetailsDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_DETAILS, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalHeaderDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return proposalHeaderDataModelArrayList;
    }

    private ProposalDetailsDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");
        ProposalDetailsDataModel proposalDetailsDataModel = new ProposalDetailsDataModel();
        proposalDetailsDataModel.setPrdt_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_CLIENT_ID)));
        proposalDetailsDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));
        proposalDetailsDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        proposalDetailsDataModel.setPrdt_base_price(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_BASE_PRICE)));
        proposalDetailsDataModel.setPrdt_charge_id(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_CHARGE_ID)));
        proposalDetailsDataModel.setPrdt_disc_amount(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_DISC_AMOUNT)));
        proposalDetailsDataModel.setPrdt_disc_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_DISC_ID)));
        proposalDetailsDataModel.setPrdt_disc_reason(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_DISC_REASON)));
        proposalDetailsDataModel.setPrdt_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_ID)));
        proposalDetailsDataModel.setPrdt_notes(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_NOTES)));
        proposalDetailsDataModel.setPrdt_parent_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_PARENT_ID)));
        proposalDetailsDataModel.setPrdt_prod_type(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_PROD_TYPE)));
        proposalDetailsDataModel.setPrdt_prod_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_PROD_ID)));
        proposalDetailsDataModel.setPrdt_propid(cursor.getInt(cursor.getColumnIndex(COLUMN_PRDT_PROPID)));
        proposalDetailsDataModel.setPrdt_sale_price(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_SALE_PRICE)));
        proposalDetailsDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        proposalDetailsDataModel.setPrdt_rate(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_RATE)));
        proposalDetailsDataModel.setPrdt_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_PRDT_SYNC_STATUS)));
        return proposalDetailsDataModel;
    }

    public ArrayList<ProposalDetailsDataModel> getAllUnsyncProposalDetails(String lastSyncTime) {
        Log.e(TAG, "getAllUnsyncProposalDetails: ");
        db = getDatabase();
        ArrayList<ProposalDetailsDataModel> proposalDetailsDataModelArrayList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME_PROPOSAL_DETAILS + " WHERE " + COLUMN_UPDATED_AT + " >= '" + lastSyncTime + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalDetailsDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return proposalDetailsDataModelArrayList;
    }

    public ArrayList<ProposalDetailsDataModel> getAllProposalDetails(int prop_id) {
        Log.e(TAG, "getAllProposalDetails: ");
        ArrayList<ProposalDetailsDataModel> proposalDetailsDataModelArrayList = new ArrayList<>();
        db = getDatabase();
        String selection = COLUMN_PRDT_PROPID + " = ? ";
        String selectionArgs[] = {String.valueOf(prop_id)};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_DETAILS, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ProposalDetailsDataModel proposalDetailsDataModel = cursorToModel(cursor);
                proposalDetailsDataModelArrayList.add(proposalDetailsDataModel);
            }
        }
        return proposalDetailsDataModelArrayList;
    }

    public void insertProposalDetailsOfServer(ArrayList<ProposalDetailsDataModel> proposalDetails) {
        Log.e(TAG, "insertProposalDetailsOfServer: ");
        db = getDatabase();
        ProposalDetailsDataModel proposalDetailsDataModel;
        for (int i = 0; i < proposalDetails.size(); i++) {
            proposalDetailsDataModel = proposalDetails.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRDT_ID, proposalDetailsDataModel.getPrdt_id());
            values.put(COLUMN_PRDT_PROPID, proposalDetailsDataModel.getPrdt_propid());
            values.put(COLUMN_PRDT_CHARGE_ID, proposalDetailsDataModel.getPrdt_charge_id());
            values.put(COLUMN_PRDT_BASE_PRICE, proposalDetailsDataModel.getPrdt_base_price());
            values.put(COLUMN_PRDT_DISC_AMOUNT, proposalDetailsDataModel.getPrdt_disc_amount());
            values.put(COLUMN_PRDT_SALE_PRICE, proposalDetailsDataModel.getPrdt_sale_price());
            values.put(COLUMN_PRDT_DISC_REASON, proposalDetailsDataModel.getPrdt_disc_reason());
            values.put(COLUMN_PRDT_NOTES, proposalDetailsDataModel.getPrdt_notes());
            values.put(COLUMN_CREATED_AT, proposalDetailsDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, proposalDetailsDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, proposalDetailsDataModel.getDeleted_at());
            values.put(COLUMN_PRDT_PROD_ID, proposalDetailsDataModel.getPrdt_prod_id());
            values.put(COLUMN_PRDT_PROD_TYPE, proposalDetailsDataModel.getPrdt_prod_type());
            values.put(COLUMN_PRDT_PARENT_ID, proposalDetailsDataModel.getPrdt_parent_id());
            values.put(COLUMN_PRDT_DISC_ID, proposalDetailsDataModel.getPrdt_disc_id());
            values.put(COLUMN_PRDT_RATE, proposalDetailsDataModel.getPrdt_rate());
            values.put(COLUMN_PRDT_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_PRDT_ID + " = ? ";
            String whereArgs[] = {String.valueOf(proposalDetailsDataModel.getPrdt_id())};
            long updatedCount = db.update(TABLE_NAME_PROPOSAL_DETAILS, values, whereClause, whereArgs);
            Log.e(TAG, "insertProposalDetailsOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_PROPOSAL_DETAILS, null, values);
                Log.e(TAG, "insertProposalDetailsOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }

    public ArrayList<ProposalDetailsDataModel> getAllDirtyProposalDetails() {
        Log.e(TAG, "getAllDirtyProposalDetails: ");
        db = getDatabase();
        ArrayList<ProposalDetailsDataModel> proposalDetailsDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_PRDT_SYNC_STATUS + " = ? OR " + COLUMN_PRDT_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_DETAILS, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalDetailsDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return proposalDetailsDataModelArrayList;
    }

    public void deleteDirtyProposalDetails(ArrayList<ProposalDetailsDataModel> proposalDetailsDataModelArrayList) {
        Log.e(TAG, "deleteDirtyProposalDetails: ");
        db = getDatabase();
        for (int i = 0; i < proposalDetailsDataModelArrayList.size(); i++) {
            ProposalDetailsDataModel proposalDetailsDataModel = proposalDetailsDataModelArrayList.get(i);
            String whereClause = COLUMN_PRDT_CLIENT_ID + " = ? AND " + COLUMN_PRDT_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(proposalDetailsDataModel.getPrdt_client_id()), proposalDetailsDataModel.getPrdt_sync_status()};
            int deletedId = db.delete(TABLE_NAME_PROPOSAL_DETAILS, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyProposalDetails: " + deletedId);
        }
        db.close();
    }

    public void updatePrdtId(String prdtInsertedId) {
        Log.e(TAG, "updatePrdtId: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRDT_ID, prdtInsertedId);
        String where = COLUMN_PRDT_CLIENT_ID + " = ? ";
        String[] whereArgs = {prdtInsertedId};
        int updatedId = db.update(TABLE_NAME_PROPOSAL_DETAILS, contentValues, where, whereArgs);
        Log.e(TAG, "updatePrdtId: " + updatedId);
    }

    public boolean isPlotInProposal(int plot_id, String tempProposalId) {
        Log.e(TAG, "isPlotAvailable: ");
        db = getDatabase();
        String where = COLUMN_PRDT_PROPID + " = ? AND " + COLUMN_PRDT_ID + " = ? ";
        String[] whereArgs = {String.valueOf(plot_id), tempProposalId};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_DETAILS, allColumns, where, whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            db.close();
            cursor.close();
            return true;
        }
        db.close();
        cursor.close();
        return false;
    }
}