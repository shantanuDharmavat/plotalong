package com.plotalong.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.ProposalRecyclerListener;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.model.quickSyncModel.ProposalHeaderDataModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 13/9/17.
 */

public class ProposalAdapter extends RecyclerView.Adapter<ProposalAdapter.ViewHolder> {
    private static final String TAG = ProposalAdapter.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final ProposalRecyclerListener proposalRecyclerListener;
    private final CustomerDataModel customerDataModel;
    private final int customerPosition;
    private ArrayList<ProposalHeaderDataModel> proposalDataModelArrayList;

    public ProposalAdapter(ArrayList<ProposalHeaderDataModel> proposalHeaderDataModels, Context context, ProposalRecyclerListener proposalRecyclerListener, CustomerDataModel customerDataModel, int position) {
        this.proposalDataModelArrayList = proposalHeaderDataModels;
        this.proposalRecyclerListener = proposalRecyclerListener;
        this.customerDataModel = customerDataModel;
        this.customerPosition = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.proposal_list_adapter_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ProposalHeaderDataModel proposalDataModel = proposalDataModelArrayList.get(position);
        if (proposalDataModel.getProp_id() == 0) {
            holder.proposalTextView.setText(GlobalConstant.String_NEW);
        } else {
            holder.proposalTextView.setText(GlobalConstant.String_PROPOSAL + proposalDataModel.getProp_id());
        }
    }

    @Override
    public int getItemCount() {
        if (proposalDataModelArrayList.size() > 0) {
            return proposalDataModelArrayList.size();
        }
        return 0;
    }

    public ProposalHeaderDataModel getProposalModel(int position) {
        return proposalDataModelArrayList.get(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView proposalTextView;

        ViewHolder(View view) {
            super(view);
            proposalTextView = view.findViewById(R.id.proposalTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    proposalRecyclerListener.onProposalClick(getLayoutPosition(), customerPosition, customerDataModel);
                }
            });
        }
    }
}