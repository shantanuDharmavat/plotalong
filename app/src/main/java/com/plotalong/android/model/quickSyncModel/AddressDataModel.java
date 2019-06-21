package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 14/11/17.
 */

public class AddressDataModel {
    @SerializedName("cfg_addr_client_id")
    @Expose
    int cfg_addr_client_id;
    @SerializedName("cfg_addr_id")
    @Expose
    int cfg_addr_id;
    @SerializedName("ref_id")
    @Expose
    int ref_id;
    @SerializedName("addr_devl_id")
    @Expose
    int addr_devl_id;
    @SerializedName("addr_line1")
    @Expose
    String addr_line1;
    @SerializedName("addr_line2")
    @Expose
    String addr_line2;
    @SerializedName("addr_locality")
    @Expose
    String addr_locality;
    @SerializedName("addr_city")
    @Expose
    String addr_city;
    @SerializedName("addr_district")
    @Expose
    String addr_district;
    @SerializedName("addr_taluka")
    @Expose
    String addr_taluka;
    @SerializedName("addr_state")
    @Expose
    String addr_state;
    @SerializedName("addr_pincode")
    @Expose
    String addr_pincode;
    @SerializedName("addr_country")
    @Expose
    String addr_country;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("created_at")
    @Expose
    String created_at;
    @SerializedName("updated_at")
    @Expose
    String updated_at;
    @SerializedName("deleted_at")
    @Expose
    String deleted_at;
    @SerializedName("addr_sync_status")
    @Expose
    String addr_sync_status;

    public AddressDataModel() {
    }

    public AddressDataModel(int cfg_addr_client_id, int cfg_addr_id, int ref_id, int addr_devl_id, String addr_line1, String addr_line2, String addr_locality, String addr_city, String addr_district, String addr_taluka, String addr_state, String addr_pincode, String addr_country, String type, String created_at, String updated_at, String deleted_at, String addr_sync_status) {
        this.cfg_addr_client_id = cfg_addr_client_id;
        this.cfg_addr_id = cfg_addr_id;
        this.ref_id = ref_id;
        this.addr_devl_id = addr_devl_id;
        this.addr_line1 = addr_line1;
        this.addr_line2 = addr_line2;
        this.addr_locality = addr_locality;
        this.addr_city = addr_city;
        this.addr_district = addr_district;
        this.addr_taluka = addr_taluka;
        this.addr_state = addr_state;
        this.addr_pincode = addr_pincode;
        this.addr_country = addr_country;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.addr_sync_status = addr_sync_status;
    }

    public int getCfg_addr_client_id() {
        return cfg_addr_client_id;
    }

    public void setCfg_addr_client_id(int cfg_addr_client_id) {
        this.cfg_addr_client_id = cfg_addr_client_id;
    }

    public int getCfg_addr_id() {
        return cfg_addr_id;
    }

    public void setCfg_addr_id(int cfg_addr_id) {
        this.cfg_addr_id = cfg_addr_id;
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public int getAddr_devl_id() {
        return addr_devl_id;
    }

    public void setAddr_devl_id(int addr_devl_id) {
        this.addr_devl_id = addr_devl_id;
    }

    public String getAddr_line1() {
        return addr_line1;
    }

    public void setAddr_line1(String addr_line1) {
        this.addr_line1 = addr_line1;
    }

    public String getAddr_line2() {
        return addr_line2;
    }

    public void setAddr_line2(String addr_line2) {
        this.addr_line2 = addr_line2;
    }

    public String getAddr_locality() {
        return addr_locality;
    }

    public void setAddr_locality(String addr_locality) {
        this.addr_locality = addr_locality;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getAddr_district() {
        return addr_district;
    }

    public void setAddr_district(String addr_district) {
        this.addr_district = addr_district;
    }

    public String getAddr_taluka() {
        return addr_taluka;
    }

    public void setAddr_taluka(String addr_taluka) {
        this.addr_taluka = addr_taluka;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public String getAddr_pincode() {
        return addr_pincode;
    }

    public void setAddr_pincode(String addr_pincode) {
        this.addr_pincode = addr_pincode;
    }

    public String getAddr_country() {
        return addr_country;
    }

    public void setAddr_country(String addr_country) {
        this.addr_country = addr_country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAddr_sync_status() {
        return addr_sync_status;
    }

    public void setAddr_sync_status(String addr_sync_status) {
        this.addr_sync_status = addr_sync_status;
    }

    @Override
    public String toString() {
        return "AddressDataModel{" +
                "cfg_addr_client_id=" + cfg_addr_client_id +
                ", cfg_addr_id=" + cfg_addr_id +
                ", ref_id=" + ref_id +
                ", addr_devl_id=" + addr_devl_id +
                ", addr_line1='" + addr_line1 + '\'' +
                ", addr_line2='" + addr_line2 + '\'' +
                ", addr_locality='" + addr_locality + '\'' +
                ", addr_city='" + addr_city + '\'' +
                ", addr_district='" + addr_district + '\'' +
                ", addr_taluka='" + addr_taluka + '\'' +
                ", addr_state='" + addr_state + '\'' +
                ", addr_pincode='" + addr_pincode + '\'' +
                ", addr_country='" + addr_country + '\'' +
                ", type='" + type + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", addr_sync_status='" + addr_sync_status + '\'' +
                '}';
    }
}
