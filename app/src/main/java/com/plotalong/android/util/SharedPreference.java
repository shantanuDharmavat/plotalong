package com.plotalong.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by shantanu on 19/5/17.
 */

public class SharedPreference {
    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";
    private static SharedPreference mInstance;
    private Context context;
    private String TAG = SharedPreference.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    private SharedPreference(Context context) {
        this.context = context;
    }

    public static synchronized SharedPreference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreference(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveDeviceToken(String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public String getDeviceToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_TOKEN, null);
    }

    public void saveStringSharedPreference(String name, String value) {
        Log.e(TAG, "saveStringSharedPreference: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public void saveIntSharedPreference(String name, int value) {
        Log.e(TAG, "saveIntSharedPreference: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }


    public String getStringSharedPreference(String name) {
        Log.e(TAG, "getStringSharedPreference: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, null);
    }


    public int getIntSharedPreference(String name) {
        Log.e(TAG, "getIntSharedPreference: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(name, 0);
    }
}