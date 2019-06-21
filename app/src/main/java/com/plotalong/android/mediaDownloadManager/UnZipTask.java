package com.plotalong.android.mediaDownloadManager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.plotalong.android.listener.QuickSyncListener;
import com.plotalong.android.listener.UnzipListener;
import com.plotalong.android.util.FileHandlingUtil;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.dialogManager.ProgressDialogManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by kbhakade on 10/7/17.
 */

public class UnZipTask extends AsyncTask<File, Integer, Boolean> {
    private static final String TAG = UnZipTask.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final String fileName;
    private final int zipSize;
    private final UnzipListener unzipListener;
    private boolean result;
    private int count = 0;

    public UnZipTask(UnzipListener unzipListener, Context context, String fileName, int zipSize) {
        Log.e(TAG, "UnZipTask: ");
        this.context = context;
        this.fileName = fileName;
        this.zipSize = zipSize;
        this.unzipListener = unzipListener;
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute: ");
        super.onPreExecute();
        ProgressDialogManager.getUnzippingProgressDialog(context, zipSize);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Boolean doInBackground(File... params) {
        Log.e(TAG, "doInBackground: ");
        result = unzip(params[0]);
        return result;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.e(TAG, "onProgressUpdate: ");
        ProgressDialogManager.setUnzippingProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.e(TAG, "onPostExecute: ");
        ProgressDialogManager.dismissUnzippingDialog();
        if (result) {
            unzipListener.onUnzipSuccess();
        }else {
            unzipListener.onUnzipFail("Something wrong with unzip file");
        }
    }

    /**
     * Unzip file from refer file to destination place
     *
     * @param filePath
     * @return boolean
     */
    private boolean unzip(File filePath) {
        Log.e(TAG, "unzip: ");
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry;
            String fileNameWithoutExtension = FileHandlingUtil.getFileNameWithoutExtension(fileName);
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                count++;
                publishProgress(count);
                String name = zipEntry.getName();
                File outputFile = new File(GlobalConstant.PROJECT_FOLDER_PATH + "/" + fileNameWithoutExtension + "/" + name);
                String outputPath = outputFile.getCanonicalPath();
                name = outputPath.substring(outputPath.lastIndexOf("/") + 1);
                outputPath = outputPath.substring(0, outputPath.lastIndexOf("/"));
                File outputDir = new File(outputPath);
                boolean isDirectoryCreated = outputDir.mkdirs();
                Log.e(TAG, "unzip: isDirectoryCreated = " + isDirectoryCreated);
                outputFile = new File(outputPath, name);
                boolean isFileCreated = outputFile.createNewFile();
                Log.e(TAG, "unzip: isFileCreated = " + isFileCreated);
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                final int BUFFER_SIZE = 2048;
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, BUFFER_SIZE);
                int count;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((count = zipInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    bufferedOutputStream.write(buffer, 0, count);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
            zipInputStream.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}