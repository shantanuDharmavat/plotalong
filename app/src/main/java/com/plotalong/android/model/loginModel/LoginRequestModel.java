package com.plotalong.android.model.loginModel;

import com.plotalong.android.model.commonModel.SignatureModel;

/**
 * Created by kbhakade on 6/7/17.
 */

public class LoginRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private LoginRequestDataModel data;

    public LoginRequestModel() {
    }

    public LoginRequestModel(String type, String action, SignatureModel signature, LoginRequestDataModel data) {
        this.type = type;
        this.action = action;
        this.signature = signature;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SignatureModel getSignature() {
        return signature;
    }

    public void setSignature(SignatureModel signature) {
        this.signature = signature;
    }

    public LoginRequestDataModel getData() {
        return data;
    }

    public void setData(LoginRequestDataModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                '}';
    }
}