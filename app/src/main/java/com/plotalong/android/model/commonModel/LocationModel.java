package com.plotalong.android.model.commonModel;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by shantanu on 13/9/17.
 */

public class LocationModel {
    private double latitude, longitude, altitude;
    private float accuracy;
    private long time;
    private String traceId;
    private String trace_sync_status;

    public String getTrace_sync_status() {
        return trace_sync_status;
    }

    public void setTrace_sync_status(String trace_sync_status) {
        this.trace_sync_status = trace_sync_status;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }
}
