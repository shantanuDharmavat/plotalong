package com.plotalong.android.model.quickSyncModel;

/**
 * Created by kbhakade on 1/6/17.
 */

public class PlotFeatureModel {
    private int cpf_id;
    private int cpf_feat_id;
    private int cpf_proj_id;
    private int cpf_phas_id;
    private int cpf_plot_id;
    private int cpf_devl_id;
    private int cpf_isactive;
    private int feat_id;
    private String feat_name;
    private String feat_description;
    private int feat_isactive;
    private String created_at;
    private String created_by;
    private String feat_icon;

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

    @Override
    public String toString() {
        return "PlotFeatureModel{" +
                "cpf_id=" + cpf_id +
                ", cpf_feat_id=" + cpf_feat_id +
                ", cpf_proj_id=" + cpf_proj_id +
                ", cpf_phas_id=" + cpf_phas_id +
                ", cpf_plot_id=" + cpf_plot_id +
                ", cpf_devl_id=" + cpf_devl_id +
                ", cpf_isactive=" + cpf_isactive +
                ", feat_id=" + feat_id +
                ", feat_name='" + feat_name + '\'' +
                ", feat_description='" + feat_description + '\'' +
                ", feat_isactive=" + feat_isactive +
                ", created_at='" + created_at + '\'' +
                ", created_by='" + created_by + '\'' +
                ", feat_icon='" + feat_icon + '\'' +
                '}';
    }
}
