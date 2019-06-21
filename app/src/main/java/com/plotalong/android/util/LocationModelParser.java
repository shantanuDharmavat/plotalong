package com.plotalong.android.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.model.commonModel.LocationModel;
import com.plotalong.android.model.commonModel.LocationParsingModel;
import com.plotalong.android.model.commonModel.TraceModel;

import java.util.ArrayList;


/**
 * Created by shantanu on 22/11/17.
 */

public class LocationModelParser {
    Context context;
    String TAG = getClass().getSimpleName();

    public LocationModelParser(Context context) {
        this.context = context;
    }

    public ArrayList<LocationParsingModel> LocationLocalToServerParse(ArrayList<LocationModel> locationModelArrayList) {
        ArrayList<String> traceSubList = new ArrayList<>();
        TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
        ArrayList<LocationParsingModel> locationParsingModelArrayList = new ArrayList<>();

        for (int i = 0; i < locationModelArrayList.size(); i++) {
            if (!traceSubList.contains(locationModelArrayList.get(i).getTraceId())) {
                traceSubList.add(locationModelArrayList.get(i).getTraceId());
            }
        }

        for (int i = 0; i < traceSubList.size(); i++) {
            LocationParsingModel parsingModel = new LocationParsingModel();
            ArrayList<TraceModel> traceModelList = new ArrayList<>();

            int k = 0;
            for (int j = 0; j < locationModelArrayList.size(); j++) {
                if (locationModelArrayList.get(j).getTraceId().equals(traceSubList.get(i))) {
                    if (k == 0) {
                        LocationModel model = locationModelArrayList.get(j);
                        String session_key = trnSessionDataSource.getSessionKeyFromTraceId(String.valueOf(model.getTraceId()));
                        parsingModel.setTrac_session_id(session_key);
                        parsingModel.setTrac_id(String.valueOf(model.getTraceId()));
                        parsingModel.setTrac_lat(String.valueOf(model.getLatitude()));
                        parsingModel.setTrac_long(String.valueOf(model.getLongitude()));
                        parsingModel.setTrac_created_at(String.valueOf(model.getTime()));
                        k++;
                    }

                    TraceModel traceModel = new TraceModel();
                    traceModel.setLat(String.valueOf(locationModelArrayList.get(j).getLatitude()));
                    traceModel.setLng(String.valueOf(locationModelArrayList.get(j).getLongitude()));
                    traceModelList.add(traceModel);
                }
            }

            Gson gson = new Gson();
            String jsonArray = gson.toJson(traceModelList);
            parsingModel.setTrac_array(jsonArray);
            locationParsingModelArrayList.add(parsingModel);

            Log.e(TAG, "getRequestModel: parse model: " + parsingModel.toString());
        }

        return locationParsingModelArrayList;
    }
}
