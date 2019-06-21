package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.feedback.AnswerDataModel;
import com.plotalong.android.model.feedback.QuestionsDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 30/10/17.
 */

public class TrnFeedbackDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_FEEDBACK = "feedback";
    public static final String COLUMN_CLIENT_ANS_ID = "client_ans_id";
    public static final String COLUMN_ANS_ID = "ans_id";
    public static final String COLUMN_ANS_DEVL_ID = "ans_devl_id";
    public static final String COLUMN_ANS_PROJ_ID = "ans_proj_id";
    public static final String COLUMN_ANS_PHASE_ID = "ans_phase_id";
    public static final String COLUMN_ANS_QUE_TEXT = "ans_que_text";
    public static final String COLUMN_ANS_TEXT = "ans_text";
    public static final String COLUMN_ANS_TYPE = "ans_type";
    public static final String COLUMN_ANS_RANGE = "ans_range";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";
    public static final String COLUMN_ANS_SPID = "ans_spid";
    public static final String COLUMN_ANS_CUST_ID = "ans_cust_id";
    public static final String COLUMN_ANS_GROUP_ID = "ans_group_id";
    public static final String COLUMN_ANS_FILE_LOCATION = "ans_file_location";
    public static final String COLUMN_ANS_SYNC_STATUS = "ans_sync_status";


    public static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE "
            + TABLE_NAME_FEEDBACK + "("
            + COLUMN_CLIENT_ANS_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_ANS_ID
            + " INTEGER,"
            + COLUMN_ANS_DEVL_ID
            + " INTEGER,"
            + COLUMN_ANS_PROJ_ID
            + " INTEGER,"
            + COLUMN_ANS_PHASE_ID
            + " INTEGER,"
            + COLUMN_ANS_TEXT
            + " TEXT,"
            + COLUMN_ANS_TYPE
            + " TEXT,"
            + COLUMN_ANS_SPID
            + " INTEGER,"
            + COLUMN_ANS_CUST_ID
            + " INTEGER,"
            + COLUMN_ANS_GROUP_ID
            + " TEXT,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_ANS_RANGE
            + " INTEGER,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_ANS_QUE_TEXT
            + " TEXT,"
            + COLUMN_ANS_FILE_LOCATION
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT,"
            + COLUMN_ANS_SYNC_STATUS
            + " TEXT"
            + ")";

    public static final String DELETE_TABLE_FEEDBACK = "DROP TABLE IF EXISTS " + TABLE_NAME_FEEDBACK;
    private static final String TAG = TrnFeedbackDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static String[] allColumns = {
            COLUMN_CLIENT_ANS_ID,
            COLUMN_ANS_ID,
            COLUMN_ANS_DEVL_ID,
            COLUMN_ANS_PROJ_ID,
            COLUMN_ANS_PHASE_ID,
            COLUMN_ANS_TEXT,
            COLUMN_ANS_TYPE,
            COLUMN_ANS_RANGE,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT,
            COLUMN_DELETED_AT,
            COLUMN_ANS_SPID,
            COLUMN_ANS_QUE_TEXT,
            COLUMN_ANS_SPID,
            COLUMN_ANS_CUST_ID,
            COLUMN_ANS_GROUP_ID,
            COLUMN_ANS_FILE_LOCATION,
            COLUMN_ANS_SYNC_STATUS
    };

    private SQLiteDatabase db;

    public TrnFeedbackDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }


    public void insertFeedbackDetails(ArrayList<QuestionsDataModel> questionsDataModelArrayList, AnswerDataModel answerDataModel) {
        db = getDatabase();
        QuestionsDataModel questionsDataModel;
        for (int i = 0; i < questionsDataModelArrayList.size(); i++) {
            questionsDataModel = questionsDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_ANS_ID, questionsDataModel.getQue_id());
            values.put(COLUMN_ANS_DEVL_ID, questionsDataModel.getQue_devl_id());
            values.put(COLUMN_ANS_PROJ_ID, questionsDataModel.getQue_proj_id());
            values.put(COLUMN_ANS_PHASE_ID, questionsDataModel.getQue_phase_id());
            values.put(COLUMN_ANS_TYPE, questionsDataModel.getQue_ans_type());
            values.put(COLUMN_ANS_RANGE, questionsDataModel.getQue_ans_range());
            values.put(COLUMN_ANS_QUE_TEXT, questionsDataModel.getQue_text());
            values.put(COLUMN_ANS_TEXT, questionsDataModel.getAns_text());
            values.put(COLUMN_CREATED_AT, questionsDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, questionsDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, questionsDataModel.getDeleted_at());
            values.put(COLUMN_ANS_SPID, answerDataModel.getAns_spid());
            values.put(COLUMN_ANS_CUST_ID, answerDataModel.getAns_cust_id());
            values.put(COLUMN_ANS_GROUP_ID, answerDataModel.getAns_group_id());
            values.put(COLUMN_ANS_FILE_LOCATION, answerDataModel.getAns_file_location());
            values.put(COLUMN_ANS_SYNC_STATUS, GlobalConstant.INSERTED);

            db.insert(TABLE_NAME_FEEDBACK, null, values);
        }
        db.close();
    }

    private AnswerDataModel cursorToModel(Cursor cursor) {
        AnswerDataModel answerDataModel = new AnswerDataModel();
        answerDataModel.setAns_client_id(cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_ANS_ID)));

        answerDataModel.setAns_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_ID)));
        answerDataModel.setAns_cust_id(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_CUST_ID)));
        answerDataModel.setAns_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_DEVL_ID)));
        answerDataModel.setAns_group_id(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_GROUP_ID)));
        answerDataModel.setAns_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_PHASE_ID)));
        answerDataModel.setAns_proj_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_PROJ_ID)));
        answerDataModel.setAns_range(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_RANGE)));
        answerDataModel.setAns_spid(cursor.getInt(cursor.getColumnIndex(COLUMN_ANS_SPID)));
        answerDataModel.setAns_text(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_TEXT)));
        answerDataModel.setAns_type(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_TYPE)));
        answerDataModel.setAns_que_text(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_QUE_TEXT)));
        answerDataModel.setAns_file_location(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_FILE_LOCATION)));
        answerDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        answerDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        answerDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));
        answerDataModel.setAns_sync_status(cursor.getString(cursor.getColumnIndex(COLUMN_ANS_SYNC_STATUS)));
        return answerDataModel;
    }

    public ArrayList<AnswerDataModel> getAllDirtyFeedback() {
        Log.e(TAG, "getAllDirtyFeedback: ");
        db = getDatabase();
        ArrayList<AnswerDataModel> answerDataModelArrayList = new ArrayList<>();
        String selection = COLUMN_ANS_SYNC_STATUS + " = ? OR " + COLUMN_ANS_SYNC_STATUS + " = ? ";
        String selectionArgs[] = {GlobalConstant.INSERTED, GlobalConstant.UPDATED};
        Cursor cursor = db.query(TABLE_NAME_FEEDBACK, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                answerDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        cursor.close();
        db.close();
        return answerDataModelArrayList;
    }

    public void deleteDirtyFeedback(ArrayList<AnswerDataModel> answerDataModelArrayList) {
        Log.e(TAG, "deleteDirtyFeedback: ");
        db = getDatabase();
        for (int i = 0; i < answerDataModelArrayList.size(); i++) {
            AnswerDataModel answerDataModel = answerDataModelArrayList.get(i);
            String whereClause = COLUMN_CLIENT_ANS_ID + " = ? AND " + COLUMN_ANS_SYNC_STATUS + " = ?";
            String[] whereArgs = {String.valueOf(answerDataModel.getAns_client_id()), answerDataModel.getAns_sync_status()};
            int deletedId = db.delete(TABLE_NAME_FEEDBACK, whereClause, whereArgs);
            Log.e(TAG, "deleteDirtyFeedback: " + deletedId);
        }
        db.close();
    }

    public void insertFeedbacksOfServer(ArrayList<AnswerDataModel> answerDataModelArrayList) {
        Log.e(TAG, "insertFeedbacksOfServer: ");

        db = getDatabase();
        AnswerDataModel answerDataModel;
        for (int i = 0; i < answerDataModelArrayList.size(); i++) {
            answerDataModel = answerDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_ANS_ID, answerDataModel.getAns_id());
            values.put(COLUMN_ANS_DEVL_ID, answerDataModel.getAns_devl_id());
            values.put(COLUMN_ANS_PROJ_ID, answerDataModel.getAns_proj_id());
            values.put(COLUMN_ANS_PHASE_ID, answerDataModel.getAns_phase_id());
            values.put(COLUMN_ANS_QUE_TEXT, answerDataModel.getAns_que_text());
            values.put(COLUMN_ANS_TEXT, answerDataModel.getAns_text());
            values.put(COLUMN_ANS_TYPE, answerDataModel.getAns_type());
            values.put(COLUMN_ANS_RANGE, answerDataModel.getAns_range());
            values.put(COLUMN_CREATED_AT, answerDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, answerDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, answerDataModel.getDeleted_at());
            values.put(COLUMN_ANS_SPID, answerDataModel.getAns_spid());
            values.put(COLUMN_ANS_CUST_ID, answerDataModel.getAns_cust_id());
            values.put(COLUMN_ANS_GROUP_ID, answerDataModel.getAns_group_id());
            values.put(COLUMN_ANS_FILE_LOCATION, answerDataModel.getAns_file_location());
            values.put(COLUMN_ANS_SYNC_STATUS, GlobalConstant.STRING_OK);
            String whereClause = COLUMN_ANS_ID + " = ? ";
            String whereArgs[] = {String.valueOf(answerDataModel.getAns_id())};
            long updatedCount = db.update(TABLE_NAME_FEEDBACK, values, whereClause, whereArgs);
            if (updatedCount <= 0) {
                db.insert(TABLE_NAME_FEEDBACK, null, values);
            }
        }
        db.close();
    }
}