package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 12/9/17.
 */

public class SessionDataModel {
    @SerializedName("sess_id")
    @Expose

    private int sess_id;
    @SerializedName("sess_key")
    @Expose

    private String sess_key;
    @SerializedName("sess_start_timestamp")
    @Expose

    private String sess_start_timestamp;
    @SerializedName("sess_start_lat")
    @Expose

    private String sess_start_lat;
    @SerializedName("sess_end_lat")
    @Expose

    private String sess_end_lat;
    @SerializedName("sess_start_long")
    @Expose

    private String sess_start_long;
    @SerializedName("sess_end_long")
    @Expose

    private String sess_end_long;
    @SerializedName("sess_type")
    @Expose

    private String sess_type;
    @SerializedName("sess_group_id")
    @Expose

    private String sess_group_id;
    @SerializedName("sess_cust_unique_id")
    @Expose

    private int sess_cust_unique_id;
    @SerializedName("sess_user_id")
    @Expose

    private int sess_user_id;
    @SerializedName("sess_spid")
    @Expose

    private int sess_spid;
    @SerializedName("sess_project_id")
    @Expose

    private int sess_project_id;
    @SerializedName("sess_phase_id")
    @Expose

    private int sess_phase_id;
    @SerializedName("sess_customer_feedback")
    @Expose

    private String sess_customer_feedback;
    @SerializedName("sess_user_feedback")
    @Expose

    private String sess_user_feedback;
    @SerializedName("sess_image_location")
    @Expose

    private String sess_image_location;
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
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;
    @SerializedName("sess_trac_id_fk")
    @Expose

    private String sess_trac_id_fk;
    @SerializedName("sess_end_flag")
    @Expose

    private String sess_end_flag;
    @SerializedName("sess_sync_status")
    @Expose

    private String sess_sync_status;


    public SessionDataModel() {
    }

    public SessionDataModel(int sess_id, String sess_key, String sess_start_timestamp, String sess_start_lat, String sess_end_lat, String sess_start_long, String sess_end_long, String sess_type, String sess_group_id, int sess_cust_unique_id, int sess_user_id, int sess_spid, int sess_project_id, int sess_phase_id, String sess_customer_feedback, String sess_user_feedback, String sess_image_location, String created_by, String updated_by, String created_at, String updated_at, String deleted_at, String sess_trac_id_fk, String sess_end_flag, String sess_sync_status) {
        this.sess_id = sess_id;
        this.sess_key = sess_key;
        this.sess_start_timestamp = sess_start_timestamp;
        this.sess_start_lat = sess_start_lat;
        this.sess_end_lat = sess_end_lat;
        this.sess_start_long = sess_start_long;
        this.sess_end_long = sess_end_long;
        this.sess_type = sess_type;
        this.sess_group_id = sess_group_id;
        this.sess_cust_unique_id = sess_cust_unique_id;
        this.sess_user_id = sess_user_id;
        this.sess_spid = sess_spid;
        this.sess_project_id = sess_project_id;
        this.sess_phase_id = sess_phase_id;
        this.sess_customer_feedback = sess_customer_feedback;
        this.sess_user_feedback = sess_user_feedback;
        this.sess_image_location = sess_image_location;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.sess_trac_id_fk = sess_trac_id_fk;
        this.sess_end_flag = sess_end_flag;
        this.sess_sync_status = sess_sync_status;
    }

    public int getSess_id() {
        return sess_id;
    }

    public void setSess_id(int sess_id) {
        this.sess_id = sess_id;
    }

    public String getSess_key() {
        return sess_key;
    }

    public void setSess_key(String sess_key) {
        this.sess_key = sess_key;
    }

    public String getSess_start_timestamp() {
        return sess_start_timestamp;
    }

    public void setSess_start_timestamp(String sess_start_timestamp) {
        this.sess_start_timestamp = sess_start_timestamp;
    }

    public String getSess_start_lat() {
        return sess_start_lat;
    }

    public void setSess_start_lat(String sess_start_lat) {
        this.sess_start_lat = sess_start_lat;
    }

    public String getSess_end_lat() {
        return sess_end_lat;
    }

