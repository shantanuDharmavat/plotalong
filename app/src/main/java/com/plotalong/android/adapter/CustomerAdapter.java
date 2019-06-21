package com.plotalong.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.CustomerRecyclerListener;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by kbhakade on 1/8/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private static final String TAG = CustomerAdapter.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final CustomerRecyclerListener customerRecyclerListener;
    private ArrayList<CustomerDataModel> customerDataModelArrayList;

    public CustomerAdapter(ArrayList<CustomerDataModel> customerDataModels, Context context, CustomerRecyclerListener customerRecyclerListener) {
        this.customerDataModelArrayList = customerDataModels;
        this.context = context;
        this.customerRecyclerListener = customerRecyclerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_adapter_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CustomerDataModel customerDataModel = customerDataModelArrayList.get(position);
        if (customerDataModel.getCustomerSelectedFlag() == 1) {
            (holder.customerListCardView).setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary));
            (holder.customerListCustomerNameTextView).setTextColor(ContextCompat.getColor(context, R.color.white));
            (holder.customerListEditCustomer).setVisibility(View.GONE);
            (holder.customerListRemoveCustomer).setVisibility(View.VISIBLE);
        } else {
            (holder.customerListCardView).setCardBackgroundColor(Color.WHITE);
        }
        holder.customerListCustomerNameTextView.setText(customerDataModel.getCust_first_name().concat(" " + customerDataModel.getCust_last_name()));
        int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
        File imgFile = new File(GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/customers/" + customerDataModel.getCust_email() + ".jpg");
        if (imgFile.exists()) {
            Picasso.with(context)
                    .load(imgFile)
                    .fit()
                    .into(holder.customerListCustomerProfileImageView);
        }
    }

    @Override
    public int getItemCount() {
        if (customerDataModelArrayList.size() > 0) {
            return customerDataModelArrayList.size();
        }
        return 0;
    }

    public void filterList(ArrayList<CustomerDataModel> customerDataModelArrayList) {
        this.customerDataModelArrayList = customerDataModelArrayList;
        notifyDataSetChanged();
    }

    public CustomerDataModel getCustomerModel(int position) {
        return customerDataModelArrayList.get(position);
    }

    public void onItemDismiss(int position) {
        if (position != -1 && position < customerDataModelArrayList.size()) {
            customerDataModelArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView customerListCardView;
        ImageView customerListCustomerProfileImageView;
        TextView customerListCustomerNameTextView;
        ImageView customerListEditCustomer;
        ImageView customerListRemoveCustomer;
        private RecyclerView recyclerViewForProposals;
        private ExpandableLayout expandableLayout;

        ViewHolder(View view) {
            super(view);
            customerListCardView = (CardView) view.findViewById(R.id.customerListCardView);
            customerListCustomerProfileImageView = (ImageView) view.findViewById(R.id.customerListCustomerProfileImageView);
            customerListCustomerNameTextView = (TextView) view.findViewById(R.id.customerListCustomerNameTextView);
            customerListEditCustomer = (ImageView) view.findViewById(R.id.customerListEditCustomer);
            customerListRemoveCustomer = (ImageView) view.findViewById(R.id.customerListRemoveCustomer);
            expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandableLayoutForProposalsList);
            recyclerViewForProposals = (RecyclerView) view.findViewById(R.id.recyclerViewForProposals);

            customerListEditCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerRecyclerListener.onEditCustomer(getLayoutPosition());
                }
            });

            customerListRemoveCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerRecyclerListener.onRemoveCustomer(getLayoutPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    customerRecyclerListener.onCustomerClicked(getLayoutPosition(), recyclerViewForProposals, expandableLayout);
                }
            });
        }
    }
}