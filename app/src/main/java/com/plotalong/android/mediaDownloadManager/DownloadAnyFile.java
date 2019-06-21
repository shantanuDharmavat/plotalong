package com.plotalong.android.mediaDownloadManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.dialogManager.ProgressDialogManager;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kbhakade on 11/7/17.
 */

public class DownloadAnyFile extends AsyncTask<String, String, Boolean> {
    private static final String TAG = DownloadAnyFile.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final String destinationFilePath;
    private String folderName = null;

    public DownloadAnyFile(Context context, String destinationFilePath) {
        Log.e(TAG, "DownloadAnyFile: ");
        this.context = context;
        this.destinationFilePath = destinationFilePath;
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute: ");
        super.onPreExecute();
//        ProgressDialogManager.getDownloadProgressDialog(context);
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        Log.e(TAG, "doInBackground: ");
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.connect();
            int lengthOfFile = c.getContentLength();
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "Server returned HTTP " + c.getResponseCode() + " " + c.getResponseMessage());
            }
            FileOutputStream fos = new FileOutputStream(destinationFilePath);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1;
            long total = 0;
            while ((len1 = is.read(buffer)) != -1) {
                total += len1;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onProgressUpdate(String... progress) {
        Log.e(TAG, "onProgressUpdate: " + progress[0]);
//        ProgressDialogManager.setDownloadingProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.e(TAG, "onPostExecute: " + result);
        if (result) {
//            ProgressDialogManager.dismissProgressDialog();
        } else {
//            ProgressDialogManager.dismissProgressDialog();
            Log.e(TAG, "onPostExecute: Result is False");
        }
    }
}