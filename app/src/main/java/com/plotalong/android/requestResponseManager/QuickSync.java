package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.dataSource.CfgAddressDataSource;
import com.plotalong.android.dataSource.CfgContentDataSource;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.CfgFeaturesDataSource;
import com.plotalong.android.dataSource.MstCustomerDataSource;
import com.plotalong.android.dataSource.MstFeaturesDataSource;
import com.plotalong.android.dataSource.MstFeedbackMasterDataSource;
import com.plotalong.android.dataSource.MstPhaseDataSource;
import com.plotalong.android.dataSource.MstPlotDataSource;
import com.plotalong.android.dataSource.MstProjectDataSource;
import com.plotalong.android.dataSource.MstProposalDetailsDataSource;
import com.plotalong.android.dataSource.MstProposalHeaderDataSource;
import com.plotalong.android.dataSource.SyncMasterDataSource;
import com.plotalong.android.dataSource.TrnFeedbackDataSource;
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.dataSource.TrnTraceDetailsDataSource;
import com.plotalong.android.dialogManager.ProgressDialogManager;
import com.plotalong.android.listener.QuickSyncListener;
import com.plotalong.android.model.quickSyncModel.QuickSyncModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncRequestModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncResponseModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kbhakade on 31/5/17.
 */

public class QuickSync {
    private final String TAG = QuickSync.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final QuickSyncListener quickSyncListener;
    private Context context;
    private QuickSyncRequestModel quickSyncRequestModel;

    public QuickSync(Context context, QuickSyncListener quickSyncListener) {
        Log.e(TAG, "QuickSync: ");
        this.context = context;
        this.quickSyncListener = quickSyncListener;
    }

