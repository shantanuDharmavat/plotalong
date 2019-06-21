package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 31/5/17.
 */

public class QuickSyncResponseModel {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deviceIsActive")
    @Expose
    private String deviceIsActive;
    @SerializedName("userIsActive")
    @Expose
    private String userIsActive;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private QuickSyncModel data;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceIsActive() {
        return deviceIsActive;
    }

    public void setDeviceIsActive(String deviceIsActive) {
        this.deviceIsActive = deviceIsActive;
    }

    public String getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(String userIsActive) {
        this.userIsActive = userIsActive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QuickSyncModel getData() {
        return data;
    }

    public void setData(QuickSyncModel data) {
        this.data = data;
    }
}
