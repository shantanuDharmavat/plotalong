package com.plotalong.android.model.quickSyncModel;

/**
 * Created by shantanu on 27/11/17.
 */

public class ActivityDataModel {
    String actiId;
    String acti_key;
    String acti_type;
    String acti_session_id;
    String acti_customer_id;
    String acti_plot_id;
    String created_at;
    String updated_at;
    String deleted_at;

    public String getActiId() {
        return actiId;
    }

    public void setActiId(String actiId) {
        this.actiId = actiId;
    }

    public String getActi_key() {
        return acti_key;
    }

    public void setActi_key(String acti_key) {
        this.acti_key = acti_key;
    }

    public String getActi_type() {
        return acti_type;
    }

    public void setActi_type(String acti_type) {
        this.acti_type = acti_type;
    }

    public String getActi_session_id() {
        return acti_session_id;
    }

    public void setActi_session_id(String acti_session_id) {
        this.acti_session_id = acti_session_id;
    }

    public String getActi_customer_id() {
        return acti_customer_id;
    }

    public void setActi_customer_id(String acti_customer_id) {
        this.acti_customer_id = acti_customer_id;
    }

    public String getActi_plot_id() {
        return acti_plot_id;
    }

    public void setActi_plot_id(String acti_plot_id) {
        this.acti_plot_id = acti_plot_id;
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
        return "ActivityDataModel{" +
                "actiId='" + actiId + '\'' +
                ", acti_key='" + acti_key + '\'' +
                ", acti_type='" + acti_type + '\'' +
                ", acti_session_id='" + acti_session_id + '\'' +
                ", acti_customer_id='" + acti_customer_id + '\'' +
                ", acti_plot_id='" + acti_plot_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
