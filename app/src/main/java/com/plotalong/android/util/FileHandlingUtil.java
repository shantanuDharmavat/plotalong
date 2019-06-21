package com.plotalong.android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by kbhakade on 10/7/17.
 */

public class FileHandlingUtil {
    private static final String TAG = FileHandlingUtil.class.getSimpleName();

    public static File createDirectory(String folderName) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/" + folderName);
        if (!direct.exists()) {
            boolean isCreated = direct.mkdirs();
            Log.e(TAG, "createDirectory: " + isCreated);
        }
        return direct;
    }

    public static File createDirectoryOfPath(String path) {
        File direct = new File(path);
        if (!direct.exists()) {
            boolean isCreated = direct.mkdirs();
            Log.e(TAG, "createDirectory: " + isCreated);
        }
        return direct;
    }


    public static File createZipFile(String folderName, String fileName) {
        return new File(createDirectory(folderName), fileName);
    }

    public static File createGenericFile(String folderName, String imageName) {
        return new File(createDirectory(folderName), imageName);
    }

    /***
     * Checks if external storage is available for read and write
     *
     * @return
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /***
     * Checks if external storage is available to at least read
     *
     * @return
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public static String getFileNameFromUrl(String url) {
        Log.e(TAG, "getFileNameFromUrl: ");
        String fileName = null;
        try {
            URI uri = new URI(url);
            URL localUrl = uri.toURL();
            File file = new File(localUrl.getFile());
            fileName = file.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static void viewContent(Context context, File file) {
        Log.e(TAG, "viewContent: ");
//        File filepath = Environment.getExternalStorageDirectory();
//        File dir = new File(filepath.getAbsolutePath() + "/NewPlotAlong/" + "/User/");
//        File file = new File(dir, "b.pdf");
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
        String type = map.getMimeTypeFromExtension(ext);

        if (type == null) {
            type = "*/*";
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.fromFile(file);
        intent.setDataAndType(data, type);
        context.startActivity(intent);
    }

    public static String getFileNameWithoutExtension(String fileName) {
        Log.e(TAG, "getFileNameWithoutExtension: ");
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        return fileName;
    }

    public static void deleteFolder(String folderName) {
        Log.e(TAG, "deleteFolder: ");
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + folderName);
//        try {
//            FileUtils.deleteDirectory(dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void deleteFolderContent(String filePath) {
        Log.e(TAG, "deleteFolder: ");
        File dir = new File(filePath);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
            Log.e(TAG, "deleteFolderByPath: Folder deleted successfully !");
        }
    }


}