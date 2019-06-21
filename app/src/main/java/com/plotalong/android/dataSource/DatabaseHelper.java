package com.plotalong.android.dataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.plotalong.android.model.syncMaster.SyncMasterDataModel;
import com.plotalong.android.util.GlobalConstant;

/**
 * Created by kbhakade on 24/5/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PlotAlong.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private Context context;

    public DatabaseHelper(Context context, String DATABASE_NAME, Object o, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: ");
        db.execSQL(SyncMasterDataSource.CREATE_TABLE_SYNC_MASTER);
        db.execSQL(CfgCustomerGroupDataSource.CREATE_TABLE_CFG_CUST_GROUP);
        db.execSQL(CfgAddressDataSource.CREATE_TABLE_CFG_ADDRESS);
        db.execSQL(CfgProjectBankDataSource.CREATE_TABLE_CFG_PROJ_BANK);
        db.execSQL(MstPlotDataSource.CREATE_TABLE_MST_PLOT);
        db.execSQL(MstCustomerDataSource.CREATE_TABLE_MST_CUSTOMER);
        db.execSQL(MstFeaturesDataSource.CREATE_TABLE_MST_FEATURES);
        db.execSQL(CfgFeaturesDataSource.CREATE_TABLE_CFG_FEATURES);
        db.execSQL(MstOrganizationDataSource.CREATE_TABLE_MST_ORGANIZATION);
        db.execSQL(MstUserDetailsDataSource.CREATE_TABLE_MST_USER_DETAILS);
        db.execSQL(MstProjectDataSource.CREATE_TABLE_MST_PROJECT);
        db.execSQL(MstPhaseDataSource.CREATE_TABLE_MST_PHASE);
        db.execSQL(MstUserAccessDataSource.CREATE_TABLE_MST_USER_ACCESS);
        db.execSQL(TrnBkformPaymentDetailsDataSource.CREATE_TABLE_TRN_BKFORM_PAYMENT_DETAILS);
        db.execSQL(TrnBookingFormDataSource.CREATE_TABLE_TRN_BOOKING_FORM);
        db.execSQL(TrnSalesActivityDataSource.CREATE_TABLE_TRN_SALES_ACTI);
        db.execSQL(TrnDeviceLoggerDataSource.CREATE_TABLE_TRN_DEVICE_LOGGER);
        db.execSQL(CfgContentDataSource.CREATE_TABLE_CFG_CONTENT);
        db.execSQL(CfgContactStatusDataSource.CREATE_TABLE_CFG_CONTACT_STATUS);
        db.execSQL(TrnTraceDetailsDataSource.CREATE_TABLE_TRACE_DETAILS);
        db.execSQL(MstProposalHeaderDataSource.CREATE_TABLE_PROPOSAL_HEADER);
        db.execSQL(MstProposalDetailsDataSource.CREATE_TABLE_PROPOSAL_DETAILS);
        db.execSQL(TrnSessionDataSource.CREATE_TABLE_SESSION_DETAILS);
        db.execSQL(MstFeedbackMasterDataSource.CREATE_TABLE_FEEDBACK_MASTER);
        db.execSQL(TrnFeedbackDataSource.CREATE_TABLE_FEEDBACK);
        db.execSQL(TrnActivityDataSource.CREATE_TABLE_DETAILS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
        // TODO: 24/5/17 Here we can write code for delete and update or start over
        db.execSQL(SyncMasterDataSource.DELETE_TABLE_SYNC_MASTER);
        db.execSQL(CfgCustomerGroupDataSource.DELETE_TABLE_CFG_CUST_GROUP);
        db.execSQL(CfgAddressDataSource.DELETE_TABLE_CFG_ADDRESS);
        db.execSQL(CfgProjectBankDataSource.DELETE_TABLE_CFG_PROJ_BANK);
        db.execSQL(CfgContentDataSource.DELETE_TABLE_CFG_CONTENT);
        db.execSQL(CfgContactStatusDataSource.DELETE_TABLE_CFG_CONTACT_STATUS);
        db.execSQL(MstCustomerDataSource.DELETE_TABLE_MST_CUSTOMER);
        db.execSQL(MstPlotDataSource.DELETE_TABLE_MST_PLOT);
        db.execSQL(MstProjectDataSource.DELETE_TABLE_MST_PROJECT);
        db.execSQL(MstPhaseDataSource.DELETE_TABLE_MST_PHASE);
        db.execSQL(MstFeaturesDataSource.DELETE_TABLE_MST_FEATURES);
        db.execSQL(CfgFeaturesDataSource.DELETE_TABLE_CFG_FEATURES);
        db.execSQL(MstOrganizationDataSource.DELETE_TABLE_MST_ORGANIZATION);
        db.execSQL(MstUserAccessDataSource.DELETE_TABLE_MST_USER_ACCESS);
        db.execSQL(MstUserDetailsDataSource.DELETE_TABLE_MST_USER_DETAILS);
        db.execSQL(TrnSalesActivityDataSource.DELETE_TABLE_TRN_SALES_ACTI);
        db.execSQL(TrnBkformPaymentDetailsDataSource.DELETE_TABLE_TRN_BKFORM_PAYMENT_DETAILS);
        db.execSQL(TrnBookingFormDataSource.DELETE_TABLE_TRN_BOOKING_FORM);
        db.execSQL(TrnDeviceLoggerDataSource.DELETE_TABLE_TRN_DEVICE_LOGGER);
        db.execSQL(TrnTraceDetailsDataSource.DELETE_TABLE_LOCATION);
        db.execSQL(MstProposalDetailsDataSource.DELETE_TABLE_PROPOSAL_DETAILS);
        db.execSQL(MstProposalHeaderDataSource.DELETE_TABLE_PROPOSAL_HEADER);
        db.execSQL(TrnSessionDataSource.DELETE_TABLE_SESSION_DETAILS);
        db.execSQL(MstFeedbackMasterDataSource.DELETE_TABLE_FEEDBACK_MASTER);
        db.execSQL(TrnFeedbackDataSource.DELETE_TABLE_FEEDBACK);
        db.execSQL(TrnActivityDataSource.DELETE_TABLE_TRN_ACTIVITY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onDowngrade: ");
        onUpgrade(db, oldVersion, newVersion);
    }


    public SQLiteDatabase getDatabase() {
        Log.e(TAG, "getDatabase: ");
        return this.getWritableDatabase();
    }

    /*public void getProjectData(){
        List<ProjectPhaseModel> list = new MstProjectDataSource(context).getProjectPhaseData(DATABASE_NAME);
    }*/
}
