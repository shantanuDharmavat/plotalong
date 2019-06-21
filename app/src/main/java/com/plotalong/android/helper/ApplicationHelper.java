package com.plotalong.android.helper;

/**
 * Created by shantanu on 17/5/17.
 */

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.plotalong.android.R;
import com.plotalong.android.util.Singleton;
import com.plotalong.android.util.Utility;

/**
 * This is a subclass of {@link Application} used to provide shared objects for this app, such as
 * the {@link Tracker}.
 */


public class ApplicationHelper extends Application {
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    private static ApplicationHelper instance;
    private static String androidDeviceId;

    public static ApplicationHelper getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAnalytics = GoogleAnalytics.getInstance(this);
        instance = this;
        androidDeviceId = Utility.getDeviceId(this);
        Singleton singleton = Singleton.getInstance();
        singleton.setDeviceId(androidDeviceId);
//        MultiDex.install(this);
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }
        return sTracker;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}