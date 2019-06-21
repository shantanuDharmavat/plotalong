package com.plotalong.android.retrofitApi;


import com.plotalong.android.model.loginModel.LoginRequestModel;
import com.plotalong.android.model.commonModel.SuccessModel;
import com.plotalong.android.model.commonModel.TokenRegistrationModel;

import com.plotalong.android.model.deviceRegistrationModel.RegistrationRequestModel;
import com.plotalong.android.model.deviceRegistrationModel.RegistrationResponseModel;
import com.plotalong.android.model.direction.DirectionResults;
import com.plotalong.android.model.forgotPassword.ForgotPassRequestModel;
import com.plotalong.android.model.forgotPassword.ForgotPassResponseModel;
import com.plotalong.android.model.fullSyncModel.FullSyncRequestModel;
import com.plotalong.android.model.fullSyncModel.FullSyncResponseModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncRequestModel;
import com.plotalong.android.model.quickSyncModel.QuickSyncResponseModel;
import com.plotalong.android.util.GlobalConstant;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by kbhakade on 29/5/17.
 */

public interface ApiInterface {
    String baseUrl = GlobalConstant.BASE_URL;
    String directionUrl = GlobalConstant.DIRECTION_URL;

    @POST("/deviceAction")
    Observable<RegistrationResponseModel> getRegistrationInfo(@Body RegistrationRequestModel registrationRequestModel);

    @POST("/deviceAction")
    Observable<RegistrationResponseModel> getLoginInfo(@Body LoginRequestModel loginRequestModel);

    @POST("/deviceAction")
    Observable<QuickSyncResponseModel> getQuickSyncData(@Body QuickSyncRequestModel quickSyncRequestModel);

    @POST("/deviceAction")
    Observable<FullSyncResponseModel> getFullSyncData(@Body FullSyncRequestModel fullSyncRequestModel);

    @POST("/deviceAction")
    Observable<ForgotPassResponseModel> getForgotPass(@Body ForgotPassRequestModel forgotPassRequestModel);


    @POST("")
    Observable<SuccessModel> getTokenRegistrationModel(@Body TokenRegistrationModel registrationModel);

    @GET("/maps/api/directions/json")
    Observable<DirectionResults> getDirections(@Query("origin") String origin, @Query("destination") String destination);


    @POST("/deviceAction")
    Call<ResponseBody> quickSync(@Body MultipartBody filePart);

}