package com.plotalong.android.util;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.plotalong.android.model.direction.DirectionResults;
import com.plotalong.android.model.direction.Location;
import com.plotalong.android.model.direction.Route;
import com.plotalong.android.model.direction.Steps;
import com.plotalong.android.requestResponseManager.Directions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanu on 2/8/17.
 */

public class DirectionDecode {
    private final DirectionResults directionResults;
    String TAG = Directions.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public DirectionDecode(DirectionResults directionResults) {
        this.directionResults = directionResults;
    }

    public DirectionDecode() {
        this.directionResults = new DirectionResults();
    }

    public ArrayList<LatLng> decodeDirections(GoogleMap mMap) {
        ArrayList<LatLng> routelist = new ArrayList<LatLng>();
        if (directionResults.getRoutes().size() > 0) {
            ArrayList<LatLng> decodelist;
            Route routeA = directionResults.getRoutes().get(0);
            Log.e(TAG, "Legs length : " + routeA.getLegs().size());
            if (routeA.getLegs().size() > 0) {
                List<Steps> steps = routeA.getLegs().get(0).getSteps();
                Log.e(TAG, "Steps size :" + steps.size());
                Steps step;
                Location location;
                String polyline;
                for (int i = 0; i < steps.size(); i++) {
                    step = steps.get(i);
                    location = step.getStart_location();
                    routelist.add(new LatLng(location.getLat(), location.getLng()));
//                    Log.e(TAG, "Start Location :" + location.getLat() + ", " + location.getLng());
                    polyline = step.getPolyline().getPoints();
                    decodelist = RouteDecode.decodePoly(polyline);
                    routelist.addAll(decodelist);
                    location = step.getEnd_location();
                    routelist.add(new LatLng(location.getLat(), location.getLng()));
//                    Log.e(TAG,"End Location :"+location.getLat() +", "+location.getLng());
                }
            }
        }
        return routelist;
        /*Log.e(TAG,"routelist size : "+routelist.size());
        if(routelist.size()>0){
            PolylineOptions rectLine = new PolylineOptions().width(10).color(
                    Color.RED);

            for (int i = 0; i < routelist.size(); i++) {
                rectLine.add(routelist.get(i));
            }
            // Adding route on the map
            mMap.addPolyline(rectLine);
        }*/
    }

    public ArrayList<LatLng> decodeString(String polyString) {
        ArrayList<LatLng> decodeList = RouteDecode.decodePoly(polyString);
        Log.e(TAG, "decodeString: " + decodeList.size());
        return decodeList;
    }
}