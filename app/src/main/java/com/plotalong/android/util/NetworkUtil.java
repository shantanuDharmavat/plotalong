package com.plotalong.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by kbhakade on 11/7/17.
 */

public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public static boolean isNetworkAvailable(Context context) {
        Log.e(TAG, "isNetworkAvailable: ");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
