package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 13/11/17.
 */

public class ProjectDataModel {


    @SerializedName("proj_id")
    @Expose

    private int proj_id;
    @SerializedName("proj_key")
    @Expose

    private String proj_key;
    @SerializedName("proj_name")
    @Expose

    private String proj_name;
    @SerializedName("proj_desc")
    @Expose

    private String proj_desc;
    @SerializedName("proj_type_id")
    @Expose

    private String proj_type_id;
    @SerializedName("proj_developer_id")
    @Expose

    private int proj_developer_id;
    @SerializedName("proj_owner_id")
    @Expose

    private int proj_owner_id;
    @SerializedName("proj_uom_id")
    @Expose

    private int proj_uom_id;
    @SerializedName("created_by")
    @Expose

    private String created_by;
    @SerializedName("updated_by")
    @Expose

    private String updated_by;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("proj_logo")
    @Expose

    private String proj_logo;
    @SerializedName("proj_coords")
    @Expose

    private String proj_coords;
    @SerializedName("proj_rera_code")
    @Expose

    private String proj_rera_code;
    @SerializedName("proj_rera_renew_date")
    @Expose

    private String proj_rera_renew_date;
    @SerializedName("proj_launch_date")
    @Expose

    private String proj_launch_date;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;


    public ProjectDataModel() {
    }

    public ProjectDataModel(int proj_id, String proj_key, String proj_name, String proj_desc, String proj_type_id, int proj_developer_id, int proj_owner_id, int proj_uom_id, String created_by, String updated_by, String created_at, String updated_at, String proj_logo, String proj_coords, String proj_rera_code, String proj_rera_renew_date, String proj_launch_date, String deleted_at) {
        this.proj_id = proj_id;
        this.proj_key = proj_key;
        this.proj_name = proj_name;
        this.proj_desc = proj_desc;
        this.proj_type_id = proj_type_id;
        this.proj_developer_id = proj_developer_id;
        this.proj_owner_id = proj_owner_id;
        this.proj_uom_id = proj_uom_id;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.proj_logo = proj_logo;
        this.proj_coords = proj_coords;
        this.proj_rera_code = proj_rera_code;
        this.proj_rera_renew_date = proj_rera_renew_date;
        this.proj_launch_date = proj_launch_date;
        this.deleted_at = deleted_at;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public String getProj_key() {
        return proj_key;
    }

    public void setProj_key(String proj_key) {
        this.proj_key = proj_key;
    }

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getProj_desc() {
        return proj_desc;
    }

    public void setProj_desc(String proj_desc) {
        this.proj_desc = proj_desc;
    }

    public String getProj_type_id() {
        return proj_type_id;
    }

    public void setProj_type_id(String proj_type_id) {
        this.proj_type_id = proj_type_id;
    }

    public int getProj_developer_id() {
        return proj_developer_id;
    }

    public void setProj_developer_id(int proj_developer_id) {
        this.proj_developer_id = proj_developer_id;
    }

    public int getProj_owner_id() {
        return proj_owner_id;
    }

    public void setProj_owner_id(int proj_owner_id) {
        this.proj_owner_id = proj_owner_id;
    }

    public int getProj_uom_id() {
        return proj_uom_id;
    }

    public void setProj_uom_id(int proj_uom_id) {
        this.proj_uom_id = proj_uom_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
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

    public String getProj_logo() {
        return proj_logo;
    }

    public void setProj_logo(String proj_logo) {
        this.proj_logo = proj_logo;
    }

    public String getProj_coords() {
        return proj_coords;
    }

    public void setProj_coords(String proj_coords) {
        this.proj_coords = proj_coords;
    }

    public String getProj_rera_code() {
        return proj_rera_code;
    }

    public void setProj_rera_code(String proj_rera_code) {
        this.proj_rera_code = proj_rera_code;
    }

    public String getProj_rera_renew_date() {
        return proj_rera_renew_date;
    }

    public void setProj_rera_renew_date(String proj_rera_renew_date) {
        this.proj_rera_renew_date = proj_rera_renew_date;
    }

    public String getProj_launch_date() {
        return proj_launch_date;
    }

    public void setProj_launch_date(String proj_launch_date) {
        this.proj_launch_date = proj_launch_date;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }


    @Override
    public String toString() {
        return "ProjectDataModel{" +
                "proj_id=" + proj_id +
                ", proj_key='" + proj_key + '\'' +
                ", proj_name='" + proj_name + '\'' +
                ", proj_desc='" + proj_desc + '\'' +
                ", proj_type_id='" + proj_type_id + '\'' +
                ", proj_developer_id=" + proj_developer_id +
                ", proj_owner_id=" + proj_owner_id +
                ", proj_uom_id=" + proj_uom_id +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", proj_logo='" + proj_logo + '\'' +
                ", proj_coords='" + proj_coords + '\'' +
                ", proj_rera_code='" + proj_rera_code + '\'' +
                ", proj_rera_renew_date='" + proj_rera_renew_date + '\'' +
                ", proj_launch_date='" + proj_launch_date + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
