package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 11/9/17.
 */

public class ProposalDetailsDataModel {
    @SerializedName("prdt_client_id")
    @Expose
    private int prdt_client_id;
    @SerializedName("prdt_prop_client_id")
    @Expose
    private int prdt_prop_client_id;
    @SerializedName("prdt_id")
    @Expose
    private int prdt_id;
    @SerializedName("prdt_propid")
    @Expose
    private int prdt_propid;
    @SerializedName("prdt_charge_id")
    @Expose
    private String prdt_charge_id;
    @SerializedName("prdt_base_price")
    @Expose
    private String prdt_base_price;
    @SerializedName("prdt_disc_amount")
    @Expose
    private String prdt_disc_amount;
    @SerializedName("prdt_sale_price")
    @Expose
    private String prdt_sale_price;
    @SerializedName("prdt_disc_reason")
    @Expose
    private String prdt_disc_reason;
    @SerializedName("prdt_notes")
    @Expose
    private String prdt_notes;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;
    @SerializedName("prdt_prod_id")
    @Expose
    private int prdt_prod_id;
    @SerializedName("prdt_prod_type")
    @Expose
    private String prdt_prod_type;
    @SerializedName("prdt_parent_id")
    @Expose
    private int prdt_parent_id;
    @SerializedName("prdt_disc_id")
    @Expose
    private int prdt_disc_id;
    @SerializedName("prdt_rate")
    @Expose
    private String prdt_rate;
    @SerializedName("prdt_qty")
    @Expose
    private int prdt_qty;
    @SerializedName("prdt_sync_status")
    @Expose
    private String prdt_sync_status;

    public ProposalDetailsDataModel() {
    }

    public ProposalDetailsDataModel(int prdt_client_id, int prdt_prop_client_id, int prdt_id, int prdt_propid, String prdt_charge_id, String prdt_base_price, String prdt_disc_amount, String prdt_sale_price, String prdt_disc_reason, String prdt_notes, String created_at, String updated_at, String deleted_at, int prdt_prod_id, String prdt_prod_type, int prdt_parent_id, int prdt_disc_id, String prdt_rate, int prdt_qty, String prdt_sync_status) {
        this.prdt_client_id = prdt_client_id;
        this.prdt_prop_client_id = prdt_prop_client_id;
        this.prdt_id = prdt_id;
        this.prdt_propid = prdt_propid;
        this.prdt_charge_id = prdt_charge_id;
        this.prdt_base_price = prdt_base_price;
        this.prdt_disc_amount = prdt_disc_amount;
        this.prdt_sale_price = prdt_sale_price;
        this.prdt_disc_reason = prdt_disc_reason;
        this.prdt_notes = prdt_notes;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.prdt_prod_id = prdt_prod_id;
        this.prdt_prod_type = prdt_prod_type;
        this.prdt_parent_id = prdt_parent_id;
        this.prdt_disc_id = prdt_disc_id;
        this.prdt_rate = prdt_rate;
        this.prdt_qty = prdt_qty;
        this.prdt_sync_status = prdt_sync_status;
    }

    public int getPrdt_client_id() {
        return prdt_client_id;
    }

    public void setPrdt_client_id(int prdt_client_id) {
        this.prdt_client_id = prdt_client_id;
    }

    public int getPrdt_prop_client_id() {
        return prdt_prop_client_id;
    }

    public void setPrdt_prop_client_id(int prdt_prop_client_id) {
        this.prdt_prop_client_id = prdt_prop_client_id;
    }

    public int getPrdt_id() {
        return prdt_id;
    }

    public void setPrdt_id(int prdt_id) {
        this.prdt_id = prdt_id;
    }

    public int getPrdt_propid() {
        return prdt_propid;
    }

    public void setPrdt_propid(int prdt_propid) {
        this.prdt_propid = prdt_propid;
    }

    public String getPrdt_charge_id() {
        return prdt_charge_id;
    }

    public void setPrdt_charge_id(String prdt_charge_id) {
        this.prdt_charge_id = prdt_charge_id;
    }

    public String getPrdt_base_price() {
        return prdt_base_price;
    }

    public void setPrdt_base_price(String prdt_base_price) {
        this.prdt_base_price = prdt_base_price;
    }

    public String getPrdt_disc_amount() {
        return prdt_disc_amount;
    }

    public void setPrdt_disc_amount(String prdt_disc_amount) {
        this.prdt_disc_amount = prdt_disc_amount;
    }

    public String getPrdt_sale_price() {
        return prdt_sale_price;
    }

    public void setPrdt_sale_price(String prdt_sale_price) {
        this.prdt_sale_price = prdt_sale_price;
    }

    public String getPrdt_disc_reason() {
        return prdt_disc_reason;
    }

    public void setPrdt_disc_reason(String prdt_disc_reason) {
        this.prdt_disc_reason = prdt_disc_reason;
    }

    public String getPrdt_notes() {
        return prdt_notes;
    }

    public void setPrdt_notes(String prdt_notes) {
        this.prdt_notes = prdt_notes;
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

    public int getPrdt_prod_id() {
        return prdt_prod_id;
    }

    public void setPrdt_prod_id(int prdt_prod_id) {
        this.prdt_prod_id = prdt_prod_id;
    }

    public String getPrdt_prod_type() {
        return prdt_prod_type;
    }

    public void setPrdt_prod_type(String prdt_prod_type) {
        this.prdt_prod_type = prdt_prod_type;
    }

    public int getPrdt_parent_id() {
        return prdt_parent_id;
    }

    public void setPrdt_parent_id(int prdt_parent_id) {
        this.prdt_parent_id = prdt_parent_id;
    }

    public int getPrdt_disc_id() {
        return prdt_disc_id;
    }

    public void setPrdt_disc_id(int prdt_disc_id) {
        this.prdt_disc_id = prdt_disc_id;
    }

    public String getPrdt_rate() {
        return prdt_rate;
    }

    public void setPrdt_rate(String prdt_rate) {
        this.prdt_rate = prdt_rate;
    }

    public int getPrdt_qty() {
        return prdt_qty;
    }

    public void setPrdt_qty(int prdt_qty) {
        this.prdt_qty = prdt_qty;
    }

    public String getPrdt_sync_status() {
        return prdt_sync_status;
    }

    public void setPrdt_sync_status(String prdt_sync_status) {
        this.prdt_sync_status = prdt_sync_status;
    }

    @Override
    public String toString() {
        return "ProposalDetailsDataModel{" +
                "prdt_client_id=" + prdt_client_id +
                ", prdt_prop_client_id=" + prdt_prop_client_id +
                ", prdt_id=" + prdt_id +
                ", prdt_propid=" + prdt_propid +
                ", prdt_charge_id='" + prdt_charge_id + '\'' +
                ", prdt_base_price='" + prdt_base_price + '\'' +
                ", prdt_disc_amount='" + prdt_disc_amount + '\'' +
                ", prdt_sale_price='" + prdt_sale_price + '\'' +
                ", prdt_disc_reason='" + prdt_disc_reason + '\'' +
                ", prdt_notes='" + prdt_notes + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", prdt_prod_id=" + prdt_prod_id +
                ", prdt_prod_type='" + prdt_prod_type + '\'' +
                ", prdt_parent_id=" + prdt_parent_id +
                ", prdt_disc_id=" + prdt_disc_id +
                ", prdt_rate='" + prdt_rate + '\'' +
                ", prdt_qty=" + prdt_qty +
                ", prdt_sync_status='" + prdt_sync_status + '\'' +
                '}';
    }
}
