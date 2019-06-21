package com.plotalong.android.model.quickSyncModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 11/9/17.
 */

public class ProposalHeaderDataModel {
    @SerializedName("prop_client_id")
    @Expose

    private int prop_client_id;
    @SerializedName("prop_id")
    @Expose

    private int prop_id;
    @SerializedName("prop_spid")
    @Expose

    private int prop_spid;
    @SerializedName("prop_contact_id")
    @Expose

    private String prop_contact_id;
    @SerializedName("prop_date")
    @Expose

    private String prop_date;
    @SerializedName("prop_stage")
    @Expose

    private String prop_stage;
    @SerializedName("prop_status")
    @Expose

    private String prop_status;
    @SerializedName("prop_devl_id")
    @Expose

    private int prop_devl_id;
    @SerializedName("prop_proj_id")
    @Expose

    private int prop_proj_id;
    @SerializedName("prop_phas_id")
    @Expose

    private int prop_phas_id;
    @SerializedName("prop_prod_total")
    @Expose

    private String prop_prod_total;
    @SerializedName("prop_charge_total")
    @Expose

    private String prop_charge_total;
    @SerializedName("prop_disc_total")
    @Expose

    private String prop_disc_total;
    @SerializedName("prop_net")
    @Expose

    private String prop_net;
    @SerializedName("prop_validity")
    @Expose

    private int prop_validity;
    @SerializedName("prop_notes")
    @Expose

    private String prop_notes;
    @SerializedName("prop_template")
    @Expose

    private String prop_template;
    @SerializedName("prop_attachments")
    @Expose

    private String prop_attachments;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;
    @SerializedName("prop_inst_count")
    @Expose

    private int prop_inst_count;
    @SerializedName("prop_header_sync_status")
    @Expose

    private String prop_header_sync_status;


    public ProposalHeaderDataModel() {
    }

    public ProposalHeaderDataModel(int prop_client_id, int prop_id, int prop_spid, String prop_contact_id, String prop_date, String prop_stage, String prop_status, int prop_devl_id, int prop_proj_id, int prop_phas_id, String prop_prod_total, String prop_charge_total, String prop_disc_total, String prop_net, int prop_validity, String prop_notes, String prop_template, String prop_attachments, String created_at, String updated_at, String deleted_at, int prop_inst_count, String prop_header_sync_status) {
        this.prop_client_id = prop_client_id;
        this.prop_id = prop_id;
        this.prop_spid = prop_spid;
        this.prop_contact_id = prop_contact_id;
        this.prop_date = prop_date;
        this.prop_stage = prop_stage;
        this.prop_status = prop_status;
        this.prop_devl_id = prop_devl_id;
        this.prop_proj_id = prop_proj_id;
        this.prop_phas_id = prop_phas_id;
        this.prop_prod_total = prop_prod_total;
        this.prop_charge_total = prop_charge_total;
        this.prop_disc_total = prop_disc_total;
        this.prop_net = prop_net;
        this.prop_validity = prop_validity;
        this.prop_notes = prop_notes;
        this.prop_template = prop_template;
        this.prop_attachments = prop_attachments;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.prop_inst_count = prop_inst_count;
        this.prop_header_sync_status = prop_header_sync_status;
    }

    public int getProp_client_id() {
        return prop_client_id;
    }

    public void setProp_client_id(int prop_client_id) {
        this.prop_client_id = prop_client_id;
    }

    public int getProp_id() {
        return prop_id;
    }

    public void setProp_id(int prop_id) {
        this.prop_id = prop_id;
    }

    public int getProp_spid() {
        return prop_spid;
    }

    public void setProp_spid(int prop_spid) {
        this.prop_spid = prop_spid;
    }

    public String getProp_contact_id() {
        return prop_contact_id;
    }

    public void setProp_contact_id(String prop_contact_id) {
        this.prop_contact_id = prop_contact_id;
    }

    public String getProp_date() {
        return prop_date;
    }

