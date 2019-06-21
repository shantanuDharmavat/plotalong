package com.plotalong.android.model.feedback;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kbhakade on 26/10/17.
 */

public class QuestionsDataModel {
    @SerializedName("que_id")
    @Expose
    private int que_id;
    @SerializedName("que_devl_id")
    @Expose
    private int que_devl_id;
    @SerializedName("que_proj_id")
    @Expose
    private int que_proj_id;
    @SerializedName("que_phase_id")
    @Expose
    private int que_phase_id;
    @SerializedName("que_text")
    @Expose
    private String que_text;
    @SerializedName("ans_text")
    @Expose
    private String ans_text;
    @SerializedName("que_ans_type")
    @Expose
    private String que_ans_type;
    @SerializedName("que_ans_range")
    @Expose
    private int que_ans_range;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;

    public QuestionsDataModel() {
    }

    public QuestionsDataModel(int que_id, int que_devl_id, int que_proj_id, int que_phase_id, String que_text, String ans_text, String que_ans_type, int que_ans_range, String created_at, String updated_at, String deleted_at, String ans_sync_status) {
        this.que_id = que_id;
        this.que_devl_id = que_devl_id;
        this.que_proj_id = que_proj_id;
        this.que_phase_id = que_phase_id;
        this.que_text = que_text;
        this.ans_text = ans_text;
        this.que_ans_type = que_ans_type;
        this.que_ans_range = que_ans_range;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public int getQue_id() {
        return que_id;
    }

    public void setQue_id(int que_id) {
        this.que_id = que_id;
    }

    public int getQue_devl_id() {
        return que_devl_id;
    }

    public void setQue_devl_id(int que_devl_id) {
        this.que_devl_id = que_devl_id;
    }

    public int getQue_proj_id() {
        return que_proj_id;
    }

    public void setQue_proj_id(int que_proj_id) {
        this.que_proj_id = que_proj_id;
    }

    public int getQue_phase_id() {
        return que_phase_id;
    }

    public void setQue_phase_id(int que_phase_id) {
        this.que_phase_id = que_phase_id;
    }

    public String getQue_text() {
        return que_text;
    }

    public void setQue_text(String que_text) {
        this.que_text = que_text;
    }

    public String getAns_text() {
        return ans_text;
    }

    public void setAns_text(String ans_text) {
        this.ans_text = ans_text;
    }

    public String getQue_ans_type() {
        return que_ans_type;
    }

    public void setQue_ans_type(String que_ans_type) {
        this.que_ans_type = que_ans_type;
    }

    public int getQue_ans_range() {
        return que_ans_range;
    }

    public void setQue_ans_range(int que_ans_range) {
        this.que_ans_range = que_ans_range;
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
        return "QuestionsDataModel{" +
                "que_id=" + que_id +
                ", que_devl_id=" + que_devl_id +
                ", que_proj_id=" + que_proj_id +
                ", que_phase_id=" + que_phase_id +
                ", que_text='" + que_text + '\'' +
                ", ans_text='" + ans_text + '\'' +
                ", que_ans_type='" + que_ans_type + '\'' +
                ", que_ans_range=" + que_ans_range +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }
}
