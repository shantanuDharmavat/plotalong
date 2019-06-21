package com.plotalong.android.model.deviceRegistrationModel;

/**
 * Created by kbhakade on 29/5/17.
 */

public class RegistrationRequestDataModel {
    private String user_email;
    private String user_password;
    private String devl_passphrase;

    public RegistrationRequestDataModel() {
    }

    public RegistrationRequestDataModel(String user_email, String user_password, String devl_passphrase) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.devl_passphrase = devl_passphrase;
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

    public String getDevl_passphrase() {
        return devl_passphrase;
    }

    public void setDevl_passphrase(String devl_passphrase) {
        this.devl_passphrase = devl_passphrase;
    }

    @Override
    public String toString() {
        return "RegistrationRequestDataModel{" +
                "user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", devl_passphrase='" + devl_passphrase + '\'' +
                '}';
    }
}
