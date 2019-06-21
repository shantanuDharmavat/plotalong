package com.plotalong.android.model.quickSyncModel;

import com.plotalong.android.model.commonModel.LocationParsingModel;
import com.plotalong.android.model.feedback.AnswerDataModel;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;


/**
 * Created by kbhakade on 8/6/17.
 */

public class QuickSyncRequestDataModel {
    private String devl_id;
    private ArrayList<CustomerDataModel> customers;
    private ArrayList<CustomerGroupDataModel> groups;
    private ArrayList<AddressDataModel> adrresses;
    private ArrayList<ProposalHeaderDataModel> proposalHeaders;
    private ArrayList<ProposalDetailsDataModel> proposalDetails;
    private ArrayList<SessionDataModel> sessions;
    private ArrayList<AnswerDataModel> feedbacks;
    private ArrayList<ContentDataModel> contents;
    private ArrayList<LocationParsingModel> trace;

    public QuickSyncRequestDataModel() {
    }

    public QuickSyncRequestDataModel(String devl_id, ArrayList<CustomerDataModel> customers, ArrayList<CustomerGroupDataModel> groups, ArrayList<AddressDataModel> adrresses, ArrayList<ProposalHeaderDataModel> proposalHeaders, ArrayList<ProposalDetailsDataModel> proposalDetails, ArrayList<SessionDataModel> sessions, ArrayList<AnswerDataModel> feedbacks, ArrayList<ContentDataModel> contents, ArrayList<LocationParsingModel> trace) {
        this.devl_id = devl_id;
        this.customers = customers;
        this.groups = groups;
        this.adrresses = adrresses;
        this.proposalHeaders = proposalHeaders;
        this.proposalDetails = proposalDetails;
        this.sessions = sessions;
        this.feedbacks = feedbacks;
        this.contents = contents;
        this.trace = trace;
    }

    public String getDevl_id() {
        return devl_id;
    }

    public void setDevl_id(String devl_id) {
        this.devl_id = devl_id;
    }

    public ArrayList<CustomerDataModel> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<CustomerDataModel> customers) {
        this.customers = customers;
    }

    public ArrayList<CustomerGroupDataModel> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<CustomerGroupDataModel> groups) {
        this.groups = groups;
    }

    public ArrayList<AddressDataModel> getAdrresses() {
        return adrresses;
    }

    public void setAdrresses(ArrayList<AddressDataModel> adrresses) {
        this.adrresses = adrresses;
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

    public ArrayList<SessionDataModel> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<SessionDataModel> sessions) {
        this.sessions = sessions;
    }

    public ArrayList<AnswerDataModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<AnswerDataModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public ArrayList<ContentDataModel> getContents() {
        return contents;
    }

    public void setContents(ArrayList<ContentDataModel> contents) {
        this.contents = contents;
    }

    public ArrayList<LocationParsingModel> getTrace() {
        return trace;
    }

    public void setTrace(ArrayList<LocationParsingModel> trace) {
        this.trace = trace;
    }

    @Override
    public String toString() {
        return "QuickSyncRequestDataModel{" +
                "devl_id='" + devl_id + '\'' +
                ", customers=" + customers +
                ", groups=" + groups +
                ", adrresses=" + adrresses +
                ", proposalHeaders=" + proposalHeaders +
                ", proposalDetails=" + proposalDetails +
                ", sessions=" + sessions +
                ", feedbacks=" + feedbacks +
                ", contents=" + contents +
                ", trace=" + trace +
                '}';
    }
}