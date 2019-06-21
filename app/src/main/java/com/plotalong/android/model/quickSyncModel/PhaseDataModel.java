package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 13/11/17.
 */

public class PhaseDataModel {
    @SerializedName("phas_id")
    @Expose

    private int phas_id;
    @SerializedName("phas_proj_id")
    @Expose

    private int phas_proj_id;
    @SerializedName("phas_devl_id")
    @Expose

    private int phas_devl_id;
    @SerializedName("phas_key")
    @Expose

    private String phas_key;
    @SerializedName("phas_name")
    @Expose

    private String phas_name;
    @SerializedName("phas_details")
    @Expose

    private String phas_details;
    @SerializedName("phas_north")
    @Expose

    private String phas_north;
    @SerializedName("phas_east")
    @Expose

    private String phas_east;
    @SerializedName("phas_west")
    @Expose

    private String phas_west;
    @SerializedName("phas_south")
    @Expose

    private String phas_south;
    @SerializedName("phas_boundaries")
    @Expose

    private String phas_boundaries;
    @SerializedName("phas_routes")
    @Expose

    private String phas_routes;
    @SerializedName("phas_nearby")
    @Expose

    private String phas_nearby;
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
    @SerializedName("phas_uom_id")
    @Expose

    private String phas_uom_id;
    @SerializedName("phas_type_id")
    @Expose

    private String phas_type_id;
    @SerializedName("phas_logo")
    @Expose

    private String phas_logo;
    @SerializedName("phas_launch_date")
    @Expose

    private String phas_launch_date;
    @SerializedName("phas_rera_code")
    @Expose

    private String phas_rera_code;
    @SerializedName("phas_coordinates")
    @Expose

    private String phas_coordinates;
    @SerializedName("phas_rera_renew_date")
    @Expose

    private String phas_rera_renew_date;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;



    public PhaseDataModel() {
    }

    public PhaseDataModel(int phas_id, int phas_proj_id, int phas_devl_id, String phas_key, String phas_name, String phas_details, String phas_north, String phas_east, String phas_west, String phas_south, String phas_boundaries, String phas_routes, String phas_nearby, String created_by, String updated_by, String created_at, String updated_at, String phas_uom_id, String phas_type_id, String phas_logo, String phas_launch_date, String phas_rera_code, String phas_coordinates, String phas_rera_renew_date, String deleted_at) {
        this.phas_id = phas_id;
        this.phas_proj_id = phas_proj_id;
        this.phas_devl_id = phas_devl_id;
        this.phas_key = phas_key;
        this.phas_name = phas_name;
        this.phas_details = phas_details;
        this.phas_north = phas_north;
        this.phas_east = phas_east;
        this.phas_west = phas_west;
        this.phas_south = phas_south;
        this.phas_boundaries = phas_boundaries;
        this.phas_routes = phas_routes;
        this.phas_nearby = phas_nearby;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.phas_uom_id = phas_uom_id;
        this.phas_type_id = phas_type_id;
        this.phas_logo = phas_logo;
        this.phas_launch_date = phas_launch_date;
        this.phas_rera_code = phas_rera_code;
        this.phas_coordinates = phas_coordinates;
        this.phas_rera_renew_date = phas_rera_renew_date;
        this.deleted_at = deleted_at;
    }

    public int getPhas_id() {
        return phas_id;
    }

    public void setPhas_id(int phas_id) {
        this.phas_id = phas_id;
    }

    public int getPhas_proj_id() {
        return phas_proj_id;
    }

    public void setPhas_proj_id(int phas_proj_id) {
        this.phas_proj_id = phas_proj_id;
    }

    public int getPhas_devl_id() {
        return phas_devl_id;
    }

    public void setPhas_devl_id(int phas_devl_id) {
        this.phas_devl_id = phas_devl_id;
    }

    public String getPhas_key() {
        return phas_key;
    }

    public void setPhas_key(String phas_key) {
        this.phas_key = phas_key;
    }

