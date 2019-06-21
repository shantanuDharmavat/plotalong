package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 14/11/17.
 */

public class CfgFeatureDataModel {
    @SerializedName("cpf_id")
    @Expose

    private int cpf_id;
    @SerializedName("cpf_feat_id")
    @Expose

    private int cpf_feat_id;
    @SerializedName("cpf_proj_id")
    @Expose

    private int cpf_proj_id;
    @SerializedName("cpf_phas_id")
    @Expose

    private int cpf_phas_id;
    @SerializedName("cpf_plot_id")
    @Expose

    private int cpf_plot_id;
    @SerializedName("cpf_devl_id")
    @Expose

    private int cpf_devl_id;
    @SerializedName("cpf_isactive")
    @Expose

    private int cpf_isactive;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;



    public CfgFeatureDataModel() {
    }

    public CfgFeatureDataModel(int cpf_id, int cpf_feat_id, int cpf_proj_id, int cpf_phas_id, int cpf_plot_id, int cpf_devl_id, int cpf_isactive, String created_at, String updated_at, String deleted_at) {
        this.cpf_id = cpf_id;
        this.cpf_feat_id = cpf_feat_id;
        this.cpf_proj_id = cpf_proj_id;
        this.cpf_phas_id = cpf_phas_id;
        this.cpf_plot_id = cpf_plot_id;
        this.cpf_devl_id = cpf_devl_id;
        this.cpf_isactive = cpf_isactive;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public int getCpf_id() {
        return cpf_id;
    }

    public void setCpf_id(int cpf_id) {
        this.cpf_id = cpf_id;
    }

    public int getCpf_feat_id() {
        return cpf_feat_id;
    }

    public void setCpf_feat_id(int cpf_feat_id) {
        this.cpf_feat_id = cpf_feat_id;
    }

    public int getCpf_proj_id() {
        return cpf_proj_id;
    }

    public void setCpf_proj_id(int cpf_proj_id) {
        this.cpf_proj_id = cpf_proj_id;
    }

    public int getCpf_phas_id() {
        return cpf_phas_id;
    }

    public void setCpf_phas_id(int cpf_phas_id) {
        this.cpf_phas_id = cpf_phas_id;
    }

    public int getCpf_plot_id() {
        return cpf_plot_id;
    }

    public void setCpf_plot_id(int cpf_plot_id) {
        this.cpf_plot_id = cpf_plot_id;
    }

    public int getCpf_devl_id() {
        return cpf_devl_id;
    }

    public void setCpf_devl_id(int cpf_devl_id) {
        this.cpf_devl_id = cpf_devl_id;
    }

    public int getCpf_isactive() {
        return cpf_isactive;
    }

    public void setCpf_isactive(int cpf_isactive) {
        this.cpf_isactive = cpf_isactive;
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

    @Override
    public String toString() {
        return "CfgFeatureDataModel{" +
                "cpf_id=" + cpf_id +
                ", cpf_feat_id=" + cpf_feat_id +
                ", cpf_proj_id=" + cpf_proj_id +
                ", cpf_phas_id=" + cpf_phas_id +
                ", cpf_plot_id=" + cpf_plot_id +
                ", cpf_devl_id=" + cpf_devl_id +
                ", cpf_isactive=" + cpf_isactive +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
