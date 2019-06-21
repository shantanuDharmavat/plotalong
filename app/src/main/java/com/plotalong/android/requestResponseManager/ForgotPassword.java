package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.plotalong.android.R;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.dialogManager.ProgressDialogManager;
import com.plotalong.android.listener.ForgotPasswordListener;
import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.forgotPassword.ForgotPassRequestDataModel;
import com.plotalong.android.model.forgotPassword.ForgotPassRequestModel;
import com.plotalong.android.model.forgotPassword.ForgotPassResponseModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kbhakade on 23/9/17.
 */

public class ForgotPassword {
    private final String TAG = ForgotPassword.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final ForgotPasswordListener forgotPasswordListener;
    private final String email;
    private Context context;

    public ForgotPassword(Context context, ForgotPasswordListener forgotPasswordListener, String email) {
        Log.e(TAG, "UserLogin: ");
        this.context = context;
        this.forgotPasswordListener = forgotPasswordListener;
        this.email = email;
    }

    public void requestForForgotPass() {
        Log.e(TAG, "requestForUserLogin: ");
        ProgressDialogManager.getProgressDialog(context);
        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getForgotPass(getRequestModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ForgotPassResponseModel>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                        ProgressDialogManager.dismissProgressDialog();
                        forgotPasswordListener.onForgotPassResponse(context.getResources().getString(R.string.serverIsDown));
                    }

                    @Override
                    public void onNext(ForgotPassResponseModel forgotPassResponseModel) {
                        Log.e(TAG, "onNext: " + forgotPassResponseModel.toString());
                        ProgressDialogManager.dismissProgressDialog();
                        forgotPasswordListener.onForgotPassResponse(forgotPassResponseModel.getMessage());
                    }
                });
    }

    private ForgotPassRequestModel getRequestModel() {
        Log.e(TAG, "getRequestModel: ");
        SignatureModel signatureModel = new SignatureModel();
        signatureModel.setUser_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
        signatureModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));
        signatureModel.setTimestamp(DateUtil.getCurrentTimeStamp());
        signatureModel.setDevice_key(Utility.getDeviceId(context));
        if (MainActivity.mLocation != null) {
            signatureModel.setLocation(String.valueOf(MainActivity.mLocation));
        }

        ForgotPassRequestDataModel forgotPassRequestDataModel = new ForgotPassRequestDataModel();
        forgotPassRequestDataModel.setUser_email(email);

        ForgotPassRequestModel ForgotPassRequestModel = new ForgotPassRequestModel();
        ForgotPassRequestModel.setAction(GlobalConstant.STRING_forgotPass);
        ForgotPassRequestModel.setType(GlobalConstant.STRING_request);
        ForgotPassRequestModel.setData(forgotPassRequestDataModel);
        ForgotPassRequestModel.setSignature(signatureModel);
        Log.e(TAG, "getRequestModel: " + ForgotPassRequestModel);
        return ForgotPassRequestModel;
    }
}