    public String getPhas_name() {
        return phas_name;
    }

    public void setPhas_name(String phas_name) {
        this.phas_name = phas_name;
    }

    public String getPhas_details() {
        return phas_details;
    }

    public void setPhas_details(String phas_details) {
        this.phas_details = phas_details;
    }

    public String getPhas_north() {
        return phas_north;
    }

    public void setPhas_north(String phas_north) {
        this.phas_north = phas_north;
    }

    public String getPhas_east() {
        return phas_east;
    }

    public void setPhas_east(String phas_east) {
        this.phas_east = phas_east;
    }

    public String getPhas_west() {
        return phas_west;
    }

    public void setPhas_west(String phas_west) {
        this.phas_west = phas_west;
    }

    public String getPhas_south() {
        return phas_south;
    }

    public void setPhas_south(String phas_south) {
        this.phas_south = phas_south;
    }

    public String getPhas_boundaries() {
        return phas_boundaries;
    }

    public void setPhas_boundaries(String phas_boundaries) {
        this.phas_boundaries = phas_boundaries;
    }

    public String getPhas_routes() {
        return phas_routes;
    }

    public void setPhas_routes(String phas_routes) {
        this.phas_routes = phas_routes;
    }

    public String getPhas_nearby() {
        return phas_nearby;
    }

    public void setPhas_nearby(String phas_nearby) {
        this.phas_nearby = phas_nearby;
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

    public String getPhas_uom_id() {
        return phas_uom_id;
    }

    public void setPhas_uom_id(String phas_uom_id) {
        this.phas_uom_id = phas_uom_id;
    }

    public String getPhas_type_id() {
        return phas_type_id;
    }

    public void setPhas_type_id(String phas_type_id) {
        this.phas_type_id = phas_type_id;
    }

    public String getPhas_logo() {
        return phas_logo;
    }

    public void setPhas_logo(String phas_logo) {
        this.phas_logo = phas_logo;
    }

    public String getPhas_launch_date() {
        return phas_launch_date;
    }

    public void setPhas_launch_date(String phas_launch_date) {
        this.phas_launch_date = phas_launch_date;
    }

    public String getPhas_rera_code() {
        return phas_rera_code;
    }

    public void setPhas_rera_code(String phas_rera_code) {
        this.phas_rera_code = phas_rera_code;
    }

    public String getPhas_coordinates() {
        return phas_coordinates;
    }

    public void setPhas_coordinates(String phas_coordinates) {
        this.phas_coordinates = phas_coordinates;
    }

    public String getPhas_rera_renew_date() {
        return phas_rera_renew_date;
    }

    public void setPhas_rera_renew_date(String phas_rera_renew_date) {
        this.phas_rera_renew_date = phas_rera_renew_date;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        return "PhaseDataModel{" +
                "phas_id=" + phas_id +
                ", phas_proj_id=" + phas_proj_id +
                ", phas_devl_id=" + phas_devl_id +
                ", phas_key='" + phas_key + '\'' +
                ", phas_name='" + phas_name + '\'' +
                ", phas_details='" + phas_details + '\'' +
                ", phas_north='" + phas_north + '\'' +
                ", phas_east='" + phas_east + '\'' +
                ", phas_west='" + phas_west + '\'' +
                ", phas_south='" + phas_south + '\'' +
                ", phas_boundaries='" + phas_boundaries + '\'' +
                ", phas_routes='" + phas_routes + '\'' +
                ", phas_nearby='" + phas_nearby + '\'' +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", phas_uom_id='" + phas_uom_id + '\'' +
                ", phas_type_id='" + phas_type_id + '\'' +
                ", phas_logo='" + phas_logo + '\'' +
                ", phas_launch_date='" + phas_launch_date + '\'' +
                ", phas_rera_code='" + phas_rera_code + '\'' +
                ", phas_coordinates='" + phas_coordinates + '\'' +
                ", phas_rera_renew_date='" + phas_rera_renew_date + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
