package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.dataSource.MstUserDetailsDataSource;
import com.plotalong.android.listener.DeviceRegistrationListener;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationRequestDataModel;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationRequestModel;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationResponseModel;
import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.deviceRegistrationModel.UserModel;
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
 * Created by kbhakade on 24/5/17.
 */

public class DeviceRegistration {
    private final String TAG = DeviceRegistration.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final DeviceRegistrationListener deviceRegistrationListener;
    private final String userName;
    private final String userPassword;
    private final String userPassphrase;
    private String encryptedPassword = null;
    private String decryptedPassword = null;
    private Context context;

    public DeviceRegistration(Context context, DeviceRegistrationListener deviceRegistrationListener, String userName, String userPassword, String userPassphrase) {
        Log.e(TAG, "DeviceRegistration: ");
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPassphrase = userPassphrase;
        this.context = context;
        this.deviceRegistrationListener = deviceRegistrationListener;
    }

    public void requestForDeviceRegistration() {
        Log.e(TAG, "requestForDeviceRegistration: ");
        ProgressDialogManager.getProgressDialog(context);
        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getRegistrationInfo(getRequestModel())
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
                        deviceRegistrationListener.onDeviceRegistrationFailed(context.getResources().getString(R.string.serverIsDown));
                    }

                    @Override
                    public void onNext(RegistrationResponseModel registrationResponseModel) {
                        Log.e(TAG, "onNext: " + registrationResponseModel.toString());
                        if (!registrationResponseModel.getStatus().equalsIgnoreCase(GlobalConstant.STRING_success)) {
                            ProgressDialogManager.dismissProgressDialog();
                            deviceRegistrationListener.onDeviceRegistrationFailed(registrationResponseModel.getMessage());
                        } else {
                            SharedPreference.getInstance(context).saveIntSharedPreference(GlobalConstant.UserId, registrationResponseModel.getData().getUser_id());
                            SharedPreference.getInstance(context).saveIntSharedPreference(GlobalConstant.DeveloperId, registrationResponseModel.getData().getDevl_id());

                            UserModel userModel = registrationResponseModel.getData();
                            Gson gson = new Gson();
                            String json = gson.toJson(userModel);
                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.CURRENT_USER, json);
                            SharedPreference.getInstance(context).saveStringSharedPreference(GlobalConstant.ROLE_LICENSE, userModel.getRole_license());

                            // Firebase subscribe for developer id.
                            FirebaseMessaging.getInstance().subscribeToTopic(registrationResponseModel.getData().getAuth_api_key());

                            MstUserDetailsDataSource mstUserDetailsDataSource = new MstUserDetailsDataSource(context);
                            mstUserDetailsDataSource.insertUserDetails(registrationResponseModel, encryptedPassword);

                            ProgressDialogManager.dismissProgressDialog();
                            deviceRegistrationListener.onDeviceRegistrationSuccess();
                        }
                    }
                });
    }

    private RegistrationRequestModel getRequestModel() {
        Log.e(TAG, "getRequestModel: ");
        SignatureModel signatureModel = new SignatureModel();

        signatureModel.setUser_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.UserId)));
        signatureModel.setDevl_id(String.valueOf(SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId)));
        signatureModel.setTimestamp(DateUtil.getCurrentFormatDateAndTime());
        signatureModel.setDevice_key(Utility.getDeviceId(context));
        if (MainActivity.mLocation != null) {
            signatureModel.setLocation(String.valueOf(MainActivity.mLocation));
        } else {
            signatureModel.setLocation("");
        }

        // TODO: 6/9/17 it may required in future, do not delete it
//        String encryptedPassword = Utility.encryption("Pass@123", "beleben");
//        Log.e(TAG, "getRequestModel: " + encryptedPassword);
//        String decryptedPassword = Utility.decryption(encryptedPassword, "abcdef@gmail.com");
//        Log.e(TAG, "getRequestModel: " + decryptedPassword);


//        String encryptedPassword = null;
//        String decryptedPassword = null;
//
//        //Encrypt
//        try {
//            encryptedPassword = AESCrypt.encrypt(userName, userPassword);
//        } catch (GeneralSecurityException e) {
//            Log.e(TAG, "getRequestModel: " + e.toString());
//        }
//        Log.e(TAG, "getRequestModel: " + encryptedPassword);
//
//
//        //Decrypt
//        try {
//            decryptedPassword = AESCrypt.decrypt(userName, encryptedPassword);
//        } catch (GeneralSecurityException e) {
//            Log.e(TAG, "getRequestModel: " + e.toString());
//        }
//        Log.e(TAG, "getRequestModel: " + decryptedPassword);


        //New one
        MCrypt mcrypt = new MCrypt();
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
        RegistrationRequestDataModel registrationRequestDataModel = new RegistrationRequestDataModel();
        registrationRequestDataModel.setUser_email(userName);
        registrationRequestDataModel.setUser_password(userPassword);
        registrationRequestDataModel.setDevl_passphrase(userPassphrase);

        RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel();
        registrationRequestModel.setAction(GlobalConstant.STRING_register);
        registrationRequestModel.setType(GlobalConstant.STRING_request);
        registrationRequestModel.setData(registrationRequestDataModel);
        registrationRequestModel.setSignature(signatureModel);
        Log.e(TAG, "getRequestModel: " + registrationRequestModel);
        return registrationRequestModel;
    }
}