package com.plotalong.android.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.SelectedCustomerExpandOptionListener;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kbhakade on 4/8/17.
 */

public class SelectedCustomerAdapter extends RecyclerView.Adapter<SelectedCustomerAdapter.MyViewHolder> {
    private static final String TAG = SelectedCustomerAdapter.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final SelectedCustomerExpandOptionListener selectedCustomerExpandOptionListener;
    private ArrayList<CustomerDataModel> selectedCustomerItemList;

    public SelectedCustomerAdapter(ArrayList<CustomerDataModel> selectedCustomerItemList, SelectedCustomerExpandOptionListener selectedCustomerExpandOptionListener, Context context) {
        this.selectedCustomerItemList = selectedCustomerItemList;
        this.selectedCustomerExpandOptionListener = selectedCustomerExpandOptionListener;
        this.context = context;
    }

    @Override
    public SelectedCustomerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_customer_list_adapter_row, parent, false);
        return new SelectedCustomerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelectedCustomerAdapter.MyViewHolder holder, int position) {
        CustomerDataModel customerDataModel = selectedCustomerItemList.get(position);
        if (customerDataModel.getCust_unique_id() == -1) {
            holder.selectedCustomerListCustomerName.setVisibility(View.GONE);
            holder.selectedCustomerListCustomerProfile.setVisibility(View.GONE);
            holder.FabSelectedCustomerAddCust.setVisibility(View.VISIBLE);
//            holder.selectedCustomerListCustomerProfile.setBackgroundResource(R.drawable.ic_list_add);
        } else {
            holder.selectedCustomerListCustomerName.setText(customerDataModel.getCust_first_name().concat(" " + customerDataModel.getCust_last_name()));
            int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
            File imgFile = new File(GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/customers/" + customerDataModel.getCust_email() + ".jpg");
            if (imgFile.exists()) {
                Picasso.with(context)
                        .load(imgFile)
                        .fit()
                        .into(holder.selectedCustomerListCustomerProfile);
            }
        }
    }

    @Override
    public int getItemCount() {
        return selectedCustomerItemList.size();
    }


    public CustomerDataModel getCustomerModel(int position) {
        return selectedCustomerItemList.get(position);
    }

    public void onItemDismiss(int position) {
        if (position != -1 && position < selectedCustomerItemList.size()) {
            selectedCustomerItemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    public ArrayList<CustomerDataModel> getCustomerList() {
        return this.selectedCustomerItemList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ExpandableLayout expandableLayoutSelectedCustomer;
        private final TextView selectedCustomerListCustomerName;
        private final ImageView selectedCustomerListCustomerProfile;
        private final ImageView imageViewSelectedCustomerInfo;
        private final ImageView imageViewSelectedCustomerEndSession;
        private final FloatingActionButton FabSelectedCustomerAddCust;

        MyViewHolder(View view) {
            super(view);
            selectedCustomerListCustomerProfile = view.findViewById(R.id.selectedCustomerListCustomerProfile);
            FabSelectedCustomerAddCust = view.findViewById(R.id.FabSelectedCustomerAddCust);
            selectedCustomerListCustomerName = view.findViewById(R.id.selectedCustomerListCustomerName);
            expandableLayoutSelectedCustomer = view.findViewById(R.id.expandableLayoutSelectedCustomer);

            imageViewSelectedCustomerInfo = view.findViewById(R.id.imageViewSelectedCustomerInfo);
            imageViewSelectedCustomerEndSession = view.findViewById(R.id.imageViewSelectedCustomerEndSession);

            imageViewSelectedCustomerInfo.setOnClickListener(this);
            imageViewSelectedCustomerEndSession.setOnClickListener(this);
            FabSelectedCustomerAddCust.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    if (getLayoutPosition() + 1 == selectedCustomerItemList.size()) {
                        selectedCustomerExpandOptionListener.onAddNewCustomerInSelectedList();
                    } else {
                        selectedCustomerExpandOptionListener.onSelectedCustomerClick(expandableLayoutSelectedCustomer, selectedCustomerListCustomerName);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick: ");
            switch (v.getId()) {
                case R.id.imageViewSelectedCustomerInfo:
                    expandableLayoutSelectedCustomer.toggle();
                    selectedCustomerExpandOptionListener.onSelectedCustomerOptionClick(0, getLayoutPosition());
                    break;
                case R.id.imageViewSelectedCustomerEndSession:
                    expandableLayoutSelectedCustomer.toggle();
                    selectedCustomerExpandOptionListener.onSelectedCustomerOptionClick(1, getLayoutPosition());
                    break;
                case R.id.FabSelectedCustomerAddCust:
                    selectedCustomerExpandOptionListener.onAddNewCustomerInSelectedList();
                    break;
            }
        }
    }
}