package com.plotalong.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plotalong.android.R;
import com.plotalong.android.adapter.CustomerVisitListAdapter;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.TrnSessionDataSource;
import com.plotalong.android.model.commonModel.CustomerVisitModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.SessionDataModel;

import java.util.ArrayList;

/**
 * Created by shantanu on 14/10/17.
 */

public class CustomerVisitFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<SessionDataModel> sessionDataModelArrayList;
    String TAG = CustomerVisitFragment.class.getSimpleName();
    private CustomerDataModel customerDataModel;
    private ArrayList<CustomerVisitModel> customerVisitModelArrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_visit, container, false);

        recyclerView = view.findViewById(R.id.RecyclerViewSiteVisitList);
        CfgCustomerGroupDataSource dataSource = new CfgCustomerGroupDataSource(getContext());
        ArrayList<String> groupIdArray = dataSource.getCustomerGroupIdFromCustomerId(customerDataModel.getCust_unique_id());

        if (groupIdArray != null && groupIdArray.size() > 0) {
            sessionDataModelArrayList = new ArrayList<>();
            TrnSessionDataSource trnSessionDataSource = new TrnSessionDataSource(getContext());
            for (int i = 0; i < groupIdArray.size(); i++) {
                if (trnSessionDataSource.getSessionsFromGroupId(groupIdArray.get(i)) != null) {
                    sessionDataModelArrayList.addAll(trnSessionDataSource.getSessionsFromGroupId(groupIdArray.get(i)));
                }
            }
        }
        for (int i = 0; i < sessionDataModelArrayList.size(); i++) {
            customerVisitModelArrayList.add(getCustomerVisitModel(sessionDataModelArrayList.get(i)));
        }
        CustomerVisitListAdapter adapter = new CustomerVisitListAdapter(customerVisitModelArrayList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        this.customerDataModel = customerDataModel;
    }

    private CustomerVisitModel getCustomerVisitModel(SessionDataModel model) {
        CustomerVisitModel visitModel = new CustomerVisitModel();
        visitModel.setImageString(model.getSess_image_location());
        visitModel.setDate(model.getSess_start_timestamp());
        visitModel.setName(customerDataModel.getCust_first_name() + " " + customerDataModel.getCust_last_name());
        visitModel.setPlotId("- - -");
        visitModel.setProposalNo("- - -");
        return visitModel;
    }
}
