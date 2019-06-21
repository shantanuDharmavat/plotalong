package com.plotalong.android.requestResponseManager;

import android.content.Context;
import android.util.Log;

import com.plotalong.android.listener.DirectionsListener;
import com.plotalong.android.model.direction.DirectionResults;
import com.plotalong.android.retrofitApi.ApiClient;
import com.plotalong.android.retrofitApi.ApiInterface;
import com.plotalong.android.util.GlobalConstant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shantanu on 2/8/17.
 */

public class Directions {
    private final DirectionsListener directionsListener;
    private String TAG = Directions.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public Directions(Context context, DirectionsListener directionsListener) {
        Log.e(TAG, "Directions: ");
        this.directionsListener = directionsListener;
    }

    public void callDirections(String start, String end) {
        Log.e(TAG, "callDirections: ");
        try {
            ApiInterface apiInterface = ApiClient.createRetrofitService(ApiInterface.class, ApiInterface.directionUrl);
            apiInterface.getDirections(start, end)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<DirectionResults>() {
                        @Override
                        public void onNext(@NonNull DirectionResults directionResults) {
                            try {
                                if (directionResults != null && directionResults.getRoutes().size()>0) {
                                    Log.e(TAG, "onNext: " + directionResults.getRoutes().get(0).getOverviewPolyLine().getPoints());
                                    directionsListener.OnDirectionsSuccessful(directionResults);
                                } else {
                                    Log.e(TAG, "onNext: no data in direction result");
                                }
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: ");
                            directionsListener.OnDirectionsFail();
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: ");
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}