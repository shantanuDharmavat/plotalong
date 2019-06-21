package com.plotalong.android.model.feedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 2/11/17.
 */

public class AnswerDataModel {
    @SerializedName("ans_client_id")
    @Expose
    private int ans_client_id;
    @SerializedName("ans_id")
    @Expose
    private int ans_id;
    @SerializedName("ans_devl_id")
    @Expose
    private int ans_devl_id;
    @SerializedName("ans_proj_id")
    @Expose
    private int ans_proj_id;
    @SerializedName("ans_phase_id")
    @Expose
    private int ans_phase_id;
    @SerializedName("ans_que_text")
    @Expose
    private String ans_que_text;
    @SerializedName("ans_text")
    @Expose
    private String ans_text;
    @SerializedName("ans_type")
    @Expose
    private String ans_type;
    @SerializedName("ans_range")
    @Expose
    private int ans_range;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;
    @SerializedName("ans_spid")
    @Expose
    private int ans_spid;
    @SerializedName("ans_cust_id")
    @Expose
    private String ans_cust_id;
    @SerializedName("ans_group_id")
    @Expose
    private String ans_group_id;
    @SerializedName("ans_file_location")
    @Expose
    private String ans_file_location;
    @SerializedName("ans_sync_status")
    @Expose
    private String ans_sync_status;

    public AnswerDataModel() {
    }

    public AnswerDataModel(int ans_client_id, int ans_id, int ans_devl_id, int ans_proj_id, int ans_phase_id, String ans_que_text, String ans_text, String ans_type, int ans_range, String created_at, String updated_at, String deleted_at, int ans_spid, String ans_cust_id, String ans_group_id, String ans_file_location, String ans_sync_status) {
        this.ans_client_id = ans_client_id;
        this.ans_id = ans_id;
        this.ans_devl_id = ans_devl_id;
        this.ans_proj_id = ans_proj_id;
        this.ans_phase_id = ans_phase_id;
        this.ans_que_text = ans_que_text;
        this.ans_text = ans_text;
        this.ans_type = ans_type;
        this.ans_range = ans_range;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.ans_spid = ans_spid;
        this.ans_cust_id = ans_cust_id;
        this.ans_group_id = ans_group_id;
        this.ans_file_location = ans_file_location;
        this.ans_sync_status = ans_sync_status;
    }

    public int getAns_client_id() {
        return ans_client_id;
    }

    public void setAns_client_id(int ans_client_id) {
        this.ans_client_id = ans_client_id;
    }

    public int getAns_id() {
        return ans_id;
    }

    public void setAns_id(int ans_id) {
        this.ans_id = ans_id;
    }

    public int getAns_devl_id() {
        return ans_devl_id;
    }

    public void setAns_devl_id(int ans_devl_id) {
        this.ans_devl_id = ans_devl_id;
    }

    public int getAns_proj_id() {
        return ans_proj_id;
    }

    public void setAns_proj_id(int ans_proj_id) {
        this.ans_proj_id = ans_proj_id;
    }

    public int getAns_phase_id() {
        return ans_phase_id;
    }

    public void setAns_phase_id(int ans_phase_id) {
        this.ans_phase_id = ans_phase_id;
    }

    public String getAns_que_text() {
        return ans_que_text;
    }

    public void setAns_que_text(String ans_que_text) {
        this.ans_que_text = ans_que_text;
    }

    public String getAns_text() {
        return ans_text;
    }

    public void setAns_text(String ans_text) {
        this.ans_text = ans_text;
    }

    public String getAns_type() {
        return ans_type;
    }

    public void setAns_type(String ans_type) {
        this.ans_type = ans_type;
    }

    public int getAns_range() {
        return ans_range;
    }

    public void setAns_range(int ans_range) {
        this.ans_range = ans_range;
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

    public int getAns_spid() {
        return ans_spid;
    }

    public void setAns_spid(int ans_spid) {
        this.ans_spid = ans_spid;
    }

    public String getAns_cust_id() {
        return ans_cust_id;
    }

    public void setAns_cust_id(String ans_cust_id) {
        this.ans_cust_id = ans_cust_id;
    }

    public String getAns_group_id() {
        return ans_group_id;
    }

    public void setAns_group_id(String ans_group_id) {
        this.ans_group_id = ans_group_id;
    }

    public String getAns_file_location() {
        return ans_file_location;
    }

    public void setAns_file_location(String ans_file_location) {
        this.ans_file_location = ans_file_location;
    }

    public String getAns_sync_status() {
        return ans_sync_status;
    }

    public void setAns_sync_status(String ans_sync_status) {
        this.ans_sync_status = ans_sync_status;
    }

    @Override
    public String toString() {
        return "AnswerDataModel{" +
                "ans_client_id=" + ans_client_id +
                ", ans_id=" + ans_id +
                ", ans_devl_id=" + ans_devl_id +
                ", ans_proj_id=" + ans_proj_id +
                ", ans_phase_id=" + ans_phase_id +
                ", ans_que_text='" + ans_que_text + '\'' +
                ", ans_text='" + ans_text + '\'' +
                ", ans_type='" + ans_type + '\'' +
                ", ans_range=" + ans_range +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", ans_spid=" + ans_spid +
                ", ans_cust_id='" + ans_cust_id + '\'' +
                ", ans_group_id='" + ans_group_id + '\'' +
                ", ans_file_location='" + ans_file_location + '\'' +
                ", ans_sync_status='" + ans_sync_status + '\'' +
                '}';
    }
}
