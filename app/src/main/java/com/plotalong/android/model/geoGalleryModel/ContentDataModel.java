package com.plotalong.android.model.geoGalleryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.android.gms.maps.model.LatLng;
import com.plotalong.android.util.Utility;


/**
 * Created by shantanu on 27/9/17.
 */

public class ContentDataModel {
    @SerializedName("cont_client_id")
    @Expose

    private int cont_client_id;
    @SerializedName("cont_id")
    @Expose

    private int cont_id;
    @SerializedName("cont_devc_id")
    @Expose

    private String cont_devc_id;
    @SerializedName("cont_devl_id")
    @Expose

    private int cont_devl_id;
    @SerializedName("cont_project_id")
    @Expose

    private int cont_project_id;
    @SerializedName("cont_phase_id")
    @Expose

    private int cont_phase_id;
    @SerializedName("cont_plot_id")
    @Expose

    private int cont_plot_id;
    @SerializedName("cont_cust_id")
    @Expose

    private int cont_cust_id;
    @SerializedName("cont_file_type")
    @Expose

    private String cont_file_type;
    @SerializedName("cont_file_location")
    @Expose

    private String cont_file_location;
    @SerializedName("cont_description")
    @Expose

    private String cont_description;
    @SerializedName("cont_app_tag")
    @Expose

    private String cont_app_tag;
    @SerializedName("cont_command_tag")
    @Expose

    private int cont_command_tag;
    @SerializedName("cont_filefolderstructure_device")
    @Expose

    private String cont_filefolderstructure_device;
    @SerializedName("cont_order")
    @Expose

    private int cont_order;
    @SerializedName("cont_latitude")
    @Expose

    private String cont_latitude;
    @SerializedName("cont_longitude")
    @Expose

    private String cont_longitude;
    @SerializedName("cont_status")
    @Expose

    private int cont_status;
    @SerializedName("device_cfg")
    @Expose

    private int device_cfg;
    @SerializedName("cont_is_deleted")
    @Expose

    private int cont_is_deleted;
    @SerializedName("cont_prod_id")
    @Expose

    private int cont_prod_id;
    @SerializedName("cont_type")
    @Expose

    private String cont_type;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("cont_sync_status")
    @Expose

    private String cont_sync_status;
    @SerializedName("cont_file_name")
    @Expose

    private String cont_file_name;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;


    public ContentDataModel() {
    }

    public ContentDataModel(int cont_client_id, int cont_id, String cont_devc_id, int cont_devl_id, int cont_project_id, int cont_phase_id, int cont_plot_id, int cont_cust_id, String cont_file_type, String cont_file_location, String cont_description, String cont_app_tag, int cont_command_tag, String cont_filefolderstructure_device, int cont_order, String cont_latitude, String cont_longitude, int cont_status, int device_cfg, int cont_is_deleted, int cont_prod_id, String cont_type, String created_at, String updated_at, String cont_sync_status, String cont_file_name, String deleted_at) {
        this.cont_client_id = cont_client_id;
        this.cont_id = cont_id;
        this.cont_devc_id = cont_devc_id;
        this.cont_devl_id = cont_devl_id;
        this.cont_project_id = cont_project_id;
        this.cont_phase_id = cont_phase_id;
        this.cont_plot_id = cont_plot_id;
        this.cont_cust_id = cont_cust_id;
        this.cont_file_type = cont_file_type;
        this.cont_file_location = cont_file_location;
        this.cont_description = cont_description;
        this.cont_app_tag = cont_app_tag;
        this.cont_command_tag = cont_command_tag;
        this.cont_filefolderstructure_device = cont_filefolderstructure_device;
        this.cont_order = cont_order;
        this.cont_latitude = cont_latitude;
        this.cont_longitude = cont_longitude;
        this.cont_status = cont_status;
        this.device_cfg = device_cfg;
        this.cont_is_deleted = cont_is_deleted;
        this.cont_prod_id = cont_prod_id;
        this.cont_type = cont_type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.cont_sync_status = cont_sync_status;
        this.cont_file_name = cont_file_name;
        this.deleted_at = deleted_at;
    }

    public int getCont_client_id() {
        return cont_client_id;
    }

    public void setCont_client_id(int cont_client_id) {
        this.cont_client_id = cont_client_id;
    }

    public int getCont_id() {
        return cont_id;
    }

    public void setCont_id(int cont_id) {
        this.cont_id = cont_id;
    }

    public String getCont_devc_id() {
        return cont_devc_id;
    }

    public void setCont_devc_id(String cont_devc_id) {
        this.cont_devc_id = cont_devc_id;
    }

    public int getCont_devl_id() {
        return cont_devl_id;
    }

    public void setCont_devl_id(int cont_devl_id) {
        this.cont_devl_id = cont_devl_id;
    }

    public int getCont_project_id() {
        return cont_project_id;
    }

    public void setCont_project_id(int cont_project_id) {
        this.cont_project_id = cont_project_id;
    }

