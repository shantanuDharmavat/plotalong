package com.plotalong.android.listener;

import com.plotalong.android.model.quickSyncModel.CustomerDataModel;

/**
 * Created by kbhakade on 13/9/17.
 */

public interface ProposalRecyclerListener {
    void onProposalClick(int layoutPosition, int proposalPosition, CustomerDataModel customerDataModel);
}
