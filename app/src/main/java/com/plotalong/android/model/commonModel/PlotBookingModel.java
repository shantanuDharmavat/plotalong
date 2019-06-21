package com.plotalong.android.model.commonModel;

import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.PlotModel;

/**
 * Created by shantanu on 20/9/17.
 */

public class PlotBookingModel {
    PlotModel plotnumber;
    CustomerDataModel customerDataModel;
    boolean hasBookedPlotFlag = false;

    public boolean getHasBookedPlotFlag() {
        return hasBookedPlotFlag;
    }

    public void setHasBookedPlotFlag(boolean hasBookedPlotFlag) {
        this.hasBookedPlotFlag = hasBookedPlotFlag;
    }

    public PlotModel getPlotnumber() {
        return plotnumber;
    }

    public void setPlotnumber(PlotModel plotnumber) {
        this.plotnumber = plotnumber;
    }

    public CustomerDataModel getCustomerDataModel() {
        return customerDataModel;
    }

    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        this.customerDataModel = customerDataModel;
    }
}
