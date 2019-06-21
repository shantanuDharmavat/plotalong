package com.plotalong.android.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.proposalNotes.NotesContentsDataModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 14/12/17.
 */

public class AdapterForUserNotes extends RecyclerView.Adapter<AdapterForUserNotes.ViewHolder> {
    private ArrayList<NotesContentsDataModel> notesContentsDataModelArrayList;
    private String lastProposalId = "0";

    public AdapterForUserNotes(ArrayList<NotesContentsDataModel> notesContentsDataModelArrayList) {
        this.notesContentsDataModelArrayList = notesContentsDataModelArrayList;
        lastProposalId = "0";
    }

    @Override
    public AdapterForUserNotes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_notes, parent, false);
        return new AdapterForUserNotes.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterForUserNotes.ViewHolder holder, final int position) {
        NotesContentsDataModel notesContentsDataModel = notesContentsDataModelArrayList.get(position);
        if (lastProposalId.equals(notesContentsDataModel.getProposal())) {
            holder.linearLayoutProposal.setVisibility(View.GONE);
            holder.linearLayoutHeading.setVisibility(View.GONE);
            holder.linearLayoutParent.setWeightSum(1);
        } else {
            lastProposalId = notesContentsDataModel.getProposal();
            holder.textViewProposal.setText(notesContentsDataModel.getProposal());
            holder.textViewExpiresOn.setText(notesContentsDataModel.getExpiryOn());
            holder.linearLayoutParent.setWeightSum(3);
        }

        if (notesContentsDataModel.getHasConflict().equals("no")) {
            holder.imageViewHasConflict.setVisibility(View.GONE);
        }

        holder.textViewWhen.setText(notesContentsDataModel.getWhen());
        holder.textViewWho.setText(notesContentsDataModel.getWho());
        holder.textViewWhat.setText(notesContentsDataModel.getWhat());
    }

    @Override
    public int getItemCount() {
        if (notesContentsDataModelArrayList.size() > 0) {
            return notesContentsDataModelArrayList.size();
        }
        return 0;
    }

    public ArrayList<NotesContentsDataModel> getList() {
        return notesContentsDataModelArrayList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWhen;
        TextView textViewWho;
        TextView textViewWhat;
        LinearLayout linearLayoutParent;
        LinearLayout linearLayoutProposal;
        LinearLayout linearLayoutHeading;
        TextView textViewProposal;
        FloatingActionButton imageViewHasConflict;
        TextView textViewExpiresOn;


        ViewHolder(View view) {
            super(view);
            textViewWhen = view.findViewById(R.id.textViewWhen);
            textViewWho = view.findViewById(R.id.textViewWho);
            textViewWhat = view.findViewById(R.id.textViewWhat);
            linearLayoutParent = view.findViewById(R.id.linearLayoutParent);
            linearLayoutProposal = view.findViewById(R.id.linearLayoutProposal);
            linearLayoutHeading = view.findViewById(R.id.linearLayoutHeading);
            textViewProposal = view.findViewById(R.id.textViewProposal);
            imageViewHasConflict = view.findViewById(R.id.imageViewHasConflict);
            textViewExpiresOn = view.findViewById(R.id.textViewExpiresOn);

        }
    }
}