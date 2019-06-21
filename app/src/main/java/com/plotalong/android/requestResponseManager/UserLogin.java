package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.dataSource.MstUserDetailsDataSource;
import com.plotalong.android.listener.UserLoginListener;
import com.plotalong.android.model.deviceRegistrationModel.UserModel;
import com.plotalong.android.model.loginModel.LoginRequestDataModel;
import com.plotalong.android.model.loginModel.LoginRequestModel;
import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationResponseModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.DateUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.MCrypt;
import com.plotalong.android.dialogManager.ProgressDialogManager;
import com.plotalong.android.util.SharedPreference;
import com.plotalong.android.util.Utility;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kbhakade on 6/7/17.
 */

public class UserLogin {
    private final String TAG = UserLogin.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final String userName;
    private final String userPassword;
    private final UserLoginListener userLoginListener;
    private Context context;
    private MCrypt mcrypt;
    private String encryptedPassword;
    private String decryptedPassword;

    public UserLogin(Context context, UserLoginListener userLoginListener, String userName, String userPassword) {
        Log.e(TAG, "UserLogin: ");
        this.userName = userName;
        this.userPassword = userPassword;
        this.context = context;
        this.userLoginListener = userLoginListener;
    }

    public void requestForUserLogin() {
        Log.e(TAG, "requestForUserLogin: ");
        ProgressDialogManager.getProgressDialog(context);

        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getLoginInfo(getRequestModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegistrationResponseModel>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                        ProgressDialogManager.dismissProgressDialog();
                        userLoginListener.onLoginFail(context.getResources().getString(R.string.serverIsDown));
                    }

                    @Override
                    public void onNext(RegistrationResponseModel registrationResponseModel) {
                        Log.e(TAG, "onNext: " + registrationResponseModel.toString());
                        if (!registrationResponseModel.getStatus().equalsIgnoreCase(GlobalConstant.STRING_success)) {
                            MstUserDetailsDataSource mstUserDetailsDataSource = new MstUserDetailsDataSource(context);
                            mstUserDetailsDataSource.insertUserDetails(registrationResponseModel, encryptedPassword);
                            ProgressDialogManager.dismissProgressDialog();
                            userLoginListener.onLoginFail(registrationResponseModel.getMessage());
//                            EventBus.getDefault().post(new LoginEvent(registrationResponseModel.getMessage(),0));
                        } else {
                            MstUserDetailsDataSource mstUserDetailsDataSource = new MstUserDetailsDataSource(context);
                            mstUserDetailsDataSource.insertUserDetails(registrationResponseModel, encryptedPassword);

                            UserModel userModel = registrationResponseModel.getData();
                            Gson gson = new Gson();
                            String json = gson.toJson(userModel);
                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.CURRENT_USER, json);
                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.ROLE_LICENSE, userModel.getRole_license());

                            ProgressDialogManager.dismissProgressDialog();
                            userLoginListener.onLoginSuccess();
//                            EventBus.getDefault().post(new LoginEvent(registrationResponseModel.getMessage(),1));
                        }
                    }
                });
    }

    private LoginRequestModel getRequestModel() {
        Log.e(TAG, "getRequestModel: ");
        SignatureModel signatureModel = new SignatureModel();
        LoginRequestDataModel loginRequestDataModel = new LoginRequestDataModel();

        signatureModel.setUser_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
        signatureModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));
        signatureModel.setTimestamp(DateUtil.getCurrentFormatDateAndTime());
        signatureModel.setDevice_key(Utility.getDeviceId(context));
        if (MainActivity.mLocation != null) {
            signatureModel.setLocation(String.valueOf(MainActivity.mLocation));
        } else {
            signatureModel.setLocation("");
        }

        //New one
        mcrypt = new MCrypt();
        try {
            encryptedPassword = MCrypt.bytesToHex(mcrypt.encrypt(userPassword, userName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getRequestModel: " + encryptedPassword);
        try {
            decryptedPassword = new String(mcrypt.decrypt(encryptedPassword, userName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getRequestModel: " + decryptedPassword);

        loginRequestDataModel.setUser_email(userName);
        loginRequestDataModel.setUser_password(userPassword);

        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setAction(GlobalConstant.STRING_login);
        loginRequestModel.setType(GlobalConstant.STRING_request);
        loginRequestModel.setData(loginRequestDataModel);
        loginRequestModel.setSignature(signatureModel);
        Log.e(TAG, "getRequestModel: " + loginRequestModel.toString());
        return loginRequestModel;
    }
}