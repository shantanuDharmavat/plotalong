package com.plotalong.android.model.quickSyncModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 1/6/17.
 */

public class PlotModel {
    @SerializedName("plot_id")
    @Expose

    private int plot_id;
    @SerializedName("plot_number")
    @Expose

    private String plot_number;
    @SerializedName("plot_phase_id")
    @Expose

    private int plot_phase_id;
    @SerializedName("plot_project_id")
    @Expose

    private int plot_project_id;
    @SerializedName("plot_sector")
    @Expose

    private String plot_sector;
    @SerializedName("plot_size")
    @Expose

    private int plot_size;
    @SerializedName("plot_length")
    @Expose

    private int plot_length;
    @SerializedName("plot_width")
    @Expose

    private int plot_width;
    @SerializedName("plot_type")
    @Expose

    private String plot_type;
    @SerializedName("plot_status")
    @Expose

    private String plot_status;
    @SerializedName("plot_priority")
    @Expose

    private int plot_priority;
    @SerializedName("plot_customer_id")
    @Expose

    private int plot_customer_id;
    @SerializedName("plot_booked_by")
    @Expose

    private int plot_booked_by;
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
    @SerializedName("plot_coordinates")
    @Expose

    private String plot_coordinates;
    @SerializedName("plot_description")
    @Expose

    private String plot_description;
    @SerializedName("addToPropasalFlag")
    @Expose

    private int addToPropasalFlag;
    @SerializedName("prod_id")
    @Expose

    private int prod_id;
    @SerializedName("cat_name")
    @Expose

    private String cat_name;
    @SerializedName("cat_rate")
    @Expose

    private String cat_rate;



    public PlotModel() {
    }

    public PlotModel(int plot_id, String plot_number, int plot_phase_id, int plot_project_id, String plot_sector, int plot_size, int plot_length, int plot_width, String plot_type, String plot_status, int plot_priority, int plot_customer_id, int plot_booked_by, String created_by, String updated_by, String created_at, String updated_at, String plot_coordinates, String plot_description, int addToPropasalFlag, int prod_id, String cat_name, String cat_rate) {
        this.plot_id = plot_id;
        this.plot_number = plot_number;
        this.plot_phase_id = plot_phase_id;
        this.plot_project_id = plot_project_id;
        this.plot_sector = plot_sector;
        this.plot_size = plot_size;
        this.plot_length = plot_length;
        this.plot_width = plot_width;
        this.plot_type = plot_type;
        this.plot_status = plot_status;
        this.plot_priority = plot_priority;
        this.plot_customer_id = plot_customer_id;
        this.plot_booked_by = plot_booked_by;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.plot_coordinates = plot_coordinates;
        this.plot_description = plot_description;
        this.addToPropasalFlag = addToPropasalFlag;
        this.prod_id = prod_id;
        this.cat_name = cat_name;
        this.cat_rate = cat_rate;
    }

    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
    }

    public String getPlot_number() {
        return plot_number;
    }

    public void setPlot_number(String plot_number) {
        this.plot_number = plot_number;
    }

    public int getPlot_phase_id() {
        return plot_phase_id;
    }

    public void setPlot_phase_id(int plot_phase_id) {
        this.plot_phase_id = plot_phase_id;
    }

    public int getPlot_project_id() {
        return plot_project_id;
    }

    public void setPlot_project_id(int plot_project_id) {
        this.plot_project_id = plot_project_id;
    }

    public String getPlot_sector() {
        return plot_sector;
    }

    public void setPlot_sector(String plot_sector) {
        this.plot_sector = plot_sector;
    }

    public int getPlot_size() {
        return plot_size;
    }

    public void setPlot_size(int plot_size) {
        this.plot_size = plot_size;
    }

    public int getPlot_length() {
        return plot_length;
    }

    public void setPlot_length(int plot_length) {
        this.plot_length = plot_length;
    }

    public int getPlot_width() {
        return plot_width;
    }

    public void setPlot_width(int plot_width) {
        this.plot_width = plot_width;
    }

    public String getPlot_type() {
        return plot_type;
    }

    public void setPlot_type(String plot_type) {
        this.plot_type = plot_type;
    }

    public String getPlot_status() {
        return plot_status;
    }

    public void setPlot_status(String plot_status) {
        this.plot_status = plot_status;
    }

    public int getPlot_priority() {
        return plot_priority;
    }

    public void setPlot_priority(int plot_priority) {
        this.plot_priority = plot_priority;
    }

    public int getPlot_customer_id() {
        return plot_customer_id;
    }

    public void setPlot_customer_id(int plot_customer_id) {
        this.plot_customer_id = plot_customer_id;
    }

    public int getPlot_booked_by() {
        return plot_booked_by;
    }

    public void setPlot_booked_by(int plot_booked_by) {
        this.plot_booked_by = plot_booked_by;
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

    public String getPlot_coordinates() {
        return plot_coordinates;
    }

    public void setPlot_coordinates(String plot_coordinates) {
        this.plot_coordinates = plot_coordinates;
    }

    public String getPlot_description() {
        return plot_description;
    }

    public void setPlot_description(String plot_description) {
        this.plot_description = plot_description;
    }

    public int getAddToPropasalFlag() {
        return addToPropasalFlag;
    }

    public void setAddToPropasalFlag(int addToPropasalFlag) {
        this.addToPropasalFlag = addToPropasalFlag;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_rate() {
        return cat_rate;
    }

    public void setCat_rate(String cat_rate) {
        this.cat_rate = cat_rate;
    }

    @Override
    public String toString() {
        return "PlotModel{" +
                "plot_id=" + plot_id +
                ", plot_number='" + plot_number + '\'' +
                ", plot_phase_id=" + plot_phase_id +
                ", plot_project_id=" + plot_project_id +
                ", plot_sector='" + plot_sector + '\'' +
                ", plot_size=" + plot_size +
                ", plot_length=" + plot_length +
                ", plot_width=" + plot_width +
                ", plot_type='" + plot_type + '\'' +
                ", plot_status='" + plot_status + '\'' +
                ", plot_priority=" + plot_priority +
                ", plot_customer_id=" + plot_customer_id +
                ", plot_booked_by=" + plot_booked_by +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", plot_coordinates='" + plot_coordinates + '\'' +
                ", plot_description='" + plot_description + '\'' +
                ", addToPropasalFlag=" + addToPropasalFlag +
                ", prod_id=" + prod_id +
                ", cat_name='" + cat_name + '\'' +
                ", cat_rate='" + cat_rate + '\'' +
                '}';
    }
}
