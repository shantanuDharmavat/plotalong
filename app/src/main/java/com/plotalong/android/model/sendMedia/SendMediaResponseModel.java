package com.plotalong.android.model.sendMedia;

/**
 * Created by kbhakade on 23/11/17.
 */

public class SendMediaResponseModel {
    int status;
    String message;

    public SendMediaResponseModel() {
    }

    public SendMediaResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendMediaResponseModel{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
