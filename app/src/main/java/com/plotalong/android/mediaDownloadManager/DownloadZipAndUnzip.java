package com.plotalong.android.mediaDownloadManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.plotalong.android.listener.QuickSyncListener;
import com.plotalong.android.listener.UnzipListener;
import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.dialogManager.ProgressDialogManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipFile;

/**
 * Created by kbhakade on 10/7/17.
 */

public class DownloadZipAndUnzip extends AsyncTask<String, String, Boolean> {
    private static final String TAG = DownloadZipAndUnzip.class.getSimpleName();
    private final Context context;
    private final String folderName;
    private final String fileName;
    private final UnzipListener unzipListener;
    private File filePath;

    public DownloadZipAndUnzip(UnzipListener unzipListener, Context context, String folderName, String fileName) {
        Log.e(TAG, "DownloadZipAndUnzip: ");
        this.context = context;
        this.folderName = folderName;
        this.fileName = fileName;
        this.unzipListener = unzipListener;
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute: ");
        super.onPreExecute();
        ProgressDialogManager.getDownloadProgressDialog(context);
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        Log.e(TAG, "doInBackground: ");
        int count;
        try {
            URL url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            int lengthOfFile = urlConnection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());

            filePath = FileHandlingUtil.createZipFile(folderName, fileName);
            OutputStream output = new FileOutputStream(filePath);

            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                output.write(data, 0, count);
            }
            output.close();
            input.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onProgressUpdate(String... progress) {
        Log.e(TAG, "onProgressUpdate: " + progress[0]);
        ProgressDialogManager.setDownloadingProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.e(TAG, "onPostExecute: ");
        ProgressDialogManager.dismissDownloadDialog();
        if (result) {
            try {
                ZipFile zip = new ZipFile(filePath);
                UnZipTask unZipTask = new UnZipTask(unzipListener,context, fileName, zip.size());
                unZipTask.execute(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "onPostExecute: Result is false");
        }
    }
}