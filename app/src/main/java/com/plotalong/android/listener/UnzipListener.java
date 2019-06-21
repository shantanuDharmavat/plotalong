package com.plotalong.android.listener;

/**
 * Created by kbhakade on 4/12/17.
 */

public interface UnzipListener {
    void onUnzipSuccess();

    void onUnzipFail(String error);
}
