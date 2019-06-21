package com.plotalong.android.model.direction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shantanu on 2/8/17.
 */

public class Route {
    @SerializedName("overview_polyline")
    private OverviewPolyLine overviewPolyLine;

    private List<Legs> legs;

    public OverviewPolyLine getOverviewPolyLine() {
        return overviewPolyLine;
    }

    public List<Legs> getLegs() {
        return legs;
    }
}
