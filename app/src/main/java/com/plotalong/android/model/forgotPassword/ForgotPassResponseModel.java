package com.plotalong.android.model.forgotPassword;

import java.util.ArrayList;

/**
 * Created by kbhakade on 23/9/17.
 */

public class ForgotPassResponseModel {
    private ForgotPassRequestDataModel data;
    private ForgotPassRequestDataModel request;
    private String type;
    private String status;
    private Boolean deviceIsActive;
    private Boolean userIsActive;
    private String message;

    public ForgotPassResponseModel() {
    }

    public ForgotPassResponseModel(ForgotPassRequestDataModel data, ForgotPassRequestDataModel request, String type, String status, Boolean deviceIsActive, Boolean userIsActive, String message) {
        this.data = data;
        this.request = request;
        this.type = type;
        this.status = status;
        this.deviceIsActive = deviceIsActive;
        this.userIsActive = userIsActive;
        this.message = message;
    }

    public ForgotPassRequestDataModel getData() {
        return data;
    }

    public void setData(ForgotPassRequestDataModel data) {
        this.data = data;
    }

    public ForgotPassRequestDataModel getRequest() {
        return request;
    }

    public void setRequest(ForgotPassRequestDataModel request) {
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

    public Boolean getDeviceIsActive() {
        return deviceIsActive;
    }

    public void setDeviceIsActive(Boolean deviceIsActive) {
        this.deviceIsActive = deviceIsActive;
    }

    public Boolean getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(Boolean userIsActive) {
        this.userIsActive = userIsActive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ForgotPassResponseModel{" +
                "data=" + data +
                ", request=" + request +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", deviceIsActive=" + deviceIsActive +
                ", userIsActive=" + userIsActive +
                ", message='" + message + '\'' +
                '}';
    }
}
