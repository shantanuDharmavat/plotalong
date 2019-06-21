package com.plotalong.android.model.syncMaster;

/**
 * Created by kbhakade on 17/11/17.
 */

public class SyncMasterDataModel {
    private int envl_id;
    private String json_string;
    private String lst;

    public SyncMasterDataModel() {
    }

    public SyncMasterDataModel(int envl_id, String json_string, String lst) {
        this.envl_id = envl_id;
        this.json_string = json_string;
        this.lst = lst;
    }

    public int getEnvl_id() {
        return envl_id;
    }

    public void setEnvl_id(int envl_id) {
        this.envl_id = envl_id;
    }

    public String getJson_string() {
        return json_string;
    }

    public void setJson_string(String json_string) {
        this.json_string = json_string;
    }

    public String getLst() {
        return lst;
    }

    public void setLst(String lst) {
        this.lst = lst;
    }

    @Override
    public String toString() {
        return "SyncMasterDataModel{" +
                "envl_id=" + envl_id +
                ", json_string='" + json_string + '\'' +
                ", lst='" + lst + '\'' +
                '}';
    }
}