    public void callQuickSync() {
        Log.e(TAG, "callQuickSync: ");
        ProgressDialogManager.getProgressDialog(context);
        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getQuickSyncData(getRequestModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuickSyncResponseModel>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: " + e.toString());
                        ProgressDialogManager.dismissProgressDialog();
                        quickSyncListener.onQuickSyncFailed(context.getResources().getString(R.string.serverIsDown));
                    }

                    @Override
                    public void onNext(QuickSyncResponseModel quickSyncResponseModel) {
                        Log.e(TAG, "onNext: " + quickSyncResponseModel.toString());
                        if (!quickSyncResponseModel.getStatus().equalsIgnoreCase(GlobalConstant.STRING_success)) {
                            ProgressDialogManager.dismissProgressDialog();
                            quickSyncListener.onQuickSyncFailed(quickSyncResponseModel.getMessage());
                        } else {
                            QuickSyncModel quickSyncModel = quickSyncResponseModel.getData();

                            insertResponseIntoRespectiveTables(quickSyncModel);

                            deleteRequestedDataFromTables();

                            SyncMasterDataSource syncMasterDataSource = new SyncMasterDataSource(context);
                            syncMasterDataSource.deleteRecord();

                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.LastQuickSyncTime, quickSyncModel.getLst());

                            ProgressDialogManager.dismissProgressDialog();
                            quickSyncListener.onQuickSyncSuccess(quickSyncModel.getCfg_content());
                        }
                    }
                });
    }

    private void deleteRequestedDataFromTables() {
        Log.e(TAG, "deleteRequestedDataFromTables: ");
        SyncMasterDataSource syncMasterDataSource = new SyncMasterDataSource(context);
        String requestString = syncMasterDataSource.getRequestJson();
        QuickSyncRequestModel quickSyncRequestModel;
        if (requestString != null) {
            Gson gson = new Gson();
            quickSyncRequestModel = gson.fromJson(requestString, QuickSyncRequestModel.class);

            if (quickSyncRequestModel.getData().getCustomers().size() > 0) {
                MstCustomerDataSource customerDataSource = new MstCustomerDataSource(context);
                customerDataSource.deleteDirtyCustomers(quickSyncRequestModel.getData().getCustomers());
            }
            if (quickSyncRequestModel.getData().getGroups().size() > 0) {
                CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
                cfgCustomerGroupDataSource.deleteDirtyGroups(quickSyncRequestModel.getData().getGroups());
            }

            if (quickSyncRequestModel.getData().getAdrresses().size() > 0) {
                CfgAddressDataSource cfgAddressDataSource = new CfgAddressDataSource(context);
                cfgAddressDataSource.deleteDirtyAddresses(quickSyncRequestModel.getData().getAdrresses());
            }

            if (quickSyncRequestModel.getData().getProposalHeaders().size() > 0) {
                MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
                mstProposalHeaderDataSource.deleteDirtyProposalHeaders(quickSyncRequestModel.getData().getProposalHeaders());
            }

            if (quickSyncRequestModel.getData().getProposalDetails().size() > 0) {
                MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
                mstProposalDetailsDataSource.deleteDirtyProposalDetails(quickSyncRequestModel.getData().getProposalDetails());
            }

            if (quickSyncRequestModel.getData().getSessions().size() > 0) {
                TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
                trnSessionDataSource.deleteDirtySessions(quickSyncRequestModel.getData().getSessions());
            }

            if (quickSyncRequestModel.getData().getFeedbacks().size() > 0) {
                TrnFeedbackDataSource trnFeedbackDataSource = new TrnFeedbackDataSource(context);
                trnFeedbackDataSource.deleteDirtyFeedback(quickSyncRequestModel.getData().getFeedbacks());
            }

            if (quickSyncRequestModel.getData().getContents().size() > 0) {
                CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(context);
                cfgContentDataSource.deleteDirtyContents(quickSyncRequestModel.getData().getContents());
            }

            if (quickSyncRequestModel.getData().getTrace() != null && quickSyncRequestModel.getData().getTrace().size() > 0) {
                TrnTraceDetailsDataSource trnTraceDetailsDataSource = new TrnTraceDetailsDataSource(context);
                trnTraceDetailsDataSource.deleteDirtyTrace(quickSyncRequestModel.getData().getTrace());
            }
        }
    }

    private void insertResponseIntoRespectiveTables(QuickSyncModel quickSyncModel) {
        Log.e(TAG, "insertResponseIntoRespectiveTables: ");
        Log.e(TAG, "onNext: Projects = " + quickSyncModel.getProjects().size());
        Log.e(TAG, "onNext: Phases = " + quickSyncModel.getPhases().size());
        Log.e(TAG, "onNext: Plots = " + quickSyncModel.getPlots().size());
        Log.e(TAG, "onNext: Customers = " + quickSyncModel.getCustomers().size());
        Log.e(TAG, "onNext: Groups = " + quickSyncModel.getGroup().size());
        Log.e(TAG, "onNext: Proposal Headers = " + quickSyncModel.getProposalHeaders().size());
        Log.e(TAG, "onNext: Proposal Details = " + quickSyncModel.getProposalDetails().size());
        Log.e(TAG, "onNext: Address = " + quickSyncModel.getAddress().size());
        Log.e(TAG, "onNext: Questions = " + quickSyncModel.getQuestions().size());
        Log.e(TAG, "onNext: Feedbacks = " + quickSyncModel.getFeedbacks().size());
        Log.e(TAG, "onNext: MST Features = " + quickSyncModel.getMst_features().size());
        Log.e(TAG, "onNext: CFG Features = " + quickSyncModel.getCfg_features().size());
        Log.e(TAG, "onNext: Sessions = " + quickSyncModel.getSession().size());
        Log.e(TAG, "onNext: LST = " + quickSyncModel.getLst());
        Log.e(TAG, "onNext: CFG_CONTENT = " + quickSyncModel.getCfg_content().size());


        if (quickSyncModel.getProjects().size() > 0) {
            MstProjectDataSource mstProjectDataSource = new MstProjectDataSource(context);
            mstProjectDataSource.insertProjectsOfServer(quickSyncModel.getProjects());
        }

        if (quickSyncModel.getPhases().size() > 0) {
            MstPhaseDataSource mstPhaseDataSource = new MstPhaseDataSource(context);
            mstPhaseDataSource.insertPhasesOfServer(quickSyncModel.getPhases());
        }

        if (quickSyncModel.getPlots().size() > 0) {
            MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
            mstPlotDataSource.insertPlotDetailsOfServer(quickSyncModel.getPlots());
        }

        if (quickSyncModel.getCustomers().size() > 0) {
            MstCustomerDataSource mstCustomerDataSource = new MstCustomerDataSource(context);
            mstCustomerDataSource.insertCustomersOfServers(quickSyncModel.getCustomers());
        }

        if (quickSyncModel.getGroup().size() > 0) {
            CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
            cfgCustomerGroupDataSource.insertCustomerGroupOfServer(quickSyncModel.getGroup());
        }

        if (quickSyncModel.getProposalHeaders().size() > 0) {
            MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
            mstProposalHeaderDataSource.insertProposalHeadersOfServer(quickSyncModel.getProposalHeaders());
        }

        if (quickSyncModel.getProposalDetails().size() > 0) {
            MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
            mstProposalDetailsDataSource.insertProposalDetailsOfServer(quickSyncModel.getProposalDetails());
        }

        if (quickSyncModel.getAddress().size() > 0) {
            CfgAddressDataSource cfgAddressDataSource = new CfgAddressDataSource(context);
            cfgAddressDataSource.insertAddressOfServer(quickSyncModel.getAddress());
        }

        if (quickSyncModel.getQuestions().size() > 0) {
            MstFeedbackMasterDataSource mstFeedbackMasterDataSource = new MstFeedbackMasterDataSource(context);
            mstFeedbackMasterDataSource.insertFeedbackDetailsOfServer(quickSyncModel.getQuestions());
        }

        if (quickSyncModel.getFeedbacks().size() > 0) {
            TrnFeedbackDataSource trnFeedbackDataSource = new TrnFeedbackDataSource(context);
            trnFeedbackDataSource.insertFeedbacksOfServer(quickSyncModel.getFeedbacks());
        }

        if (quickSyncModel.getMst_features().size() > 0) {
            MstFeaturesDataSource mstFeaturesDataSource = new MstFeaturesDataSource(context);
            mstFeaturesDataSource.insertMstFeaturesOfServer(quickSyncModel.getMst_features());
        }

        if (quickSyncModel.getCfg_features().size() > 0) {
            CfgFeaturesDataSource cfgFeaturesDataSource = new CfgFeaturesDataSource(context);
            cfgFeaturesDataSource.insertCfgFeaturesOfServer(quickSyncModel.getCfg_features());
        }

        if (quickSyncModel.getSession().size() > 0) {
            TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
            trnSessionDataSource.insertSessionOfServer(quickSyncModel.getSession());
        }

        if (quickSyncModel.getCfg_content().size() > 0) {
            CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(context);
            cfgContentDataSource.insertContent(quickSyncModel.getCfg_content());
        }

        /*if (quickSyncModel.getTrace() != null && quickSyncModel.getTrace().size() > 0) {
            Log.e(TAG, "insertResponseIntoRespectiveTables: TRACE RECEIVED");
        }*/


    }

    private QuickSyncRequestModel getRequestModel() {
        Log.e(TAG, "getRequestModel: ");
        SyncMasterDataSource syncMasterDataSource = new SyncMasterDataSource(context);
        String requestString = syncMasterDataSource.getRequestJson();
        if (requestString != null) {
            Gson gson = new Gson();
            quickSyncRequestModel = gson.fromJson(requestString, QuickSyncRequestModel.class);
        }
        return quickSyncRequestModel;
    }
}