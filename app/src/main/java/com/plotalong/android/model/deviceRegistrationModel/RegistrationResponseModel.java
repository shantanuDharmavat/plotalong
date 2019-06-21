package com.plotalong.android.model.deviceRegistrationModel;

/**
 * Created by kbhakade on 26/5/17.
 */

public class RegistrationResponseModel {
    private UserModel data;
    private UserModel request;
    private String type;
    private String status;
    private Boolean deviceIsActive;
    private Boolean userIsActive;
    private String message;

    public RegistrationResponseModel() {
    }

    public RegistrationResponseModel(UserModel data, UserModel request, String type, String status, String message, Boolean deviceIsActive, Boolean userIsActive) {
        this.data = data;
        this.request = request;
        this.type = type;
        this.status = status;
        this.message = message;
        this.deviceIsActive = deviceIsActive;
        this.userIsActive = userIsActive;
    }

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public UserModel getRequest() {
        return request;
    }

    public void setRequest(UserModel request) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public String toString() {
        return "RegistrationResponseModel{" +
                "data=" + data +
                ", request=" + request +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", deviceIsActive=" + deviceIsActive +
                ", userIsActive=" + userIsActive +
                '}';
    }
}
