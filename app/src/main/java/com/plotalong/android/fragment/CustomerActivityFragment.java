package com.plotalong.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.plotalong.android.R;
import com.plotalong.android.adapter.AdapterForUserNotes;
import com.plotalong.android.dataSource.CfgCustomerGroupDataSource;
import com.plotalong.android.dataSource.MstProposalHeaderDataSource;
import com.plotalong.android.listener.YesNoAlertListener;
import com.plotalong.android.model.proposalNotes.NotesContentsDataModel;
import com.plotalong.android.model.proposalNotes.NotesDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.CustomerGroupDataModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by shantanu on 14/10/17.
 */

public class CustomerActivityFragment extends Fragment implements YesNoAlertListener {
    private String TAG = CustomerActivityFragment.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private CustomerDataModel customerDataModel;
    private View view;
    private RecyclerView recyclerViewNotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_customer_activity, container, false);
        initControls();
        getRequiredData();
        return view;
    }


    public void setCustomerDataModel(CustomerDataModel customerDataModel) {
        Log.e(TAG, "setCustomerDetailsToControls: ");
        this.customerDataModel = customerDataModel;
    }

    private void getRequiredData() {
        Log.e(TAG, "getRequiredData: ");
        try {
            CfgCustomerGroupDataSource cfgCustomerGroupDataSource = new CfgCustomerGroupDataSource(getActivity());
            MstProposalHeaderDataSource mstProposalHeaderDataSource = new MstProposalHeaderDataSource(getActivity());
            ArrayList<ProposalHeaderDataModel> proposalHeaderDataModelArrayList = new ArrayList<>();

            ArrayList<CustomerGroupDataModel> customerGroupDataModelArrayList = cfgCustomerGroupDataSource.getAllGroups(customerDataModel.getCust_unique_id());
            if (customerGroupDataModelArrayList.size() > 0) {
                proposalHeaderDataModelArrayList = mstProposalHeaderDataSource.getAllProposalHeaders(customerGroupDataModelArrayList);
            }
            ArrayList<NotesContentsDataModel> notesContentsDataModels = new ArrayList<>();
            if (proposalHeaderDataModelArrayList.size() > 0) {
                for (int i = 0; i < proposalHeaderDataModelArrayList.size(); i++) {
                    try {
                        String notesJsonString = proposalHeaderDataModelArrayList.get(i).getProp_notes();
                        if (notesJsonString != null) {
                            Gson gson = new Gson();
                            NotesDataModel notesDataModel = gson.fromJson(notesJsonString, NotesDataModel.class);
                            ArrayList<NotesContentsDataModel> tempNotesContentsDataModels = notesDataModel.getUser_notes();
                            if (tempNotesContentsDataModels.size() > 0) {
                                for (int j = 0; j < tempNotesContentsDataModels.size(); j++) {
                                    NotesContentsDataModel notesContentsDataModel = tempNotesContentsDataModels.get(j);
                                    notesContentsDataModel.setExpiryOn(notesDataModel.getExpires_on());
                                    notesContentsDataModel.setHasConflict(notesDataModel.getHas_conflicts());
                                    notesContentsDataModel.setType(notesDataModel.getLic_type());
                                    notesContentsDataModel.setProposal(String.valueOf(proposalHeaderDataModelArrayList.get(i).getProp_id()));
                                    notesContentsDataModels.add(notesContentsDataModel);
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "getRequiredData: " + e.getMessage());
                    }
                }
            }
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewNotes.setLayoutManager(llm);
            AdapterForUserNotes adapterForUserNotes = new AdapterForUserNotes(notesContentsDataModels);
            recyclerViewNotes.setAdapter(adapterForUserNotes);
        } catch (Exception e) {
            Log.e(TAG, "getRequiredData: " + e.getMessage());
        }
    }

    private void initControls() {
        Log.e(TAG, "initControls: ");
        recyclerViewNotes = view.findViewById(R.id.recyclerViewNotes);
    }

    @Override
    public void onYesResponse(String title) {
        Log.e(TAG, "onYesResponse: ");
    }

    @Override
    public void onNoResponse(String title) {
        Log.e(TAG, "onNoResponse: ");
    }

    @Override
    public void onOkResponse(String title) {
        Log.e(TAG, "onOkResponse: ");
    }
}