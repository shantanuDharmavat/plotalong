package com.plotalong.android.model.plotJsonModel;

/**
 * Created by shantanu on 21/8/17.
 */

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public LatLng getLatLng(){
        Double dLat = Double.valueOf(lat);
        Double dLon = Double.valueOf(_long);
        return new LatLng(dLat,dLon);
    }


}