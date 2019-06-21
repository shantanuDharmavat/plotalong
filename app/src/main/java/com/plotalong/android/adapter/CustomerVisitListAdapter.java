package com.plotalong.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.commonModel.CustomerVisitModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by shantanu on 6/11/17.
 */

public class CustomerVisitListAdapter extends RecyclerView.Adapter<CustomerVisitListAdapter.ViewHolderClass> {
    Context context;
    private ArrayList<CustomerVisitModel> list;

    public CustomerVisitListAdapter(ArrayList<CustomerVisitModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomerVisitListAdapter.ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_customer_site_visit_details, parent, false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(CustomerVisitListAdapter.ViewHolderClass holder, int position) {
        CustomerVisitModel model = list.get(position);
        File file = new File(model.getImageString());
        Picasso.with(context)
                .load(file)
                .placeholder(R.drawable.map_place_holder)
//                .resize(20,15)
//                .centerInside()
                .into(holder.imageView);

        holder.tvProposalNo.setText(model.getProposalNo());
        holder.tvName.setText(model.getName());
        holder.tvPlots.setText(model.getPlotId());
        holder.tvDate.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvDate, tvName, tvPlots, tvProposalNo;

        public ViewHolderClass(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custSiteVisitImageViewMap);
            tvDate = itemView.findViewById(R.id.custSiteVisitTextViewDate);
            tvName = itemView.findViewById(R.id.custSiteVisitTextViewName);
            tvPlots = itemView.findViewById(R.id.custSiteVisitTextViewPlotsLiked);
            tvProposalNo = itemView.findViewById(R.id.custSiteVisitTextViewProposalNo);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}
