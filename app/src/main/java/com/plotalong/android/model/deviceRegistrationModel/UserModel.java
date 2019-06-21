package com.plotalong.android.model.deviceRegistrationModel;

/**
 * Created by kbhakade on 24/5/17.
 */

public class UserModel {
    private int user_id;
    private String user_email_id;
    private String user_first_name;
    private String user_last_name;
    private String user_mobile;
    private int devl_id;
    private String user_pic;
    private String devc_api_key;
    private String auth_api_key;
    private String role_license;

    public UserModel() {
    }

    public UserModel(int user_id, String user_email_id, String user_first_name, String user_last_name, String user_mobile, int devl_id, String user_pic, String devc_api_key, String auth_api_key, String role_license) {
        this.user_id = user_id;
        this.user_email_id = user_email_id;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_mobile = user_mobile;
        this.devl_id = devl_id;
        this.user_pic = user_pic;
        this.devc_api_key = devc_api_key;
        this.auth_api_key = auth_api_key;
        this.role_license = role_license;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_email_id() {
        return user_email_id;
    }

    public void setUser_email_id(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public int getDevl_id() {
        return devl_id;
    }

    public void setDevl_id(int devl_id) {
        this.devl_id = devl_id;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getDevc_api_key() {
        return devc_api_key;
    }

    public void setDevc_api_key(String devc_api_key) {
        this.devc_api_key = devc_api_key;
    }

    public String getAuth_api_key() {
        return auth_api_key;
    }

    public void setAuth_api_key(String auth_api_key) {
        this.auth_api_key = auth_api_key;
    }

    public String getRole_license() {
        return role_license;
    }

    public void setRole_license(String role_license) {
        this.role_license = role_license;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", user_email_id='" + user_email_id + '\'' +
                ", user_first_name='" + user_first_name + '\'' +
                ", user_last_name='" + user_last_name + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", devl_id=" + devl_id +
                ", user_pic='" + user_pic + '\'' +
                ", devc_api_key='" + devc_api_key + '\'' +
                ", auth_api_key='" + auth_api_key + '\'' +
                ", role_license='" + role_license + '\'' +
                '}';
    }
}
