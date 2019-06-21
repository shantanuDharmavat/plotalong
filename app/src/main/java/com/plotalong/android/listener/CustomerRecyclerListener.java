package com.plotalong.android.listener;

import android.support.v7.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by kbhakade on 11/8/17.
 */

public interface CustomerRecyclerListener {
    void onCustomerClicked(int position, RecyclerView recyclerViewForProposals, ExpandableLayout expandableLayout);

    void onRemoveCustomer(int position);

    void onEditCustomer(int position);
}
