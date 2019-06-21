package com.plotalong.android.model.commonModel;

/**
 * Created by shantanu on 6/11/17.
 */

public class LocationParsingModel {
    int clinet_trace_id;
    String trac_id = "",
            trac_key = "",
            trac_lat = "",
            trac_long = "",
            trac_array = "",
            trac_session_id = "",
            trac_created_at = "",
            trac_updated_at = "",
            trac_deleted_at = "",
            trace_sync_status;

    public LocationParsingModel() {
    }

    public LocationParsingModel(int clinet_trace_id, String trac_id, String trac_key, String trac_lat, String trac_long, String trac_array, String trac_session_id, String trac_created_at, String trac_updated_at, String trac_deleted_at, String trace_sync_status) {
        this.clinet_trace_id = clinet_trace_id;
        this.trac_id = trac_id;
        this.trac_key = trac_key;
        this.trac_lat = trac_lat;
        this.trac_long = trac_long;
        this.trac_array = trac_array;
        this.trac_session_id = trac_session_id;
        this.trac_created_at = trac_created_at;
        this.trac_updated_at = trac_updated_at;
        this.trac_deleted_at = trac_deleted_at;
        this.trace_sync_status = trace_sync_status;
    }

    public int getClinet_trace_id() {
        return clinet_trace_id;
    }

    public void setClinet_trace_id(int clinet_trace_id) {
        this.clinet_trace_id = clinet_trace_id;
    }

    public String getTrac_id() {
        return trac_id;
    }

    public void setTrac_id(String trac_id) {
        this.trac_id = trac_id;
    }

    public String getTrac_key() {
        return trac_key;
    }

    public void setTrac_key(String trac_key) {
        this.trac_key = trac_key;
    }

    public String getTrac_lat() {
        return trac_lat;
    }

    public void setTrac_lat(String trac_lat) {
        this.trac_lat = trac_lat;
    }

    public String getTrac_long() {
        return trac_long;
    }

    public void setTrac_long(String trac_long) {
        this.trac_long = trac_long;
    }

    public String getTrac_array() {
        return trac_array;
    }

    public void setTrac_array(String trac_array) {
        this.trac_array = trac_array;
    }

    public String getTrac_session_id() {
        return trac_session_id;
    }

    public void setTrac_session_id(String trac_session_id) {
        this.trac_session_id = trac_session_id;
    }

    public String getTrac_created_at() {
        return trac_created_at;
    }

    public void setTrac_created_at(String trac_created_at) {
        this.trac_created_at = trac_created_at;
    }

    public String getTrac_updated_at() {
        return trac_updated_at;
    }

    public void setTrac_updated_at(String trac_updated_at) {
        this.trac_updated_at = trac_updated_at;
    }

    public String getTrac_deleted_at() {
        return trac_deleted_at;
    }

    public void setTrac_deleted_at(String trac_deleted_at) {
        this.trac_deleted_at = trac_deleted_at;
    }

    public String getTrace_sync_status() {
        return trace_sync_status;
    }

    public void setTrace_sync_status(String trace_sync_status) {
        this.trace_sync_status = trace_sync_status;
    }

    @Override
    public String toString() {
        return "LocationParsingModel{" +
                "clinet_trace_id=" + clinet_trace_id +
                ", trac_id='" + trac_id + '\'' +
                ", trac_key='" + trac_key + '\'' +
                ", trac_lat='" + trac_lat + '\'' +
                ", trac_long='" + trac_long + '\'' +
                ", trac_array='" + trac_array + '\'' +
                ", trac_session_id='" + trac_session_id + '\'' +
                ", trac_created_at='" + trac_created_at + '\'' +
                ", trac_updated_at='" + trac_updated_at + '\'' +
                ", trac_deleted_at='" + trac_deleted_at + '\'' +
                ", trace_sync_status='" + trace_sync_status + '\'' +
                '}';
    }
}
