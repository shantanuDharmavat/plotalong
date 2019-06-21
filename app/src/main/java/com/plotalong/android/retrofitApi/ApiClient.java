package com.plotalong.android.retrofitApi;

import android.util.Log;

import com.plotalong.android.util.GlobalConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kbhakade on 29/5/17.
 */

public class ApiClient {
    private static String TAG = ApiClient.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(ApiInterface.baseUrl).addConverterFactory(GsonConverterFactory.create());

    public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {
        Log.e(TAG, "createRetrofitService: ");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    public static ApiInterface createService(Class<ApiInterface> serviceClass) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = builder.client(httpClient.build())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceClass);
    }
}