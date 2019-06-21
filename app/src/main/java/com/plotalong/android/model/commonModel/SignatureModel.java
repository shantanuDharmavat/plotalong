package com.plotalong.android.model.commonModel;

/**
 * Created by kbhakade on 24/5/17.
 */

public class SignatureModel {
    private String user_id;
    private String devl_id;
    private String timestamp;
    private String device_key;
    private String location;
    private String role_license;

    public SignatureModel() {
    }

    public SignatureModel(String user_id, String devl_id, String timestamp, String device_key, String location, String role_license) {
        this.user_id = user_id;
        this.devl_id = devl_id;
        this.timestamp = timestamp;
        this.device_key = device_key;
        this.location = location;
        this.role_license = role_license;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDevl_id() {
        return devl_id;
    }

    public void setDevl_id(String devl_id) {
        this.devl_id = devl_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevice_key() {
        return device_key;
    }

    public void setDevice_key(String device_key) {
        this.device_key = device_key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole_license() {
        return role_license;
    }

    public void setRole_license(String role_license) {
        this.role_license = role_license;
    }

    @Override
    public String toString() {
        return "SignatureModel{" +
                "user_id='" + user_id + '\'' +
                ", devl_id='" + devl_id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", device_key='" + device_key + '\'' +
                ", location='" + location + '\'' +
                ", role_license='" + role_license + '\'' +
                '}';
    }
}
