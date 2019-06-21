package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 1/6/17.
 */

public class CustomerDataModel {
    @SerializedName("cust_client_id")
    @Expose

    private int cust_client_id;
    @SerializedName("cust_unique_id")
    @Expose

    private int cust_unique_id;
    @SerializedName("cust_key")
    @Expose

    private String cust_key;
    @SerializedName("cust_id")
    @Expose

    private int cust_id;
    @SerializedName("cust_devl_id")
    @Expose

    private int cust_devl_id;
    @SerializedName("cust_spid")
    @Expose

    private int cust_spid;
    @SerializedName("cust_first_name")
    @Expose

    private String cust_first_name;
    @SerializedName("cust_last_name")
    @Expose

    private String cust_last_name;
    @SerializedName("cust_mobile")
    @Expose

    private String cust_mobile;
    @SerializedName("cust_landline")
    @Expose

    private String cust_landline;
    @SerializedName("cust_alternate_contact")
    @Expose

    private String cust_alternate_contact;
    @SerializedName("cust_address")
    @Expose

    private String cust_address;
    @SerializedName("cust_email")
    @Expose

    private String cust_email;
    @SerializedName("cust_requirement")
    @Expose

    private String cust_requirement;
    @SerializedName("cust_budget")
    @Expose

    private String cust_budget;
    @SerializedName("cust_preferred_location")
    @Expose

    private String cust_preferred_location;
    @SerializedName("created_at")
    @Expose

    private String created_at;
    @SerializedName("updated_at")
    @Expose

    private String updated_at;
    @SerializedName("cust_profile_pic")
    @Expose

    private String cust_profile_pic;
    @SerializedName("cust_area")
    @Expose

    private String cust_area;
    @SerializedName("cust_uid")
    @Expose

    private String cust_uid;
    @SerializedName("cust_occupation")
    @Expose

    private String cust_occupation;
    @SerializedName("cust_pan_no")
    @Expose

    private String cust_pan_no;
    @SerializedName("cust_son_of")
    @Expose

    private String cust_son_of;
    @SerializedName("cust_marital_status")
    @Expose

    private String cust_marital_status;
    @SerializedName("cust_dob")
    @Expose

    private String cust_dob;
    @SerializedName("cust_rating")
    @Expose

    private int cust_rating;
    @SerializedName("deleted_at")
    @Expose

    private String deleted_at;
    @SerializedName("customerSelectedFlag")
    @Expose

    private int customerSelectedFlag =0;
    @SerializedName("plotLikeFlag")
    @Expose

    private int plotLikeFlag =0;
    @SerializedName("customerTempGroupId")
    @Expose

    private String customerTempGroupId;
    @SerializedName("custSessKey")
    @Expose

    private String custSessKey;
    @SerializedName("tempProposalId")
    @Expose

    private String tempProposalId;
    @SerializedName("cust_sync_status")
    @Expose

    private String cust_sync_status;


    public CustomerDataModel() {
    }

