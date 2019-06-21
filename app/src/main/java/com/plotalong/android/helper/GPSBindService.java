package com.plotalong.android.helper;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.plotalong.android.util.GlobalConstant;


/**
 * Created by shantanu on 31/5/17.
 */

public class GPSBindService extends Service implements
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = GPSBindService.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    public static Location mCurrentLocation;
    private final IBinder binder = new GpsBinder();
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        initiateGooglePlayService();
    }

    public void initiateGooglePlayService() {
        Log.e(TAG, "initiateGooglePlayService: ");
        if (isGooglePlayServicesAvailable()) {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(GlobalConstant.LOCATION_UPDATE_TIME);
            mLocationRequest.setFastestInterval(GlobalConstant.LOCATION_UPDATE_TIME);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            mLocationRequest.setSmallestDisplacement(10.0f);  /* min dist for location change, here it is 10 meter */
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            mGoogleApiClient.connect();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        mGoogleApiClient.disconnect();
        return false;
    }

    private boolean isGooglePlayServicesAvailable() {
        Log.e(TAG, "isGooglePlayServicesAvailable: ");
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        return ConnectionResult.SUCCESS == status;
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.e(TAG, "onConnected: ");
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "onConnectionSuspended: ");
    }

    protected void startLocationUpdates() {
        Log.e(TAG, "startLocationUpdates: ");
        try {
            PendingResult<Status> pendingResult;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        } catch (IllegalStateException ignored) {
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged: " + location.getLongitude());
        if (mGoogleApiClient.isConnected()) {
            Log.e(TAG, "onLocationChanged: acc" + location.getAccuracy());
            mCurrentLocation = location;
            Intent intent = new Intent("GPSLocationUpdates");
            intent.putExtra("location", location);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            Toast.makeText(this, "Bound Service = "+location.getLongitude(), Toast.LENGTH_SHORT).show();

//            EventBus.getDefault().post(new LoginEvent("KAILAS", 0));
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: ");
    }

    public class GpsBinder extends Binder {
        public GPSBindService getService() {
            return GPSBindService.this;
        }
    }
}