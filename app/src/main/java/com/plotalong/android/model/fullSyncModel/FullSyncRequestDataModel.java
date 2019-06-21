package com.plotalong.android.model.fullSyncModel;

/**
 * Created by kbhakade on 19/7/17.
 */

public class FullSyncRequestDataModel {
    private String devl_id;

    public FullSyncRequestDataModel() {
    }

    public FullSyncRequestDataModel(String devl_id) {
        this.devl_id = devl_id;
    }

    public String getDevl_id() {
        return devl_id;
    }

    public void setDevl_id(String devl_id) {
        this.devl_id = devl_id;
    }

    @Override
    public String toString() {
        return "FullSyncRequestDataModel{" +
                "devl_id='" + devl_id + '\'' +
                '}';
    }
}
