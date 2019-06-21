package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.plotalong.android.R;
import com.plotalong.android.activity.MainActivity;
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
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.listener.FullSyncListener;
import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.fullSyncModel.FullSyncDataModel;
import com.plotalong.android.model.fullSyncModel.FullSyncRequestDataModel;
import com.plotalong.android.model.fullSyncModel.FullSyncRequestModel;
import com.plotalong.android.model.fullSyncModel.FullSyncResponseModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.dialogManager.ProgressDialogManager;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kbhakade on 19/7/17.
 */

public class FullSync {
    private final String TAG = FullSync.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final FullSyncListener fullSyncListener;
    private Context context;

    public FullSync(Context context, FullSyncListener fullSyncListener) {
        Log.e(TAG, "FullSync: ");
        this.context = context;
        this.fullSyncListener = fullSyncListener;
    }

    public void callFullSync() {
        Log.e(TAG, "callFullSync: ");
        ProgressDialogManager.getProgressDialog(context);

        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getFullSyncData(getRequestModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FullSyncResponseModel>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                        ProgressDialogManager.dismissProgressDialog();
                        fullSyncListener.onFullSyncFailed(context.getResources().getString(R.string.serverIsDown));
                    }

                    @Override
                    public void onNext(FullSyncResponseModel fullSyncResponseModel) {
                        Log.e(TAG, "onNext: " + fullSyncResponseModel.toString());
                        if (!fullSyncResponseModel.getStatus().equalsIgnoreCase(GlobalConstant.STRING_success)) {
                            ProgressDialogManager.dismissProgressDialog();
                            fullSyncListener.onFullSyncFailed(fullSyncResponseModel.getMessage());
                        } else {
                            FullSyncDataModel fullSyncDataModel = fullSyncResponseModel.getData();

                            Log.e(TAG, "onNext: Projects = " + fullSyncDataModel.getProjects().size());
                            Log.e(TAG, "onNext: Phases = " + fullSyncDataModel.getPhases().size());
                            Log.e(TAG, "onNext: Plots = " + fullSyncDataModel.getPlots().size());
                            Log.e(TAG, "onNext: Customers = " + fullSyncDataModel.getCustomers().size());
                            Log.e(TAG, "onNext: Groups = " + fullSyncDataModel.getGroup().size());
                            Log.e(TAG, "onNext: Proposal Headers = " + fullSyncDataModel.getProposalHeaders().size());
                            Log.e(TAG, "onNext: Proposal Details = " + fullSyncDataModel.getProposalDetails().size());
                            Log.e(TAG, "onNext: Address = " + fullSyncDataModel.getAddress().size());
                            Log.e(TAG, "onNext: Questions = " + fullSyncDataModel.getQuestions().size());
                            Log.e(TAG, "onNext: MST Features = " + fullSyncDataModel.getMst_features().size());
                            Log.e(TAG, "onNext: CFG Features = " + fullSyncDataModel.getCfg_features().size());
                            Log.e(TAG, "onNext: Sessions = " + fullSyncDataModel.getSession().size());
                            Log.e(TAG, "onNext: CFG_CONTENT = " + fullSyncDataModel.getCfg_content().size());

                            Log.e(TAG, "onNext: LST = " + fullSyncDataModel.getLst());


                            if (fullSyncDataModel.getProjects().size() > 0) {
                                MstProjectDataSource mstProjectDataSource = new MstProjectDataSource(context);
                                mstProjectDataSource.insertProjectsOfServer(fullSyncDataModel.getProjects());
                            }

                            if (fullSyncDataModel.getPhases().size() > 0) {
                                MstPhaseDataSource mstPhaseDataSource = new MstPhaseDataSource(context);
                                mstPhaseDataSource.insertPhasesOfServer(fullSyncDataModel.getPhases());
                            }

                            if (fullSyncDataModel.getPlots().size() > 0) {
                                MstPlotDataSource mstPlotDataSource = new MstPlotDataSource(context);
                                mstPlotDataSource.insertPlotDetailsOfServer(fullSyncDataModel.getPlots());
                            }

                            if (fullSyncDataModel.getCustomers().size() > 0) {
                                MstCustomerDataSource mstCustomerDataSource = new MstCustomerDataSource(context);
                                mstCustomerDataSource.insertCustomersOfServers(fullSyncDataModel.getCustomers());
                            }

                            if (fullSyncDataModel.getGroup().size() > 0) {
                                CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
                                cfgCustomerGroupDataSource.insertCustomerGroupOfServer(fullSyncDataModel.getGroup());
                            }

                            if (fullSyncDataModel.getProposalHeaders().size() > 0) {
                                MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
                                mstProposalHeaderDataSource.insertProposalHeadersOfServer(fullSyncDataModel.getProposalHeaders());
                            }

                            if (fullSyncDataModel.getProposalDetails().size() > 0) {
                                MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
                                mstProposalDetailsDataSource.insertProposalDetailsOfServer(fullSyncDataModel.getProposalDetails());
                            }

                            if (fullSyncDataModel.getAddress().size() > 0) {
                                CfgAddressDataSource cfgAddressDataSource = new CfgAddressDataSource(context);
                                cfgAddressDataSource.insertAddressOfServer(fullSyncDataModel.getAddress());
                            }

                            if (fullSyncDataModel.getQuestions().size() > 0) {
                                MstFeedbackMasterDataSource mstFeedbackMasterDataSource = new MstFeedbackMasterDataSource(context);
                                mstFeedbackMasterDataSource.insertFeedbackDetailsOfServer(fullSyncDataModel.getQuestions());
                            }

                            if (fullSyncDataModel.getMst_features().size() > 0) {
                                MstFeaturesDataSource mstFeaturesDataSource = new MstFeaturesDataSource(context);
                                mstFeaturesDataSource.insertMstFeaturesOfServer(fullSyncDataModel.getMst_features());
                            }

                            if (fullSyncDataModel.getCfg_features().size() > 0) {
                                CfgFeaturesDataSource cfgFeaturesDataSource = new CfgFeaturesDataSource(context);
                                cfgFeaturesDataSource.insertCfgFeaturesOfServer(fullSyncDataModel.getCfg_features());
                            }

                            if (fullSyncDataModel.getSession().size() > 0) {
                                TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
                                trnSessionDataSource.insertSessionOfServer(fullSyncDataModel.getSession());
                            }

                            if (fullSyncDataModel.getCfg_content().size() > 0) {
                                CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(context);
                                cfgContentDataSource.insertContent(fullSyncDataModel.getCfg_content());
                            }

                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.LastQuickSyncTime, fullSyncDataModel.getLst());

                            ProgressDialogManager.dismissProgressDialog();
                            fullSyncListener.onFullSyncSuccess(fullSyncResponseModel.getData().getContentZip());
                        }
                    }
                });
    }

    private FullSyncRequestModel getRequestModel() {
        Log.e(TAG, "getRequestModel: ");
        SignatureModel signatureModel = new SignatureModel();
        signatureModel.setUser_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
        signatureModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));
        signatureModel.setTimestamp(GlobalConstant.EmptyDateForFullSync);
        signatureModel.setDevice_key(Utility.getDeviceId(context));
//        signatureModel.setRole_license(SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.ROLE_LICENSE));
        signatureModel.setRole_license("WFINAPP");
        if (MainActivity.mLocation != null) {
            signatureModel.setLocation(String.valueOf(MainActivity.mLocation));
        } else {
            signatureModel.setLocation("");
        }

        FullSyncRequestDataModel fullSyncRequestDataModel = new FullSyncRequestDataModel();
        fullSyncRequestDataModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));

        FullSyncRequestModel fullSyncRequestModel = new FullSyncRequestModel();
        fullSyncRequestModel.setAction(GlobalConstant.STRING_fullsync);
        fullSyncRequestModel.setType(GlobalConstant.STRING_request);
        fullSyncRequestModel.setData(fullSyncRequestDataModel);
        fullSyncRequestModel.setSignature(signatureModel);
        Log.e(TAG, "getRequestModel: " + fullSyncRequestModel.toString());
        return fullSyncRequestModel;
    }
}