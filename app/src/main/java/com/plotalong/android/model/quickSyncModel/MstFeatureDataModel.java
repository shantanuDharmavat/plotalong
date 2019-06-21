package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 14/11/17.
 */

public class MstFeatureDataModel {
    @SerializedName("feat_id")
    @Expose

    private int feat_id;
    @SerializedName("feat_name")
    @Expose

    private String feat_name;
    @SerializedName("feat_description")
    @Expose

    private String feat_description;
    @SerializedName("feat_isactive")
    @Expose

    private int feat_isactive;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("created_by")
    @Expose

    private String created_by;
    @SerializedName("feat_icon")
    @Expose

    private String feat_icon;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;


    public MstFeatureDataModel() {
    }

    public MstFeatureDataModel(int feat_id, String feat_name, String feat_description, int feat_isactive, String created_at, String updated_at, String created_by, String feat_icon, String deleted_at) {
        this.feat_id = feat_id;
        this.feat_name = feat_name;
        this.feat_description = feat_description;
        this.feat_isactive = feat_isactive;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.feat_icon = feat_icon;
        this.deleted_at = deleted_at;
    }

    public int getFeat_id() {
        return feat_id;
    }

    public void setFeat_id(int feat_id) {
        this.feat_id = feat_id;
    }

    public String getFeat_name() {
        return feat_name;
    }

    public void setFeat_name(String feat_name) {
        this.feat_name = feat_name;
    }

    public String getFeat_description() {
        return feat_description;
    }

    public void setFeat_description(String feat_description) {
        this.feat_description = feat_description;
    }

    public int getFeat_isactive() {
        return feat_isactive;
    }

    public void setFeat_isactive(int feat_isactive) {
        this.feat_isactive = feat_isactive;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getFeat_icon() {
        return feat_icon;
    }

    public void setFeat_icon(String feat_icon) {
        this.feat_icon = feat_icon;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        return "MstFeatureDataModel{" +
                "feat_id=" + feat_id +
                ", feat_name='" + feat_name + '\'' +
                ", feat_description='" + feat_description + '\'' +
                ", feat_isactive=" + feat_isactive +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_by='" + created_by + '\'' +
                ", feat_icon='" + feat_icon + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
