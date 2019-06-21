package com.plotalong.android.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
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
import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncRequestDataModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncRequestModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncResponseModel;
import com.plotalong.android.model.syncMaster.SyncMasterDataModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kbhakade on 17/11/17.
 */

public class SyncCall {
    private static final String TAG = SyncCall.class.getSimpleName();
    private Context context;

    public void callSync(Context context, QuickSyncListener quickSyncListener) {
        Log.e(TAG, "callSync: ");
        this.context = context;
        SyncMasterDataSource syncMasterDataSource = new SyncMasterDataSource(context);
        boolean isSyncMasterEmpty = syncMasterDataSource.checkIsTableEmpty();
        if (isSyncMasterEmpty) {
            SyncMasterDataModel syncMasterDataModel = new SyncMasterDataModel();
            Gson gson = new Gson();
            String jsonString = gson.toJson(getRequestModel());

            syncMasterDataModel.setJson_string(jsonString);
            syncMasterDataModel.setLst(SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.LastQuickSyncTime));
            syncMasterDataSource.insertJsonString(syncMasterDataModel);
            quickSyncListener.onCallQuickSyncAgain();
        } else {
            ArrayList<ContentDataModel> contentDataModelArrayList;
            contentDataModelArrayList = getRequestModel().getData().getContents();
//            if (contentDataModelArrayList.size() > 0) {
            syncDataAndUploadContents(contentDataModelArrayList, quickSyncListener, getRequestModel());
//            } else {
//                QuickSync quickSync = new QuickSync(context, quickSyncListener);
//                quickSync.callQuickSync();
//            }
        }
    }


    private void syncDataAndUploadContents(ArrayList<ContentDataModel> contentDataModelArrayList, final QuickSyncListener quickSyncListener, QuickSyncRequestModel quickSyncRequestModel) {

        ProgressDialogManager.getProgressDialog(context);
        ArrayList<File> fileArrayList = new ArrayList<>();
        for (int i = 0; i < contentDataModelArrayList.size(); i++) {
            Uri myUri = Uri.parse(contentDataModelArrayList.get(i).getCont_file_location());
            File imageFIle = new File(String.valueOf(myUri));
            fileArrayList.add(imageFIle);
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < fileArrayList.size(); i++) {
            builder.addFormDataPart("files[]", fileArrayList.get(i).getName(), RequestBody.create(MediaType.parse("multipart/form-data"), fileArrayList.get(i)));
        }

        final Gson gson = new Gson();
        String stringObject = gson.toJson(quickSyncRequestModel, QuickSyncRequestModel.class);
        builder.addFormDataPart("data", stringObject);
        if (contentDataModelArrayList.size() > 0) {
            builder.addFormDataPart("withFiles", "true");
        } else {
            builder.addFormDataPart("withFiles", "false");
        }
        final MultipartBody requestBody = builder.build();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.quickSync(requestBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Toast.makeText(context, "Ready to use now.", Toast.LENGTH_SHORT).show();
                ProgressDialogManager.dismissProgressDialog();
                if (response.isSuccessful()) {
                    String string;
                    try {
                        string = response.body().string();
                        QuickSyncResponseModel quickSyncResponseModel = gson.fromJson(string, QuickSyncResponseModel.class);


                        QuickSyncModel quickSyncModel = quickSyncResponseModel.getData();

                        insertResponseIntoRespectiveTables(quickSyncModel);

                        deleteRequestedDataFromTables();

                        SyncMasterDataSource syncMasterDataSource = new SyncMasterDataSource(context);
                        syncMasterDataSource.deleteRecord();
//
                        SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.LastQuickSyncTime, quickSyncModel.getLst());

                        ProgressDialogManager.dismissProgressDialog();
                        quickSyncListener.onQuickSyncSuccess(quickSyncModel.getCfg_content());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    quickSyncListener.onQuickSyncFailed("Sync error");
                    ProgressDialogManager.dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());
                quickSyncListener.onQuickSyncFailed("File uploading error");
                ProgressDialogManager.dismissProgressDialog();
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

            if (quickSyncRequestModel.getData().getContents().size() > 0) {
                for (int i = 0; i < quickSyncRequestModel.getData().getContents().size(); i++) {
                    String filePath = quickSyncRequestModel.getData().getContents().get(i).getCont_file_location();
                    Log.e(TAG, "deleteRequestedDataFromTables: " + filePath);
                    filePath = filePath.substring(0, filePath.lastIndexOf(File.separator));
                    FileHandlingUtil.deleteFolderContent(filePath);
                }
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
        SignatureModel signatureModel = new SignatureModel();
        signatureModel.setUser_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
        signatureModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));
        signatureModel.setTimestamp(SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.LastQuickSyncTime));
        signatureModel.setDevice_key(Utility.getDeviceId(context));
        signatureModel.setRole_license(SharedPreference.getInstance(context).getStringSharedPreference(GlobalConstant.ROLE_LICENSE));

        if (MainActivity.mLocation != null) {
            signatureModel.setLocation(String.valueOf(MainActivity.mLocation));
        } else {
            signatureModel.setLocation("");
        }

        QuickSyncRequestDataModel quickSyncRequestDataModel = new QuickSyncRequestDataModel();
        quickSyncRequestDataModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));

        MstCustomerDataSource customerDataSource = new MstCustomerDataSource(context);
        CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(context);
        CfgAddressDataSource cfgAddressDataSource = new CfgAddressDataSource(context);
        MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(context);
        MstProposalDetailsDataSource mstProposalDetailsDataSource = new MstProposalDetailsDataSource(context);
        TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
        TrnFeedbackDataSource trnFeedbackDataSource = new TrnFeedbackDataSource(context);
        CfgContentDataSource cfgContentDataSource = new CfgContentDataSource(context);
        TrnTraceDetailsDataSource trnTraceDetailsDataSource = new TrnTraceDetailsDataSource(context);


        quickSyncRequestDataModel.setCustomers(customerDataSource.getAllDirtyCustomers());
        quickSyncRequestDataModel.setGroups(cfgCustomerGroupDataSource.getAllDirtyCustomerGroups());
        quickSyncRequestDataModel.setAdrresses(cfgAddressDataSource.getAllDirtyAddress());
        quickSyncRequestDataModel.setProposalHeaders(mstProposalHeaderDataSource.getAllDirtyProposalHeaders());
        quickSyncRequestDataModel.setProposalDetails(mstProposalDetailsDataSource.getAllDirtyProposalDetails());
        quickSyncRequestDataModel.setSessions(trnSessionDataSource.getAllDirtySessions());
        quickSyncRequestDataModel.setFeedbacks(trnFeedbackDataSource.getAllDirtyFeedback());
        quickSyncRequestDataModel.setContents(cfgContentDataSource.getAllDirtyContent());
        quickSyncRequestDataModel.setTrace(trnTraceDetailsDataSource.getAllTraces());

        QuickSyncRequestModel quickSyncRequestModel = new QuickSyncRequestModel();
        quickSyncRequestModel.setAction(GlobalConstant.STRING_quicksync);
        quickSyncRequestModel.setType(GlobalConstant.STRING_request);
        quickSyncRequestModel.setData(quickSyncRequestDataModel);
        quickSyncRequestModel.setSignature(signatureModel);
        return quickSyncRequestModel;
    }
}