    public int getCont_phase_id() {
        return cont_phase_id;
    }

    public void setCont_phase_id(int cont_phase_id) {
        this.cont_phase_id = cont_phase_id;
    }

    public int getCont_plot_id() {
        return cont_plot_id;
    }

    public void setCont_plot_id(int cont_plot_id) {
        this.cont_plot_id = cont_plot_id;
    }

    public int getCont_cust_id() {
        return cont_cust_id;
    }

    public void setCont_cust_id(int cont_cust_id) {
        this.cont_cust_id = cont_cust_id;
    }

    public String getCont_file_type() {
        return cont_file_type;
    }

    public void setCont_file_type(String cont_file_type) {
        this.cont_file_type = cont_file_type;
    }

    public String getCont_file_location() {
        return cont_file_location;
    }

    public void setCont_file_location(String cont_file_location) {
        this.cont_file_location = cont_file_location;
    }

    public String getCont_description() {
        return cont_description;
    }

    public void setCont_description(String cont_description) {
        this.cont_description = cont_description;
    }

    public String getCont_app_tag() {
        return cont_app_tag;
    }

    public void setCont_app_tag(String cont_app_tag) {
        this.cont_app_tag = cont_app_tag;
    }

    public int getCont_command_tag() {
        return cont_command_tag;
    }

    public void setCont_command_tag(int cont_command_tag) {
        this.cont_command_tag = cont_command_tag;
    }

    public String getCont_filefolderstructure_device() {
        return cont_filefolderstructure_device;
    }

    public void setCont_filefolderstructure_device(String cont_filefolderstructure_device) {
        this.cont_filefolderstructure_device = cont_filefolderstructure_device;
    }

    public int getCont_order() {
        return cont_order;
    }

    public void setCont_order(int cont_order) {
        this.cont_order = cont_order;
    }

    public String getCont_latitude() {
        return cont_latitude;
    }

    public void setCont_latitude(String cont_latitude) {
        this.cont_latitude = cont_latitude;
    }

    public String getCont_longitude() {
        return cont_longitude;
    }

    public void setCont_longitude(String cont_longitude) {
        this.cont_longitude = cont_longitude;
    }

    public int getCont_status() {
        return cont_status;
    }

    public void setCont_status(int cont_status) {
        this.cont_status = cont_status;
    }

    public int getDevice_cfg() {
        return device_cfg;
    }

    public void setDevice_cfg(int device_cfg) {
        this.device_cfg = device_cfg;
    }

    public int getCont_is_deleted() {
        return cont_is_deleted;
    }

    public void setCont_is_deleted(int cont_is_deleted) {
        this.cont_is_deleted = cont_is_deleted;
    }

    public int getCont_prod_id() {
        return cont_prod_id;
    }

    public void setCont_prod_id(int cont_prod_id) {
        this.cont_prod_id = cont_prod_id;
    }

    public String getCont_type() {
        return cont_type;
    }

    public void setCont_type(String cont_type) {
        this.cont_type = cont_type;
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

    public String getCont_sync_status() {
        return cont_sync_status;
    }

    public void setCont_sync_status(String cont_sync_status) {
        this.cont_sync_status = cont_sync_status;
    }

    public String getCont_file_name() {
        return cont_file_name;
    }

    public void setCont_file_name(String cont_file_name) {
        this.cont_file_name = cont_file_name;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public LatLng getLatLng(){
        return Utility.getLatLng(getCont_latitude(),getCont_longitude());
    }

    @Override
    public String toString() {
        return "ContentDataModel{" +
                "cont_client_id=" + cont_client_id +
                ", cont_id=" + cont_id +
                ", cont_devc_id='" + cont_devc_id + '\'' +
                ", cont_devl_id=" + cont_devl_id +
                ", cont_project_id=" + cont_project_id +
                ", cont_phase_id=" + cont_phase_id +
                ", cont_plot_id=" + cont_plot_id +
                ", cont_cust_id=" + cont_cust_id +
                ", cont_file_type='" + cont_file_type + '\'' +
                ", cont_file_location='" + cont_file_location + '\'' +
                ", cont_description='" + cont_description + '\'' +
                ", cont_app_tag='" + cont_app_tag + '\'' +
                ", cont_command_tag=" + cont_command_tag +
                ", cont_filefolderstructure_device='" + cont_filefolderstructure_device + '\'' +
                ", cont_order=" + cont_order +
                ", cont_latitude='" + cont_latitude + '\'' +
                ", cont_longitude='" + cont_longitude + '\'' +
                ", cont_status=" + cont_status +
                ", device_cfg=" + device_cfg +
                ", cont_is_deleted=" + cont_is_deleted +
                ", cont_prod_id=" + cont_prod_id +
                ", cont_type='" + cont_type + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", cont_sync_status='" + cont_sync_status + '\'' +
                ", cont_file_name='" + cont_file_name + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
