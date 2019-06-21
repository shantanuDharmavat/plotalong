package com.plotalong.android.model.direction;


/**
 * Created by shantanu on 2/8/17.
 */

public class Steps {
    private Location start_location;
    private Location end_location;
    private OverviewPolyLine polyline;

    public Location getStart_location() {
        return start_location;
    }

    public Location getEnd_location() {
        return end_location;
    }

    public OverviewPolyLine getPolyline() {
        return polyline;
    }
}