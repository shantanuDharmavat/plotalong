package com.plotalong.android.model.quickSyncModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.plotalong.android.model.feedback.AnswerDataModel;
import com.plotalong.android.model.feedback.QuestionsDataModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 13/11/17.
 */

public class QuickSyncModel {
    @SerializedName("projects")
    @Expose
    private ArrayList<ProjectDataModel> projects;
    @SerializedName("phases")
    @Expose
    private ArrayList<PhaseDataModel> phases;
    @SerializedName("plots")
    @Expose
    private ArrayList<PlotModel> plots;
    @SerializedName("customers")
    @Expose
    private ArrayList<CustomerDataModel> customers;
    @SerializedName("group")
    @Expose
    private ArrayList<CustomerGroupDataModel> group;
    @SerializedName("proposalHeaders")
    @Expose
    private ArrayList<ProposalHeaderDataModel> proposalHeaders;
    @SerializedName("proposalDetails")
    @Expose
    private ArrayList<ProposalDetailsDataModel> proposalDetails;
    @SerializedName("address")
    @Expose
    private ArrayList<AddressDataModel> address;
    @SerializedName("questions")
    @Expose
    private ArrayList<QuestionsDataModel> questions;
    @SerializedName("feedbacks")
    @Expose
    private ArrayList<AnswerDataModel> feedbacks;
    @SerializedName("mst_features")
    @Expose
    private ArrayList<MstFeatureDataModel> mst_features;
    @SerializedName("cfg_features")
    @Expose
    private ArrayList<CfgFeatureDataModel> cfg_features;
    @SerializedName("session")
    @Expose
    private ArrayList<SessionDataModel> session;
    @SerializedName("cfg_content")
    @Expose
    private ArrayList<ContentDataModel> cfg_content;
    @SerializedName("lst")
    @Expose
    private String lst;

    public QuickSyncModel() {
    }

    public ArrayList<ProjectDataModel> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<ProjectDataModel> projects) {
        this.projects = projects;
    }

    public ArrayList<PhaseDataModel> getPhases() {
        return phases;
    }

    public void setPhases(ArrayList<PhaseDataModel> phases) {
        this.phases = phases;
    }

    public ArrayList<PlotModel> getPlots() {
        return plots;
    }

    public void setPlots(ArrayList<PlotModel> plots) {
        this.plots = plots;
    }

    public ArrayList<CustomerDataModel> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<CustomerDataModel> customers) {
        this.customers = customers;
    }

    public ArrayList<CustomerGroupDataModel> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<CustomerGroupDataModel> group) {
        this.group = group;
    }

    public ArrayList<ProposalHeaderDataModel> getProposalHeaders() {
        return proposalHeaders;
    }

    public void setProposalHeaders(ArrayList<ProposalHeaderDataModel> proposalHeaders) {
        this.proposalHeaders = proposalHeaders;
    }

    public ArrayList<ProposalDetailsDataModel> getProposalDetails() {
        return proposalDetails;
    }

    public void setProposalDetails(ArrayList<ProposalDetailsDataModel> proposalDetails) {
        this.proposalDetails = proposalDetails;
    }

    public ArrayList<AddressDataModel> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<AddressDataModel> address) {
        this.address = address;
    }

    public ArrayList<QuestionsDataModel> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionsDataModel> questions) {
        this.questions = questions;
    }

    public ArrayList<AnswerDataModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<AnswerDataModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public ArrayList<MstFeatureDataModel> getMst_features() {
        return mst_features;
    }

    public void setMst_features(ArrayList<MstFeatureDataModel> mst_features) {
        this.mst_features = mst_features;
    }

    public ArrayList<CfgFeatureDataModel> getCfg_features() {
        return cfg_features;
    }

    public void setCfg_features(ArrayList<CfgFeatureDataModel> cfg_features) {
        this.cfg_features = cfg_features;
    }

    public ArrayList<SessionDataModel> getSession() {
        return session;
    }

    public void setSession(ArrayList<SessionDataModel> session) {
        this.session = session;
    }

    public ArrayList<ContentDataModel> getCfg_content() {
        return cfg_content;
    }

    public void setCfg_content(ArrayList<ContentDataModel> cfg_content) {
        this.cfg_content = cfg_content;
    }

    public String getLst() {
        return lst;
    }

    public void setLst(String lst) {
        this.lst = lst;
    }

    @Override
    public String toString() {
        return "QuickSyncModel{" +
                "projects=" + projects +
                ", phases=" + phases +
                ", plots=" + plots +
                ", customers=" + customers +
                ", group=" + group +
                ", proposalHeaders=" + proposalHeaders +
                ", proposalDetails=" + proposalDetails +
                ", address=" + address +
                ", questions=" + questions +
                ", feedbacks=" + feedbacks +
                ", mst_features=" + mst_features +
                ", cfg_features=" + cfg_features +
                ", session=" + session +
                ", cfg_content=" + cfg_content +
                ", lst='" + lst + '\'' +
                '}';
    }
}