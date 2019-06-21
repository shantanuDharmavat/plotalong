package com.plotalong.android.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plotalong.android.model.feedback.QuestionsDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 30/10/17.
 */

public class MstFeedbackMasterDataSource extends DatabaseHelper {
    public static final String TABLE_NAME_FEEDBACK_MASTER = "feedback_master";
    public static final String COLUMN_CLIENT_QUE_ID = "client_que_id";
    public static final String COLUMN_QUE_ID = "que_id";
    public static final String COLUMN_QUE_DEVL_ID = "que_devl_id";
    public static final String COLUMN_QUE_PROJ_ID = "que_proj_id";
    public static final String COLUMN_QUE_PHASE_ID = "que_phase_id";
    public static final String COLUMN_QUE_TEXT = "que_text";
    public static final String COLUMN_QUE_ANS_TYPE = "que_ans_type";
    public static final String COLUMN_QUE_ANS_RANGE = "que_ans_range";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_DELETED_AT = "deleted_at";


    public static final String CREATE_TABLE_FEEDBACK_MASTER = "CREATE TABLE "
            + TABLE_NAME_FEEDBACK_MASTER + "("
            + COLUMN_CLIENT_QUE_ID
            + " INTEGER PRIMARY KEY,"
            + COLUMN_QUE_ID
            + " INTEGER,"
            + COLUMN_QUE_DEVL_ID
            + " INTEGER,"
            + COLUMN_QUE_PROJ_ID
            + " INTEGER,"
            + COLUMN_QUE_PHASE_ID
            + " INTEGER,"
            + COLUMN_QUE_TEXT
            + " TEXT,"
            + COLUMN_QUE_ANS_TYPE
            + " TEXT,"
            + COLUMN_QUE_ANS_RANGE
            + " INTEGER,"
            + COLUMN_CREATED_AT
            + " TEXT,"
            + COLUMN_UPDATED_AT
            + " TEXT,"
            + COLUMN_DELETED_AT
            + " TEXT"
            + ")";
    public static final String DELETE_TABLE_FEEDBACK_MASTER = "DROP TABLE IF EXISTS " + TABLE_NAME_FEEDBACK_MASTER;
    private static final String TAG = MstFeedbackMasterDataSource.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static String[] allColumns = {
            COLUMN_CLIENT_QUE_ID,
            COLUMN_QUE_ID,
            COLUMN_QUE_DEVL_ID,
            COLUMN_QUE_PROJ_ID,
            COLUMN_QUE_PHASE_ID,
            COLUMN_QUE_TEXT,
            COLUMN_QUE_ANS_TYPE,
            COLUMN_QUE_ANS_RANGE,
            COLUMN_CREATED_AT,
            COLUMN_UPDATED_AT,
            COLUMN_DELETED_AT
    };

    private SQLiteDatabase db;

    public MstFeedbackMasterDataSource(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION);
    }

    /***
     * This method is for insert user details into user table
     *
     * @param questionsDataModelArrayList
     */
    public void insertFeedbackDetailsOfServer(ArrayList<QuestionsDataModel> questionsDataModelArrayList) {
        Log.e(TAG, "insertFeedbackDetailsOfServer: ");
        db = getDatabase();
        QuestionsDataModel questionsDataModel;
        for (int i = 0; i < questionsDataModelArrayList.size(); i++) {
            questionsDataModel = questionsDataModelArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUE_ID, questionsDataModel.getQue_id());
            values.put(COLUMN_QUE_DEVL_ID, questionsDataModel.getQue_devl_id());
            values.put(COLUMN_QUE_PROJ_ID, questionsDataModel.getQue_proj_id());
            values.put(COLUMN_QUE_PHASE_ID, questionsDataModel.getQue_phase_id());
            values.put(COLUMN_QUE_TEXT, questionsDataModel.getQue_text());
            values.put(COLUMN_QUE_ANS_TYPE, questionsDataModel.getQue_ans_type());
            values.put(COLUMN_QUE_ANS_RANGE, questionsDataModel.getQue_ans_range());
            values.put(COLUMN_CREATED_AT, questionsDataModel.getCreated_at());
            values.put(COLUMN_UPDATED_AT, questionsDataModel.getUpdated_at());
            values.put(COLUMN_DELETED_AT, questionsDataModel.getDeleted_at());

            String whereClause = COLUMN_QUE_ID + " = ? ";
            String whereArgs[] = {String.valueOf(questionsDataModel.getQue_id())};
            long updatedCount = db.update(TABLE_NAME_FEEDBACK_MASTER, values, whereClause, whereArgs);
            if (updatedCount <= 0) {
                db.insert(TABLE_NAME_FEEDBACK_MASTER, null, values);
            }
        }
        db.close();
    }

    public ArrayList<QuestionsDataModel> getAllQuestions(int proj_phas_id) {
        Log.e(TAG, "getAllQuestions: ");
        db = getDatabase();
        String selection = COLUMN_QUE_PHASE_ID + " = ?";
        String selectionArgs[] = {String.valueOf(proj_phas_id)};
        ArrayList<QuestionsDataModel> questionsDataModelArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_FEEDBACK_MASTER, allColumns, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                questionsDataModelArrayList.add(cursorToModel(cursor));
            }
        }
        return questionsDataModelArrayList;
    }

    private QuestionsDataModel cursorToModel(Cursor cursor) {
        QuestionsDataModel questionsDataModel = new QuestionsDataModel();
        questionsDataModel.setQue_id(cursor.getInt(cursor.getColumnIndex(COLUMN_QUE_ID)));
        questionsDataModel.setQue_devl_id(cursor.getInt(cursor.getColumnIndex(COLUMN_QUE_DEVL_ID)));
        questionsDataModel.setQue_proj_id(cursor.getInt(cursor.getColumnIndex(COLUMN_QUE_PROJ_ID)));
        questionsDataModel.setQue_phase_id(cursor.getInt(cursor.getColumnIndex(COLUMN_QUE_PHASE_ID)));
        questionsDataModel.setQue_text(cursor.getString(cursor.getColumnIndex(COLUMN_QUE_TEXT)));
        questionsDataModel.setQue_ans_type(cursor.getString(cursor.getColumnIndex(COLUMN_QUE_ANS_TYPE)));
        questionsDataModel.setQue_ans_range(cursor.getInt(cursor.getColumnIndex(COLUMN_QUE_ANS_RANGE)));
        questionsDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_AT)));
        questionsDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_AT)));
        questionsDataModel.setDeleted_at(cursor.getString(cursor.getColumnIndex(COLUMN_DELETED_AT)));
        return questionsDataModel;
    }
}