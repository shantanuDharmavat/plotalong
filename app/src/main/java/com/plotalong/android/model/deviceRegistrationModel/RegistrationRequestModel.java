package com.plotalong.android.model.deviceRegistrationModel;

import com.plotalong.android.model.commonModel.SignatureModel;


/**
 * Created by kbhakade on 25/5/17.
 */

public class RegistrationRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private RegistrationRequestDataModel data;

    public RegistrationRequestModel() {
    }

    public RegistrationRequestModel(String type, String action, SignatureModel signature, RegistrationRequestDataModel data) {
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

    public RegistrationRequestDataModel getData() {
        return data;
    }

    public void setData(RegistrationRequestDataModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegistrationRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                '}';
    }
}
