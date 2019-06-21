package com.plotalong.android.model.quickSyncModel;

/**
 * Created by kbhakade on 1/6/17.
 */

public class TsaModel {
    private int tsa_id;
    private int tsa_devl_id;
    private int tsa_proj_id;
    private int tsa_phas_id;
    private int tsa_plot_id;
    private int tsa_spid;
    private long tsa_contact_id;
    private String tsa_actitype;
    private String tsa_comment;
    private int tsa_rating;
    private String created_at;
    private String created_by;
    private int tsa_acti_id;
    private String tsa_status;
    private String updated_at;

    public int getTsa_id() {
        return tsa_id;
    }

    public void setTsa_id(int tsa_id) {
        this.tsa_id = tsa_id;
    }

    public int getTsa_devl_id() {
        return tsa_devl_id;
    }

    public void setTsa_devl_id(int tsa_devl_id) {
        this.tsa_devl_id = tsa_devl_id;
    }

    public int getTsa_proj_id() {
        return tsa_proj_id;
    }

    public void setTsa_proj_id(int tsa_proj_id) {
        this.tsa_proj_id = tsa_proj_id;
    }

    public int getTsa_phas_id() {
        return tsa_phas_id;
    }

    public void setTsa_phas_id(int tsa_phas_id) {
        this.tsa_phas_id = tsa_phas_id;
    }

    public int getTsa_plot_id() {
        return tsa_plot_id;
    }

    public void setTsa_plot_id(int tsa_plot_id) {
        this.tsa_plot_id = tsa_plot_id;
    }

    public int getTsa_spid() {
        return tsa_spid;
    }

    public void setTsa_spid(int tsa_spid) {
        this.tsa_spid = tsa_spid;
    }

    public long getTsa_contact_id() {
        return tsa_contact_id;
    }

    public void setTsa_contact_id(long tsa_contact_id) {
        this.tsa_contact_id = tsa_contact_id;
    }

    public String getTsa_actitype() {
        return tsa_actitype;
    }

    public void setTsa_actitype(String tsa_actitype) {
        this.tsa_actitype = tsa_actitype;
    }

    public String getTsa_comment() {
        return tsa_comment;
    }

    public void setTsa_comment(String tsa_comment) {
        this.tsa_comment = tsa_comment;
    }

    public int getTsa_rating() {
        return tsa_rating;
    }

    public void setTsa_rating(int tsa_rating) {
        this.tsa_rating = tsa_rating;
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

    public int getTsa_acti_id() {
        return tsa_acti_id;
    }

    public void setTsa_acti_id(int tsa_acti_id) {
        this.tsa_acti_id = tsa_acti_id;
    }

    public String getTsa_status() {
        return tsa_status;
    }

    public void setTsa_status(String tsa_status) {
        this.tsa_status = tsa_status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "TsaModel{" +
                "tsa_id=" + tsa_id +
                ", tsa_devl_id=" + tsa_devl_id +
                ", tsa_proj_id=" + tsa_proj_id +
                ", tsa_phas_id=" + tsa_phas_id +
                ", tsa_plot_id=" + tsa_plot_id +
                ", tsa_spid=" + tsa_spid +
                ", tsa_contact_id=" + tsa_contact_id +
                ", tsa_actitype='" + tsa_actitype + '\'' +
                ", tsa_comment='" + tsa_comment + '\'' +
                ", tsa_rating=" + tsa_rating +
                ", created_at='" + created_at + '\'' +
                ", created_by='" + created_by + '\'' +
                ", tsa_acti_id=" + tsa_acti_id +
                ", tsa_status='" + tsa_status + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
