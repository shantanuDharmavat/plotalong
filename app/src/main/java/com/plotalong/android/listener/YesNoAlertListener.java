package com.plotalong.android.listener;

/**
 * Created by kbhakade on 29/6/17.
 */

public interface YesNoAlertListener {
    void onYesResponse(String title);

    void onNoResponse(String title);

    void onOkResponse(String title);
}
