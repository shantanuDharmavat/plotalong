package com.plotalong.android.model.forgotPassword;

/**
 * Created by kbhakade on 23/9/17.
 */

public class ForgotPassRequestDataModel {
    private String user_email;

    public ForgotPassRequestDataModel() {
    }

    public ForgotPassRequestDataModel(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "ForgotPassRequestDataModel{" +
                "user_email='" + user_email + '\'' +
                '}';
    }
}