    public CustomerDataModel(int cust_client_id, int cust_unique_id, String cust_key, int cust_id, int cust_devl_id, int cust_spid, String cust_first_name, String cust_last_name, String cust_mobile, String cust_landline, String cust_alternate_contact, String cust_address, String cust_email, String cust_requirement, String cust_budget, String cust_preferred_location, String created_at, String updated_at, String cust_profile_pic, String cust_area, String cust_uid, String cust_occupation, String cust_pan_no, String cust_son_of, String cust_marital_status, String cust_dob, int cust_rating, String deleted_at, int customerSelectedFlag, int plotLikeFlag, String customerTempGroupId, String custSessKey, String tempProposalId, String cust_sync_status) {
        this.cust_client_id = cust_client_id;
        this.cust_unique_id = cust_unique_id;
        this.cust_key = cust_key;
        this.cust_id = cust_id;
        this.cust_devl_id = cust_devl_id;
        this.cust_spid = cust_spid;
        this.cust_first_name = cust_first_name;
        this.cust_last_name = cust_last_name;
        this.cust_mobile = cust_mobile;
        this.cust_landline = cust_landline;
        this.cust_alternate_contact = cust_alternate_contact;
        this.cust_address = cust_address;
        this.cust_email = cust_email;
        this.cust_requirement = cust_requirement;
        this.cust_budget = cust_budget;
        this.cust_preferred_location = cust_preferred_location;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.cust_profile_pic = cust_profile_pic;
        this.cust_area = cust_area;
        this.cust_uid = cust_uid;
        this.cust_occupation = cust_occupation;
        this.cust_pan_no = cust_pan_no;
        this.cust_son_of = cust_son_of;
        this.cust_marital_status = cust_marital_status;
        this.cust_dob = cust_dob;
        this.cust_rating = cust_rating;
        this.deleted_at = deleted_at;
        this.customerSelectedFlag = customerSelectedFlag;
        this.plotLikeFlag = plotLikeFlag;
        this.customerTempGroupId = customerTempGroupId;
        this.custSessKey = custSessKey;
        this.tempProposalId = tempProposalId;
        this.cust_sync_status = cust_sync_status;
    }

    public int getCust_client_id() {
        return cust_client_id;
    }

    public void setCust_client_id(int cust_client_id) {
        this.cust_client_id = cust_client_id;
    }

    public int getCust_unique_id() {
        return cust_unique_id;
    }

    public void setCust_unique_id(int cust_unique_id) {
        this.cust_unique_id = cust_unique_id;
    }

    public String getCust_key() {
        return cust_key;
    }

