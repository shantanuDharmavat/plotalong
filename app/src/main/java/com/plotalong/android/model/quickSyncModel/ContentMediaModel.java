package com.plotalong.android.model.quickSyncModel;

import okhttp3.MultipartBody;

/**
 * Created by kbhakade on 5/12/17.
 */

public class ContentMediaModel {
    private String path;
    private MultipartBody.Part file;

    public ContentMediaModel() {
    }

    public ContentMediaModel(String path, MultipartBody.Part file) {
        this.path = path;
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ContentMediaModel{" +
                "path='" + path + '\'' +
                ", file=" + file +
                '}';
    }
}
