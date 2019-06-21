package com.plotalong.android.listener;

import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by kbhakade on 6/9/17.
 */

public interface SelectedCustomerExpandOptionListener {
    void onSelectedCustomerOptionClick(int optionPosition, int layoutPosition);

    void onAddNewCustomerInSelectedList();

    void onSelectedCustomerClick(ExpandableLayout expandableLayout, TextView selectedCustomerListCustomerName);
}
