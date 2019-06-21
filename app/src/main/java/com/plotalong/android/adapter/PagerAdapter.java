package com.plotalong.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.plotalong.android.fragment.CustomerActivityFragment;
import com.plotalong.android.fragment.CustomerDetailsFragment;
import com.plotalong.android.fragment.CustomerVisitFragment;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;

/**
 * Created by shantanu on 13/10/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private CustomerDataModel customerDataModel;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, CustomerDataModel customerDataModel) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.customerDataModel = customerDataModel;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CustomerDetailsFragment tab1 = new CustomerDetailsFragment();
                tab1.setCustomerDataModel(customerDataModel);
                return tab1;
            case 1:
                CustomerActivityFragment tab2 = new CustomerActivityFragment();
                tab2.setCustomerDataModel(customerDataModel);
                return tab2;
            case 2:
                CustomerVisitFragment tab3 = new CustomerVisitFragment();
                tab3.setCustomerDataModel(customerDataModel);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}