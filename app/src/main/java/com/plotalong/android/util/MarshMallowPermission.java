package com.plotalong.android.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;

/**
 * Created by kbhakade on 12/7/17.
 */
public class MarshMallowPermission {
    public static void requestStoragePermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MarshmallowIntentId.WRITE_EXTERNAL_STORAGE_PERMISSION);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MarshmallowIntentId.WRITE_EXTERNAL_STORAGE_PERMISSION);
        }
    }


    public static void requestCameraPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MarshmallowIntentId.CAMERA);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MarshmallowIntentId.CAMERA);
        }
    }



    public static void requestReadSMSPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_SMS)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_SMS}, MarshmallowIntentId.READ_SMS_INTENT_ID);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_SMS}, MarshmallowIntentId.READ_SMS_INTENT_ID);
        }
    }

    public static void requestLocationPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION )) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, MarshmallowIntentId.ACCESS_FINE_LOCATION_INTENT_ID);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, MarshmallowIntentId.ACCESS_FINE_LOCATION_INTENT_ID);
        }
    }

    public static void requestContactsPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, MarshmallowIntentId.ACCESS_CONTACTS_INTENT_ID);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, MarshmallowIntentId.ACCESS_CONTACTS_INTENT_ID);
        }
    }


    public static void requestRecordAudioPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.RECORD_AUDIO)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, MarshmallowIntentId.RECORD_AUDIO_INTENT_ID);
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, MarshmallowIntentId.RECORD_AUDIO_INTENT_ID);
        }
    }

}