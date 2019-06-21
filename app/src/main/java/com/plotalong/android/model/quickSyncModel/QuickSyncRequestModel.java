package com.plotalong.android.model.quickSyncModel;

import com.plotalong.android.model.commonModel.SignatureModel;

/**
 * Created by kbhakade on 31/5/17.
 */

public class QuickSyncRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private QuickSyncRequestDataModel data;
    private String devl_id;

    public QuickSyncRequestModel() {
    }

    public QuickSyncRequestModel(String type, String action, SignatureModel signature, QuickSyncRequestDataModel data, String devl_id) {
        this.type = type;
        this.action = action;
        this.signature = signature;
        this.data = data;
        this.devl_id = devl_id;
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

    public QuickSyncRequestDataModel getData() {
        return data;
    }

    public void setData(QuickSyncRequestDataModel data) {
        this.data = data;
    }

    public String getDevl_id() {
        return devl_id;
    }

    public void setDevl_id(String devl_id) {
        this.devl_id = devl_id;
    }

    @Override
    public String toString() {
        return "QuickSyncRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                ", devl_id='" + devl_id + '\'' +
                '}';
    }
}
