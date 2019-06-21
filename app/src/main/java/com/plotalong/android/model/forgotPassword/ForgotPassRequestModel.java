package com.plotalong.android.model.forgotPassword;

import com.plotalong.android.model.commonModel.SignatureModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 23/9/17.
 */

public class ForgotPassRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private ForgotPassRequestDataModel data;

    public ForgotPassRequestModel() {
    }

    public ForgotPassRequestModel(String type, String action, SignatureModel signature, ForgotPassRequestDataModel data) {
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

    public ForgotPassRequestDataModel getData() {
        return data;
    }

    public void setData(ForgotPassRequestDataModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ForgotPassRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                '}';
    }
}
