package com.plotalong.android.model.sendMedia;

import com.plotalong.android.model.commonModel.SignatureModel;
import com.plotalong.android.model.loginModel.LoginRequestDataModel;

/**
 * Created by kbhakade on 23/11/17.
 */

public class SendMediaRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private LoginRequestDataModel data;


    public SendMediaRequestModel() {
    }

    public SendMediaRequestModel(String type, String action, SignatureModel signature, LoginRequestDataModel data) {
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
        return "SendMediaRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                '}';
    }
}
