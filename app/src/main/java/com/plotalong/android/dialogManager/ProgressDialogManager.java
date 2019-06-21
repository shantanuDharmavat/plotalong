package com.plotalong.android.dialogManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.plotalong.android.R;
import com.plotalong.android.util.GlobalConstant;

import dmax.dialog.SpotsDialog;

/**
 * Created by kbhakade on 29/5/17.
 */

public class ProgressDialogManager {
    private static ProgressDialog downloadProgressDialog;
    private static SpotsDialog progressDialog;
    private static ProgressDialog progressUnzipDialog;
    private static String TAG = ProgressDialogManager.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public static SpotsDialog getProgressDialog(Context context) {
        Log.e(TAG, "showProgressDialog: ");
        progressDialog = new SpotsDialog(context, R.style.CustomProgressDialog);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        return progressDialog;
    }

    public static void dismissProgressDialog() {
        Log.e(TAG, "dismissProgressDialog: ");
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public static ProgressDialog getDownloadProgressDialog(Context context) {
        Log.e(TAG, "getDownloadProgressDialog: ");
        downloadProgressDialog = new ProgressDialog(context);
        downloadProgressDialog.setMessage("Downloading, Please wait...");
        downloadProgressDialog.setIndeterminate(false);
        downloadProgressDialog.setMax(100);
        downloadProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        downloadProgressDialog.setCancelable(false);
        downloadProgressDialog.show();
        return downloadProgressDialog;
    }

    public static void setDownloadingProgress(String progress) {
        Log.e(TAG, "setDownloadingProgress: " + progress);
        downloadProgressDialog.setProgress(Integer.parseInt(progress));
    }

    public static void dismissDownloadDialog() {
        Log.e(TAG, "dismissDownloadDialog: ");
        downloadProgressDialog.dismiss();
    }


    public static ProgressDialog getUnzippingProgressDialog(Context context, int zipSize) {
        Log.e(TAG, "getUnzippingProgressDialog: " + zipSize);
        progressUnzipDialog = new ProgressDialog(context);
        progressUnzipDialog.setMessage("Getting ready, Please wait...");
        progressUnzipDialog.setIndeterminate(false);
        progressUnzipDialog.setMax(zipSize);
        progressUnzipDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressUnzipDialog.setCancelable(false);
        progressUnzipDialog.show();
        return progressUnzipDialog;
    }

    public static void setUnzippingProgress(int count) {
        Log.e(TAG, "setUnzippingProgress: " + count);
        progressUnzipDialog.setProgress(count);
    }

    public static void dismissUnzippingDialog() {
        Log.e(TAG, "dismissUnzippingDialog: ");
        progressUnzipDialog.dismiss();
    }
}