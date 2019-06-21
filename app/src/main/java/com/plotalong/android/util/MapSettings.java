package com.plotalong.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.plotalong.android.dataSource.TrnSessionDataSource;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shantanu on 24/5/17.
 */

public class MapSettings {
    private final String TAG = getClass().getSimpleName();
    private final GoogleMap map;
    Context context;

    public MapSettings(GoogleMap map){
        this.map = map;
    }
    public GoogleMap onFocusMapOptions(){
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);
        map.setMyLocationEnabled(true);
        return map;
    }

    public GoogleMap preFocusMapOptions(){
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setPadding(75,25,25,30);
        return map;
    }

    public void mapSnapShot(GoogleMap mMap, final String traceId, final File path, String customerGroupId, Context context, String sessionKey) {
        String filename = traceId + ".jpg";
        File file = new File(path,filename);
        this.context = context;

        TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(context);
        trnSessionDataSource.updateSessionMapScreenShot(customerGroupId,sessionKey,file.getPath(),traceId);

        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            Bitmap bitmap;
            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                bitmap = snapshot;
                try {
                    Log.e(TAG, "onSnapshotReady: " );
                    saveImageToCardAndDatabase(snapshot, traceId, path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mMap.snapshot(callback);
    }

    private void saveImageToCardAndDatabase(Bitmap bm, String traceId, File path) {

        String fname = traceId + ".jpg";
        File file = new File(path, fname);

        Log.i(TAG, "" + file);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GoogleMap onLoseFocusMapOptions(){
        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setAllGesturesEnabled(false);
        map.setMyLocationEnabled(false);
        return map;
    }
}
