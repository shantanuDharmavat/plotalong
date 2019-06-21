package com.plotalong.android.util;

import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by shantanu on 11/9/17.
 */

public class GetLocationDistance {

    public double getDistanceBetweenPoints(LatLng siteCenterLatLng, LatLng siteEdgeLatLng){
        double distance = 0;

        Location edgeLocation = new Location(LocationManager.GPS_PROVIDER);
        Location centerLocation = new Location(LocationManager.GPS_PROVIDER);

        centerLocation.setLatitude(siteCenterLatLng.latitude);
        centerLocation.setLongitude(siteCenterLatLng.longitude);

        edgeLocation.setLatitude(siteEdgeLatLng.latitude);
        edgeLocation.setLongitude(siteEdgeLatLng.longitude);

        double pointDistance = centerLocation.distanceTo(edgeLocation);

        if (pointDistance > distance)
            distance = pointDistance;

        return distance;
    }

    public LatLng getPolygonCenter(List<LatLng> list){
        double latSum = 0;
        double lngSum = 0;

        for (int i = 0; i < list.size(); i++){
            latSum += list.get(i).latitude;
            lngSum += list.get(i).longitude;
//            latList.add(list.get(i).latitude);
//            lngList.add(list.get(i).longitude);
        }

        latSum = latSum/list.size();
        lngSum = lngSum/list.size();

        return new LatLng(latSum,lngSum);
    }
}
