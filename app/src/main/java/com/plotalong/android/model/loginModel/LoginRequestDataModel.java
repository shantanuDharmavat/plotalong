package com.plotalong.android.model.loginModel;

/**
 * Created by kbhakade on 6/7/17.
 */

public class LoginRequestDataModel {
    private String user_email;
    private String user_password;

    public LoginRequestDataModel() {
    }

    public LoginRequestDataModel(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "LoginRequestDataModel{" +
                "user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
