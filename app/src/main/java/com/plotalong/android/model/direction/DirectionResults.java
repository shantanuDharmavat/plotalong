package com.plotalong.android.model.direction;

import com.google.gson.annotations.SerializedName;
import com.plotalong.android.model.commonModel.*;

import java.util.List;

/**
 * Created by shantanu on 2/8/17.
 */

public class DirectionResults {
    @SerializedName("routes")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }
}