    public void setProp_date(String prop_date) {
        this.prop_date = prop_date;
    }

    public String getProp_stage() {
        return prop_stage;
    }

    public void setProp_stage(String prop_stage) {
        this.prop_stage = prop_stage;
    }

    public String getProp_status() {
        return prop_status;
    }

    public void setProp_status(String prop_status) {
        this.prop_status = prop_status;
    }

    public int getProp_devl_id() {
        return prop_devl_id;
    }

    public void setProp_devl_id(int prop_devl_id) {
        this.prop_devl_id = prop_devl_id;
    }

    public int getProp_proj_id() {
        return prop_proj_id;
    }

    public void setProp_proj_id(int prop_proj_id) {
        this.prop_proj_id = prop_proj_id;
    }

    public int getProp_phas_id() {
        return prop_phas_id;
    }

    public void setProp_phas_id(int prop_phas_id) {
        this.prop_phas_id = prop_phas_id;
    }

    public String getProp_prod_total() {
        return prop_prod_total;
    }

    public void setProp_prod_total(String prop_prod_total) {
        this.prop_prod_total = prop_prod_total;
    }

    public String getProp_charge_total() {
        return prop_charge_total;
    }

    public void setProp_charge_total(String prop_charge_total) {
        this.prop_charge_total = prop_charge_total;
    }

    public String getProp_disc_total() {
        return prop_disc_total;
    }

    public void setProp_disc_total(String prop_disc_total) {
        this.prop_disc_total = prop_disc_total;
    }

    public String getProp_net() {
        return prop_net;
    }

    public void setProp_net(String prop_net) {
        this.prop_net = prop_net;
    }

    public int getProp_validity() {
        return prop_validity;
    }

    public void setProp_validity(int prop_validity) {
        this.prop_validity = prop_validity;
    }

    public String getProp_notes() {
        return prop_notes;
    }

    public void setProp_notes(String prop_notes) {
        this.prop_notes = prop_notes;
    }

    public String getProp_template() {
        return prop_template;
    }

    public void setProp_template(String prop_template) {
        this.prop_template = prop_template;
    }

    public String getProp_attachments() {
        return prop_attachments;
    }

    public void setProp_attachments(String prop_attachments) {
        this.prop_attachments = prop_attachments;
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

    public int getProp_inst_count() {
        return prop_inst_count;
    }

    public void setProp_inst_count(int prop_inst_count) {
        this.prop_inst_count = prop_inst_count;
    }

    public String getProp_header_sync_status() {
        return prop_header_sync_status;
    }

    public void setProp_header_sync_status(String prop_header_sync_status) {
        this.prop_header_sync_status = prop_header_sync_status;
    }

    @Override
    public String toString() {
        return "ProposalHeaderDataModel{" +
                "prop_client_id=" + prop_client_id +
                ", prop_id=" + prop_id +
                ", prop_spid=" + prop_spid +
                ", prop_contact_id='" + prop_contact_id + '\'' +
                ", prop_date='" + prop_date + '\'' +
                ", prop_stage='" + prop_stage + '\'' +
                ", prop_status='" + prop_status + '\'' +
                ", prop_devl_id=" + prop_devl_id +
                ", prop_proj_id=" + prop_proj_id +
                ", prop_phas_id=" + prop_phas_id +
                ", prop_prod_total='" + prop_prod_total + '\'' +
                ", prop_charge_total='" + prop_charge_total + '\'' +
                ", prop_disc_total='" + prop_disc_total + '\'' +
                ", prop_net='" + prop_net + '\'' +
                ", prop_validity=" + prop_validity +
                ", prop_notes='" + prop_notes + '\'' +
                ", prop_template='" + prop_template + '\'' +
                ", prop_attachments='" + prop_attachments + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", prop_inst_count=" + prop_inst_count +
                ", prop_header_sync_status='" + prop_header_sync_status + '\'' +
                '}';
    }
}