    public void setCust_key(String cust_key) {
        this.cust_key = cust_key;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getCust_devl_id() {
        return cust_devl_id;
    }

    public void setCust_devl_id(int cust_devl_id) {
        this.cust_devl_id = cust_devl_id;
    }

    public int getCust_spid() {
        return cust_spid;
    }

    public void setCust_spid(int cust_spid) {
        this.cust_spid = cust_spid;
    }

    public String getCust_first_name() {
        return cust_first_name;
    }

    public void setCust_first_name(String cust_first_name) {
        this.cust_first_name = cust_first_name;
    }

    public String getCust_last_name() {
        return cust_last_name;
    }

    public void setCust_last_name(String cust_last_name) {
        this.cust_last_name = cust_last_name;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public String getCust_landline() {
        return cust_landline;
    }

    public void setCust_landline(String cust_landline) {
        this.cust_landline = cust_landline;
    }

    public String getCust_alternate_contact() {
        return cust_alternate_contact;
    }

    public void setCust_alternate_contact(String cust_alternate_contact) {
        this.cust_alternate_contact = cust_alternate_contact;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_requirement() {
        return cust_requirement;
    }

    public void setCust_requirement(String cust_requirement) {
        this.cust_requirement = cust_requirement;
    }

    public String getCust_budget() {
        return cust_budget;
    }

    public void setCust_budget(String cust_budget) {
        this.cust_budget = cust_budget;
    }

    public String getCust_preferred_location() {
        return cust_preferred_location;
    }

    public void setCust_preferred_location(String cust_preferred_location) {
        this.cust_preferred_location = cust_preferred_location;
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

    public String getCust_profile_pic() {
        return cust_profile_pic;
    }

    public void setCust_profile_pic(String cust_profile_pic) {
        this.cust_profile_pic = cust_profile_pic;
    }

    public String getCust_area() {
        return cust_area;
    }

    public void setCust_area(String cust_area) {
        this.cust_area = cust_area;
    }

    public String getCust_uid() {
        return cust_uid;
    }

    public void setCust_uid(String cust_uid) {
        this.cust_uid = cust_uid;
    }

    public String getCust_occupation() {
        return cust_occupation;
    }

    public void setCust_occupation(String cust_occupation) {
        this.cust_occupation = cust_occupation;
    }

    public String getCust_pan_no() {
        return cust_pan_no;
    }

    public void setCust_pan_no(String cust_pan_no) {
        this.cust_pan_no = cust_pan_no;
    }

    public String getCust_son_of() {
        return cust_son_of;
    }

    public void setCust_son_of(String cust_son_of) {
        this.cust_son_of = cust_son_of;
    }

    public String getCust_marital_status() {
        return cust_marital_status;
    }

    public void setCust_marital_status(String cust_marital_status) {
        this.cust_marital_status = cust_marital_status;
    }

    public String getCust_dob() {
        return cust_dob;
    }

    public void setCust_dob(String cust_dob) {
        this.cust_dob = cust_dob;
    }

    public int getCust_rating() {
        return cust_rating;
    }

    public void setCust_rating(int cust_rating) {
        this.cust_rating = cust_rating;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getCustomerSelectedFlag() {
        return customerSelectedFlag;
    }

    public void setCustomerSelectedFlag(int customerSelectedFlag) {
        this.customerSelectedFlag = customerSelectedFlag;
    }

    public int getPlotLikeFlag() {
        return plotLikeFlag;
    }

    public void setPlotLikeFlag(int plotLikeFlag) {
        this.plotLikeFlag = plotLikeFlag;
    }

    public String getCustomerTempGroupId() {
        return customerTempGroupId;
    }

    public void setCustomerTempGroupId(String customerTempGroupId) {
        this.customerTempGroupId = customerTempGroupId;
    }

    public String getCustSessKey() {
        return custSessKey;
    }

    public void setCustSessKey(String custSessKey) {
        this.custSessKey = custSessKey;
    }

    public String getTempProposalId() {
        return tempProposalId;
    }

    public void setTempProposalId(String tempProposalId) {
        this.tempProposalId = tempProposalId;
    }

    public String getCust_sync_status() {
        return cust_sync_status;
    }

    public void setCust_sync_status(String cust_sync_status) {
        this.cust_sync_status = cust_sync_status;
    }

    @Override
    public String toString() {
        return "CustomerDataModel{" +
                "cust_client_id=" + cust_client_id +
                ", cust_unique_id=" + cust_unique_id +
                ", cust_key='" + cust_key + '\'' +
                ", cust_id=" + cust_id +
                ", cust_devl_id=" + cust_devl_id +
                ", cust_spid=" + cust_spid +
                ", cust_first_name='" + cust_first_name + '\'' +
                ", cust_last_name='" + cust_last_name + '\'' +
                ", cust_mobile='" + cust_mobile + '\'' +
                ", cust_landline='" + cust_landline + '\'' +
                ", cust_alternate_contact='" + cust_alternate_contact + '\'' +
                ", cust_address='" + cust_address + '\'' +
                ", cust_email='" + cust_email + '\'' +
                ", cust_requirement='" + cust_requirement + '\'' +
                ", cust_budget='" + cust_budget + '\'' +
                ", cust_preferred_location='" + cust_preferred_location + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", cust_profile_pic='" + cust_profile_pic + '\'' +
                ", cust_area='" + cust_area + '\'' +
                ", cust_uid='" + cust_uid + '\'' +
                ", cust_occupation='" + cust_occupation + '\'' +
                ", cust_pan_no='" + cust_pan_no + '\'' +
                ", cust_son_of='" + cust_son_of + '\'' +
                ", cust_marital_status='" + cust_marital_status + '\'' +
                ", cust_dob='" + cust_dob + '\'' +
                ", cust_rating=" + cust_rating +
                ", deleted_at='" + deleted_at + '\'' +
                ", customerSelectedFlag=" + customerSelectedFlag +
                ", plotLikeFlag=" + plotLikeFlag +
                ", customerTempGroupId='" + customerTempGroupId + '\'' +
                ", custSessKey='" + custSessKey + '\'' +
                ", tempProposalId='" + tempProposalId + '\'' +
                ", cust_sync_status='" + cust_sync_status + '\'' +
                '}';
    }
}
