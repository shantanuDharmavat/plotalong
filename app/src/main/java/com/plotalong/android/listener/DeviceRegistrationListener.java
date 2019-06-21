package com.plotalong.android.listener;

/**
 * Created by kbhakade on 29/6/17.
 */

public interface DeviceRegistrationListener {
    void onDeviceRegistrationSuccess();

    void onDeviceRegistrationFailed(String message);
}
