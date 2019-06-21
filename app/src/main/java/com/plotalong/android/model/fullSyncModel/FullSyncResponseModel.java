package com.plotalong.android.model.fullSyncModel;

/**
 * Created by kbhakade on 19/7/17.
 */

public class FullSyncResponseModel {
    private String type;
    private String status;
    private String deviceIsActive;
    private String userIsActive;
    private String message;
    private FullSyncDataModel data;
    private FullSyncRequestModel request;

    public FullSyncResponseModel() {
    }

    public FullSyncResponseModel(String type, String status, String deviceIsActive, String userIsActive, String message, FullSyncDataModel data, FullSyncRequestModel request) {
        this.type = type;
        this.status = status;
        this.deviceIsActive = deviceIsActive;
        this.userIsActive = userIsActive;
        this.message = message;
        this.data = data;
        this.request = request;
    }

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

    public FullSyncDataModel getData() {
        return data;
    }

    public void setData(FullSyncDataModel data) {
        this.data = data;
    }

    public FullSyncRequestModel getRequest() {
        return request;
    }

    public void setRequest(FullSyncRequestModel request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "FullSyncResponseModel{" +
                "type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", deviceIsActive='" + deviceIsActive + '\'' +
                ", userIsActive='" + userIsActive + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", request=" + request +
                '}';
    }
}