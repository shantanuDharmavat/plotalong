package com.plotalong.android.model.direction;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shantanu on 2/8/17.
 */

public class OverviewPolyLine {

    @SerializedName("points")
    public String points;

    public String getPoints() {
        return points;
    }
}