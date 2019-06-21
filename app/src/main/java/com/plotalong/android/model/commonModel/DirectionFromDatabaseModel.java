package com.plotalong.android.model.commonModel;

/**
 * Created by shantanu on 8/8/17.
 */

public class DirectionFromDatabaseModel {
    String location, distance,overview_polyline;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(String overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    @Override
    public String toString() {
        return "DirectionFromDatabaseModel{" +
                "location='" + location + '\'' +
                ", distance='" + distance + '\'' +
                ", overview_polyline='" + overview_polyline + '\'' +
                '}';
    }
}
