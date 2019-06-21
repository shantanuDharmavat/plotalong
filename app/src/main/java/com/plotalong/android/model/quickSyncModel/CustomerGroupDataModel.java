package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 11/9/17.
 */

public class CustomerGroupDataModel {
    @SerializedName("cgc_client_id")
    @Expose

    private int cgc_client_id;
    @SerializedName("cgc_id")
    @Expose

    private int cgc_id;
    @SerializedName("cgc_cust_id")
    @Expose

    private int cgc_cust_id;
    @SerializedName("cgc_group_id")
    @Expose

    private String cgc_group_id;
    @SerializedName("cgc_status")
    @Expose

    private String cgc_status;
    @SerializedName("cgc_member_type")
    @Expose

    private String cgc_member_type;
    @SerializedName("cgc_phas_id")
    @Expose

    private int cgc_phas_id;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;
    @SerializedName("cgc_sync_status")
    @Expose

    private String cgc_sync_status;


    public CustomerGroupDataModel() {
    }

    public CustomerGroupDataModel(int cgc_client_id, int cgc_id, int cgc_cust_id, String cgc_group_id, String cgc_status, String cgc_member_type, int cgc_phas_id, String created_at, String updated_at, String deleted_at, String cgc_sync_status) {
        this.cgc_client_id = cgc_client_id;
        this.cgc_id = cgc_id;
        this.cgc_cust_id = cgc_cust_id;
        this.cgc_group_id = cgc_group_id;
        this.cgc_status = cgc_status;
        this.cgc_member_type = cgc_member_type;
        this.cgc_phas_id = cgc_phas_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.cgc_sync_status = cgc_sync_status;
    }

    public int getCgc_client_id() {
        return cgc_client_id;
    }

    public void setCgc_client_id(int cgc_client_id) {
        this.cgc_client_id = cgc_client_id;
    }

    public int getCgc_id() {
        return cgc_id;
    }

    public void setCgc_id(int cgc_id) {
        this.cgc_id = cgc_id;
    }

    public int getCgc_cust_id() {
        return cgc_cust_id;
    }

    public void setCgc_cust_id(int cgc_cust_id) {
        this.cgc_cust_id = cgc_cust_id;
    }

    public String getCgc_group_id() {
        return cgc_group_id;
    }

    public void setCgc_group_id(String cgc_group_id) {
        this.cgc_group_id = cgc_group_id;
    }

    public String getCgc_status() {
        return cgc_status;
    }

    public void setCgc_status(String cgc_status) {
        this.cgc_status = cgc_status;
    }

    public String getCgc_member_type() {
        return cgc_member_type;
    }

    public void setCgc_member_type(String cgc_member_type) {
        this.cgc_member_type = cgc_member_type;
    }

    public int getCgc_phas_id() {
        return cgc_phas_id;
    }

    public void setCgc_phas_id(int cgc_phas_id) {
        this.cgc_phas_id = cgc_phas_id;
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

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getCgc_sync_status() {
        return cgc_sync_status;
    }

    public void setCgc_sync_status(String cgc_sync_status) {
        this.cgc_sync_status = cgc_sync_status;
    }

    @Override
    public String toString() {
        return "CustomerGroupDataModel{" +
                "cgc_client_id=" + cgc_client_id +
                ", cgc_id=" + cgc_id +
                ", cgc_cust_id=" + cgc_cust_id +
                ", cgc_group_id='" + cgc_group_id + '\'' +
                ", cgc_status='" + cgc_status + '\'' +
                ", cgc_member_type='" + cgc_member_type + '\'' +
                ", cgc_phas_id=" + cgc_phas_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", cgc_sync_status='" + cgc_sync_status + '\'' +
                '}';
    }
}
