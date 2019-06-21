package com.plotalong.android.model.fullSyncModel;

import com.plotalong.android.model.feedback.QuestionsDataModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.model.quickSyncModel.ProposalDetailsDataModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;
import com.plotalong.android.model.quickSyncModel.AddressDataModel;
import com.plotalong.android.model.quickSyncModel.CfgFeatureDataModel;
import com.plotalong.android.model.quickSyncModel.MstFeatureDataModel;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.model.quickSyncModel.ProjectDataModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 20/7/17.
 */

public class FullSyncDataModel {
    private String contentZip;
    private ArrayList<ProjectDataModel> projects;
    private ArrayList<PhaseDataModel> phases;
    private ArrayList<PlotModel> plots;
    private ArrayList<CustomerDataModel> customers;
    private ArrayList<CustomerGroupDataModel> group;
    private ArrayList<ProposalHeaderDataModel> proposalHeaders;
    private ArrayList<ProposalDetailsDataModel> proposalDetails;
    private ArrayList<AddressDataModel> address;
    private ArrayList<QuestionsDataModel> questions;
    private ArrayList<MstFeatureDataModel> mst_features;
    private ArrayList<CfgFeatureDataModel> cfg_features;
    private ArrayList<SessionDataModel> session;
    private ArrayList<ContentDataModel> cfg_content;

    private String lst;

    public String getContentZip() {
        return contentZip;
    }

    public void setContentZip(String contentZip) {
        this.contentZip = contentZip;
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
        return "FullSyncDataModel{" +
                "contentZip='" + contentZip + '\'' +
                ", projects=" + projects +
                ", phases=" + phases +
                ", plots=" + plots +
                ", customers=" + customers +
                ", group=" + group +
                ", proposalHeaders=" + proposalHeaders +
                ", proposalDetails=" + proposalDetails +
                ", address=" + address +
                ", questions=" + questions +
                ", mst_features=" + mst_features +
                ", cfg_features=" + cfg_features +
                ", session=" + session +
                ", cfg_content=" + cfg_content +
                ", lst='" + lst + '\'' +
                '}';
    }
}
