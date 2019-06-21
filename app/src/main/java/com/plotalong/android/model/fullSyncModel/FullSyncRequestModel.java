package com.plotalong.android.model.fullSyncModel;

import com.plotalong.android.model.commonModel.SignatureModel;

/**
 * Created by kbhakade on 19/7/17.
 */

public class FullSyncRequestModel {
    private String type;
    private String action;
    private SignatureModel signature;
    private FullSyncRequestDataModel data;
    private String devl_id;

    public FullSyncRequestModel() {
    }

    public FullSyncRequestModel(String type, String action, SignatureModel signature, FullSyncRequestDataModel data, String devl_id) {
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

    public FullSyncRequestDataModel getData() {
        return data;
    }

    public void setData(FullSyncRequestDataModel data) {
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
        return "FullSyncRequestModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", signature=" + signature +
                ", data=" + data +
                ", devl_id='" + devl_id + '\'' +
                '}';
    }
}
