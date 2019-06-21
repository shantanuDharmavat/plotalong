package com.plotalong.android.listener;

/**
 * Created by kbhakade on 19/7/17.
 */

public interface FullSyncListener {
    void onFullSyncSuccess(String zipFilePath);

    void onFullSyncFailed(String message);
}
