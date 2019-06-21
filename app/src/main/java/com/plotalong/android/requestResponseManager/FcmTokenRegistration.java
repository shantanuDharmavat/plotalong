package com.plotalong.android.requestResponseManager;

import android.util.Log;

import com.plotalong.android.model.commonModel.SuccessModel;
import com.plotalong.android.model.commonModel.TokenRegistrationModel;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shantanu on 18/7/17.
 */

public class FcmTokenRegistration {
    private static final String TAG = FcmTokenRegistration.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private String token;

    public FcmTokenRegistration(String token) {
        this.token = token;
    }

    public void tokenRegistration() {
        Log.e(TAG, "tokenRegistration: ");
        ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.baseUrl);
        apiInterface.getTokenRegistrationModel(getTokenRegistrationModel())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SuccessModel>() {
                    @Override
                    public void onNext(@NonNull SuccessModel successModel) {
                        Log.e(TAG, "onNext: token");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: Token sent");
                    }
                });
    }

    private TokenRegistrationModel getTokenRegistrationModel() {
        Log.e(TAG, "getTokenRegistrationModel: ");
        Singleton singleton = Singleton.getInstance();
        String deviceId = singleton.getDeviceId();
        TokenRegistrationModel tokenRegistrationModel = new TokenRegistrationModel();
        tokenRegistrationModel.setToken(token);
        tokenRegistrationModel.setDeviceKey(deviceId);
        return tokenRegistrationModel;
    }
}