    public void setSess_end_lat(String sess_end_lat) {
        this.sess_end_lat = sess_end_lat;
    }

    public String getSess_start_long() {
        return sess_start_long;
    }

    public void setSess_start_long(String sess_start_long) {
        this.sess_start_long = sess_start_long;
    }

    public String getSess_end_long() {
        return sess_end_long;
    }

    public void setSess_end_long(String sess_end_long) {
        this.sess_end_long = sess_end_long;
    }

    public String getSess_type() {
        return sess_type;
    }

    public void setSess_type(String sess_type) {
        this.sess_type = sess_type;
    }

    public String getSess_group_id() {
        return sess_group_id;
    }

    public void setSess_group_id(String sess_group_id) {
        this.sess_group_id = sess_group_id;
    }

    public int getSess_cust_unique_id() {
        return sess_cust_unique_id;
    }

    public void setSess_cust_unique_id(int sess_cust_unique_id) {
        this.sess_cust_unique_id = sess_cust_unique_id;
    }

    public int getSess_user_id() {
        return sess_user_id;
    }

    public void setSess_user_id(int sess_user_id) {
        this.sess_user_id = sess_user_id;
    }

    public int getSess_spid() {
        return sess_spid;
    }

    public void setSess_spid(int sess_spid) {
        this.sess_spid = sess_spid;
    }

    public int getSess_project_id() {
        return sess_project_id;
    }

    public void setSess_project_id(int sess_project_id) {
        this.sess_project_id = sess_project_id;
    }

    public int getSess_phase_id() {
        return sess_phase_id;
    }

    public void setSess_phase_id(int sess_phase_id) {
        this.sess_phase_id = sess_phase_id;
    }

    public String getSess_customer_feedback() {
        return sess_customer_feedback;
    }

    public void setSess_customer_feedback(String sess_customer_feedback) {
        this.sess_customer_feedback = sess_customer_feedback;
    }

    public String getSess_user_feedback() {
        return sess_user_feedback;
    }

    public void setSess_user_feedback(String sess_user_feedback) {
        this.sess_user_feedback = sess_user_feedback;
    }

    public String getSess_image_location() {
        return sess_image_location;
    }

    public void setSess_image_location(String sess_image_location) {
        this.sess_image_location = sess_image_location;
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

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getSess_trac_id_fk() {
        return sess_trac_id_fk;
    }

    public void setSess_trac_id_fk(String sess_trac_id_fk) {
        this.sess_trac_id_fk = sess_trac_id_fk;
    }

    public String getSess_end_flag() {
        return sess_end_flag;
    }

    public void setSess_end_flag(String sess_end_flag) {
        this.sess_end_flag = sess_end_flag;
    }

    public String getSess_sync_status() {
        return sess_sync_status;
    }

    public void setSess_sync_status(String sess_sync_status) {
        this.sess_sync_status = sess_sync_status;
    }


    @Override
    public String toString() {
        return "SessionDataModel{" +
                "sess_id=" + sess_id +
                ", sess_key='" + sess_key + '\'' +
                ", sess_start_timestamp='" + sess_start_timestamp + '\'' +
                ", sess_start_lat='" + sess_start_lat + '\'' +
                ", sess_end_lat='" + sess_end_lat + '\'' +
                ", sess_start_long='" + sess_start_long + '\'' +
                ", sess_end_long='" + sess_end_long + '\'' +
                ", sess_type='" + sess_type + '\'' +
                ", sess_group_id='" + sess_group_id + '\'' +
                ", sess_cust_unique_id=" + sess_cust_unique_id +
                ", sess_user_id=" + sess_user_id +
                ", sess_spid=" + sess_spid +
                ", sess_project_id=" + sess_project_id +
                ", sess_phase_id=" + sess_phase_id +
                ", sess_customer_feedback='" + sess_customer_feedback + '\'' +
                ", sess_user_feedback='" + sess_user_feedback + '\'' +
                ", sess_image_location='" + sess_image_location + '\'' +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", sess_trac_id_fk='" + sess_trac_id_fk + '\'' +
                ", sess_end_flag='" + sess_end_flag + '\'' +
                ", sess_sync_status='" + sess_sync_status + '\'' +
                '}';
    }
}
