package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 11/9/17.
 */

public class MstProposalHeaderDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_PROPOSAL_HEADER = "proposal_header";
    public static final String COLUMN_PROP_CLIENT_ID = "prop_client_id";
    public static final String COLUMN_PROP_ID = "prop_id";
    public static final String COLUMN_PROP_SPID = "prop_spid";
    public static final String COLUMN_PROP_CONTACT_ID = "prop_contact_id";
    public static final String COLUMN_PROP_DATE = "prop_date";
    public static final String COLUMN_PROP_STAGE = "prop_stage";
    public static final String COLUMN_PROP_STATUS = "prop_status";
    public static final String COLUMN_PROP_DEVL_ID = "prop_devl_id";
    public static final String COLUMN_PROP_PROJ_ID = "prop_proj_id";
    public static final String COLUMN_PROP_PHAS_ID = "prop_phas_id";
    public static final String COLUMN_PROP_PROD_TOTAL = "prop_prod_total";
    public static final String COLUMN_PROP_CHARGE_TOTAL = "prop_charge_total";
    public static final String COLUMN_PROP_DISC_TOTAL = "prop_disc_total";
    public static final String COLUMN_PROP_NET = "prop_net";
    public static final String COLUMN_PROP_VALIDITY = "prop_validity";
    public static final String COLUMN_PROP_NOTES = "prop_notes";
    public static final String COLUMN_PROP_TEMPLATE = "prop_template";
    public static final String COLUMN_PROP_ATTACHMENTS = "prop_attachments";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";
    public static final String COLUMN_PROP_INST_COUNT = "prop_inst_count";
    public static final String COLUMN_PROP_HEADER_SYNC_STATUS = "prop_header_sync_status";


    public static final String CREATE_TABLE_PROPOSAL_HEADER = "CREATE TABLE "
            + TABLE_NAME_PROPOSAL_HEADER + "("
            + COLUMN_PROP_CLIENT_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_PROP_ID
            + " INTEGER,"
            + COLUMN_PROP_SPID
            + " INTEGER,"
            + COLUMN_PROP_CONTACT_ID
            + " TEXT,"
            + COLUMN_PROP_DATE
            + " TEXT,"
            + COLUMN_PROP_STAGE
            + " TEXT,"
            + COLUMN_PROP_STATUS
            + " TEXT,"
            + COLUMN_PROP_DEVL_ID
            + " TEXT,"
            + COLUMN_PROP_PROJ_ID
            + " TEXT,"
            + COLUMN_PROP_PHAS_ID
            + " TEXT,"
            + COLUMN_PROP_PROD_TOTAL
            + " TEXT,"
            + COLUMN_PROP_CHARGE_TOTAL
            + " TEXT,"
            + COLUMN_PROP_DISC_TOTAL
            + " TEXT,"
            + COLUMN_PROP_NET
            + " TEXT,"
            + COLUMN_PROP_VALIDITY
            + " INTEGER,"
            + COLUMN_PROP_NOTES
            + " INTEGER,"
            + COLUMN_PROP_TEMPLATE
            + " TEXT,"
            + COLUMN_PROP_ATTACHMENTS
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT,"
            + COLUMN_PROP_INST_COUNT
            + " TEXT,"
            + COLUMN_PROP_HEADER_SYNC_STATUS
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_PROPOSAL_HEADER = "DROP TABLE IF EXISTS " + TABLE_NAME_PROPOSAL_HEADER;

    public static final String allColumns[] = {COLUMN_PROP_CLIENT_ID, COLUMN_PROP_ID, COLUMN_PROP_SPID,
            COLUMN_PROP_CONTACT_ID, COLUMN_PROP_DATE, COLUMN_PROP_STAGE, COLUMN_PROP_STATUS,
            COLUMN_PROP_DEVL_ID, COLUMN_PROP_PROJ_ID, COLUMN_PROP_PHAS_ID, COLUMN_PROP_PROD_TOTAL,
            COLUMN_PROP_CHARGE_TOTAL, COLUMN_PROP_DISC_TOTAL, COLUMN_PROP_NET, COLUMN_PROP_VALIDITY,
            COLUMN_PROP_NOTES, COLUMN_PROP_TEMPLATE, COLUMN_PROP_ATTACHMENTS, COLUMN_CREATED_AT,
            COLUMN_DELETED_AT, COLUMN_PROP_INST_COUNT, COLUMN_PROP_HEADER_SYNC_STATUS};
    private static final String TAG = MstProposalHeaderDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private SQLiteDatabase db;

    public MstProposalHeaderDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    public long insertProposal(ProposalHeaderDataModel proposalHeaderDataModel) {
        Log.e(TAG, "insertProposal: " + proposalHeaderDataModel);
        db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROP_ID, proposalHeaderDataModel.getProp_id());
        values.put(COLUMN_PROP_SPID, proposalHeaderDataModel.getProp_spid());
        values.put(COLUMN_PROP_CONTACT_ID, proposalHeaderDataModel.getProp_contact_id());
        values.put(COLUMN_PROP_DATE, proposalHeaderDataModel.getProp_date());
        values.put(COLUMN_PROP_STAGE, proposalHeaderDataModel.getProp_stage());
        values.put(COLUMN_PROP_STATUS, proposalHeaderDataModel.getProp_status());
        values.put(COLUMN_PROP_DEVL_ID, proposalHeaderDataModel.getProp_devl_id());
        values.put(COLUMN_PROP_PROJ_ID, proposalHeaderDataModel.getProp_proj_id());
        values.put(COLUMN_PROP_PHAS_ID, proposalHeaderDataModel.getProp_phas_id());
        values.put(COLUMN_PROP_PROD_TOTAL, proposalHeaderDataModel.getProp_prod_total());
        values.put(COLUMN_PROP_CHARGE_TOTAL, proposalHeaderDataModel.getProp_charge_total());
        values.put(COLUMN_PROP_DISC_TOTAL, proposalHeaderDataModel.getProp_disc_total());
        values.put(COLUMN_PROP_NET, proposalHeaderDataModel.getProp_net());
        values.put(COLUMN_PROP_VALIDITY, proposalHeaderDataModel.getProp_validity());
        values.put(COLUMN_PROP_NOTES, proposalHeaderDataModel.getProp_notes());
        values.put(COLUMN_PROP_TEMPLATE, proposalHeaderDataModel.getProp_template());
        values.put(COLUMN_PROP_ATTACHMENTS, proposalHeaderDataModel.getProp_attachments());
        values.put(COLUMN_CREATED_AT, proposalHeaderDataModel.getCreated_at());
        values.put(COLUMN_DELETED_AT, proposalHeaderDataModel.getDeleted_at());
        values.put(COLUMN_PROP_INST_COUNT, proposalHeaderDataModel.getProp_inst_count());
        values.put(COLUMN_PROP_HEADER_SYNC_STATUS, GlobalConstant.INSERTED);
        long insertedCount = db.insert(TABLE_NAME_PROPOSAL_HEADER, null, values);
        db.close();
        return insertedCount;
    }

    public ProposalHeaderDataModel getProposalDataModel(String cgc_group_id, int phaseId) {
        Log.e(TAG, "getProposalDataModel: ");
        ProposalHeaderDataModel proposalHeaderDataModel = null;
        db = getDatabase();
        String selection = COLUMN_PROP_CONTACT_ID + " = ? AND " + COLUMN_PROP_PHAS_ID + " = ?";
        String selectionArgs[] = {cgc_group_id, String.valueOf(phaseId)};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalHeaderDataModel = cursorToModel(cursor);
            }
        }
        return proposalHeaderDataModel;
    }

    private ProposalHeaderDataModel cursorToModel(Cursor cursor) {
        Log.e(TAG, "cursorToModel: ");
        ProposalHeaderDataModel proposalHeaderDataModel = new ProposalHeaderDataModel();
        proposalHeaderDataModel.setProp_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_CLIENT_ID)));
        proposalHeaderDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));
        proposalHeaderDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        proposalHeaderDataModel.setProp_attachments(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_ATTACHMENTS)));
        proposalHeaderDataModel.setProp_charge_total(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_CHARGE_TOTAL)));
        proposalHeaderDataModel.setProp_contact_id(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_CONTACT_ID)));
        proposalHeaderDataModel.setProp_date(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_DATE)));
        proposalHeaderDataModel.setProp_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_DEVL_ID)));
        proposalHeaderDataModel.setProp_disc_total(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_DISC_TOTAL)));
        proposalHeaderDataModel.setProp_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_ID)));
        proposalHeaderDataModel.setProp_inst_count(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_INST_COUNT)));
        proposalHeaderDataModel.setProp_net(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_NET)));
        proposalHeaderDataModel.setProp_notes(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_NOTES)));
        proposalHeaderDataModel.setProp_phas_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_PHAS_ID)));
        proposalHeaderDataModel.setProp_prod_total(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_PROD_TOTAL)));
        proposalHeaderDataModel.setProp_proj_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_PROJ_ID)));
        proposalHeaderDataModel.setProp_validity(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_VALIDITY)));
        proposalHeaderDataModel.setProp_template(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_TEMPLATE)));
        proposalHeaderDataModel.setProp_status(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_STATUS)));
        proposalHeaderDataModel.setProp_stage(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_STAGE)));
        proposalHeaderDataModel.setProp_spid(cursor.getInt(cursor.getColumnIndex(COLUMN_PROP_SPID)));
        proposalHeaderDataModel.setProp_header_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_PROP_HEADER_SYNC_STATUS)));
        return proposalHeaderDataModel;
    }

    public ProposalHeaderDataModel getProposalDataModel(String customerGroupId) {
        Log.e(TAG, "getProposalDataModel: ");
        ProposalHeaderDataModel proposalHeaderDataModel = null;
        db = getDatabase();
        String selection = COLUMN_PROP_CONTACT_ID + " = ? ";
        String selectionArgs[] = {customerGroupId};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalHeaderDataModel = cursorToModel(cursor);
            }
        }
        return proposalHeaderDataModel;
    }

    public void insertProposalHeadersOfServer(ArrayList<ProposalHeaderDataModel> proposalHeaders) {
        Log.e(TAG, "insertProposalHeadersOfServer: ");
        db = getDatabase();
        ProposalHeaderDataModel proposalHeaderDataModel;
        for (int i = 0; i < proposalHeaders.size(); i++) {
            proposalHeaderDataModel = proposalHeaders.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_PROP_ID, proposalHeaderDataModel.getProp_id());
            values.put(COLUMN_PROP_SPID, proposalHeaderDataModel.getProp_spid());
            values.put(COLUMN_PROP_CONTACT_ID, proposalHeaderDataModel.getProp_contact_id());
            values.put(COLUMN_PROP_DATE, proposalHeaderDataModel.getProp_date());
            values.put(COLUMN_PROP_STAGE, proposalHeaderDataModel.getProp_stage());
            values.put(COLUMN_PROP_STATUS, proposalHeaderDataModel.getProp_status());
            values.put(COLUMN_PROP_DEVL_ID, proposalHeaderDataModel.getProp_devl_id());
            values.put(COLUMN_PROP_PROJ_ID, proposalHeaderDataModel.getProp_proj_id());
            values.put(COLUMN_PROP_PHAS_ID, proposalHeaderDataModel.getProp_phas_id());
            values.put(COLUMN_PROP_PROD_TOTAL, proposalHeaderDataModel.getProp_prod_total());
            values.put(COLUMN_PROP_CHARGE_TOTAL, proposalHeaderDataModel.getProp_charge_total());
            values.put(COLUMN_PROP_DISC_TOTAL, proposalHeaderDataModel.getProp_disc_total());
            values.put(COLUMN_PROP_NET, proposalHeaderDataModel.getProp_net());
            values.put(COLUMN_PROP_VALIDITY, proposalHeaderDataModel.getProp_validity());
            values.put(COLUMN_PROP_NOTES, proposalHeaderDataModel.getProp_notes());
            values.put(COLUMN_PROP_TEMPLATE, proposalHeaderDataModel.getProp_template());
            values.put(COLUMN_PROP_ATTACHMENTS, proposalHeaderDataModel.getProp_attachments());
            values.put(COLUMN_CREATED_AT, proposalHeaderDataModel.getCreated_at());
            values.put(COLUMN_DELETED_AT, proposalHeaderDataModel.getDeleted_at());
            values.put(COLUMN_PROP_INST_COUNT, proposalHeaderDataModel.getProp_inst_count());
            values.put(COLUMN_PROP_HEADER_SYNC_STATUS, GlobalConstant.STRING_OK);

            String whereClause = COLUMN_PROP_ID + " = ? ";
            String whereArgs[] = {String.valueOf(proposalHeaderDataModel.getProp_id())};
            long updatedCount = db.update(TABLE_NAME_PROPOSAL_HEADER, values, whereClause, whereArgs);
            Log.e(TAG, "insertProposalHeadersOfServer: updatedCount = " + updatedCount);
            if (updatedCount <= 0) {
                long insertedCount = db.insert(TABLE_NAME_PROPOSAL_HEADER, null, values);
                Log.e(TAG, "insertProposalHeadersOfServer: insertedCount = " + insertedCount);
            }
        }
        db.close();
    }

    public ArrayList<ProposalHeaderDataModel> getAllDirtyProposalHeaders() {
        Log.e(TAG, "getAllDirtyProposalHeaders: ");
        db = getDatabase();
        ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_PROP_HEADER_SYNC_STATUS + " = ? OR " + COLUMN_PROP_HEADER_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                proposalHeaderDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return proposalHeaderDataModelArrayList;
    }

    public void deleteDirtyProposalHeaders(ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList) {
        Log.e(TAG, "deleteDirtyProposalHeaders: ");
        db = getDatabase();
        for (int i = 0; i < proposalHeaderDataModelArrayList.size(); i++) {
            ProposalHeaderDataModel proposalHeaderDataModel = proposalHeaderDataModelArrayList.get(i);
            String whereClause = COLUMN_PROP_CLIENT_ID + " = ? AND " + COLUMN_PROP_HEADER_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(proposalHeaderDataModel.getProp_client_id()), proposalHeaderDataModel.getProp_header_sync_status()};
            int deletedId = db.delete(TABLE_NAME_PROPOSAL_HEADER, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyProposalHeaders: " + deletedId);
        }
        db.close();
    }

    public ProposalHeaderDataModel getProposalDataModelByProposalId(String proposalId) {
        Log.e(TAG, "getProposalDataModelByProposalId: ");
        db = getDatabase();
        ProposalHeaderDataModel proposalHeaderDataModel = null;
        String selection = COLUMN_PROP_ID + " = ? ";
        String[] selectionArgs = {proposalId};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            proposalHeaderDataModel = cursorToModel(cursor);
        }
        cursor.close();
        db.close();
        return proposalHeaderDataModel;
    }

    public void updateProposalHeaderAsUpdated(String tempProposalId) {
        Log.e(TAG, "updateProposalHeaderAsUpdated: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROP_HEADER_SYNC_STATUS, GlobalConstant.UPDATED);
        String where = COLUMN_PROP_ID + " = ? ";
        String[] whereArgs = {tempProposalId};
        int updatedId = db.update(TABLE_NAME_PROPOSAL_HEADER, contentValues, where, whereArgs);
        Log.e(TAG, "updateProposalHeaderAsUpdated: " + updatedId);
        db.close();
    }

    public void updateProposalHeaderPropId(String insertedProposalId) {
        Log.e(TAG, "updateProposalHeaderPropId: ");
        db = getDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROP_ID, insertedProposalId);
        String where = COLUMN_PROP_CLIENT_ID + " = ? ";
        String[] whereArgs = {insertedProposalId};
        int updatedId = db.update(TABLE_NAME_PROPOSAL_HEADER, contentValues, where, whereArgs);
        Log.e(TAG, "updateProposalHeaderPropId: " + updatedId);
        db.close();
    }

    public String getProposalNotes(String customerTempGroupId) {
        Log.e(TAG, "getProposalNotes: ");
        db = getDatabase();
        String notesJsonString = null;
        String where = COLUMN_PROP_CONTACT_ID + " = ?";
        String[] whereArgs = {customerTempGroupId};
        Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, where, whereArgs, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            notesJsonString = cursor.getString(cursor.getColumnIndex(COLUMN_PROP_NOTES));
        }
        cursor.close();
        return notesJsonString;
    }

    public ArrayList<ProposalHeaderDataModel> getAllProposalHeaders(ArrayList<CustomerGroupDataModel> customerGroupDataModels) {
        Log.e(TAG, "getAllProposalHeaders: ");
        ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();
        db = getDatabase();
        for (int i = 0; i < customerGroupDataModels.size(); i++) {
            String where = COLUMN_PROP_CONTACT_ID + " = ? ";
            String[] whereArgs = {customerGroupDataModels.get(i).getCgc_group_id()};
            Cursor cursor = db.query(TABLE_NAME_PROPOSAL_HEADER, allColumns, where, whereArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                proposalHeaderDataModelArrayList.add(cursorToModel(cursor));
                cursor.close();
            }
        }
        db.close();
        return proposalHeaderDataModelArrayList;
    }
}