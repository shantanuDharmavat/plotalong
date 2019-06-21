package com.plotalong.android.listener;

import com.plotalong.android.model.geoGalleryModel.ContentDataModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 29/6/17.
 */

public interface QuickSyncListener {
    void onQuickSyncSuccess(ArrayList<ContentDataModel> cfg_content);

    void onQuickSyncFailed(String message);

    void onProjectChange();

    void onCallQuickSyncAgain();
}
