package com.plotalong.android.model.commonModel;

import android.support.v7.widget.RecyclerView;

import android.view.View;

/**
 * Created by shantanu on 3/11/17.
 */

public class CustomerVisitModel {
    String imageString;
    String date;
    String plotId;
    String proposalNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getProposalNo() {
        return proposalNo;
    }

    public void setProposalNo(String proposalNo) {
        this.proposalNo = proposalNo;
    }

    @Override
    public String toString() {
        return "CustomerVisitModel{" +
                "imageString='" + imageString + '\'' +
                ", date='" + date + '\'' +
                ", plotId='" + plotId + '\'' +
                ", proposalNo='" + proposalNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}