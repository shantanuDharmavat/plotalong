package com.plotalong.android.listener;

import com.plotalong.android.model.direction.DirectionResults;

/**
 * Created by shantanu on 2/8/17.
 */

public interface DirectionsListener {
    void OnDirectionsSuccessful(DirectionResults directionResults);

    void OnDirectionsFail();
}
