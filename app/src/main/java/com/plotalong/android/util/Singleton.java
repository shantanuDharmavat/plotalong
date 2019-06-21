package com.plotalong.android.util;

import com.plotalong.android.model.quickSyncModel.PhaseDataModel;

/**
 * Created by shantanu on 18/7/17.
 */

public class Singleton {
    private static final Singleton ourInstance = new Singleton();
    public PhaseDataModel phaseDataModel;
    public int DeveloperId;
    private String DeviceId;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return ourInstance;
    }

    public int getDeveloperId() {
        return DeveloperId;
    }

    public void setDeveloperId(int developerId) {
        DeveloperId = developerId;
    }

    public PhaseDataModel getSelectedProjectPhase() {
        return phaseDataModel;
    }

    public void setSelectedProjectPhase(PhaseDataModel selectedProjectPhase) {
        this.phaseDataModel = selectedProjectPhase;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }
}
