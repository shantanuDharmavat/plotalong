package com.plotalong.android.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.CustomerRecyclerListenerForLikePlot;
import com.plotalong.android.model.quickSyncModel.CustomerDataModel;
import com.plotalong.android.util.GlobalConstant;
import com.plotalong.android.util.SharedPreference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by kbhakade on 26/9/17.
 */

public class AdapterForLikeDislikePlot extends RecyclerView.Adapter<AdapterForLikeDislikePlot.ViewHolder> {
    private static final String TAG = AdapterForLikeDislikePlot.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final CustomerRecyclerListenerForLikePlot customerRecyclerListenerForLikePlot;
    private ArrayList<CustomerDataModel> customerDataModelArrayList;

    public AdapterForLikeDislikePlot(ArrayList<CustomerDataModel> customerDataModels, Context context, CustomerRecyclerListenerForLikePlot customerRecyclerListenerForLikePlot) {
        this.customerDataModelArrayList = customerDataModels;
        this.context = context;
        this.customerRecyclerListenerForLikePlot = customerRecyclerListenerForLikePlot;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_row_for_like_plot, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CustomerDataModel customerDataModel = customerDataModelArrayList.get(position);
//        if (customerDataModel.getCustomerSelectedFlag() == 1) {
//            (holder.cardViewCustomerListForPlotLike).setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary));
//            (holder.textViewCustomerNameLikePlot).setTextColor(ContextCompat.getColor(context, R.color.white));
//        }

        if (customerDataModel.getPlotLikeFlag() == 1) {
            (holder.imageViewPlotLike).setVisibility(View.VISIBLE);
        }

        holder.textViewCustomerNameLikePlot.setText(customerDataModel.getCust_first_name().concat(" " + customerDataModel.getCust_last_name()));
        int developerId = SharedPreference.getInstance(context).getIntSharedPreference(GlobalConstant.DeveloperId);
        File imgFile = new File(GlobalConstant.PROJECT_FOLDER_PATH + developerId + "/customers/" + customerDataModel.getCust_email() + ".jpg");
        if (imgFile.exists()) {
            Picasso.with(context)
                    .load(imgFile)
                    .fit()
                    .into(holder.imageViewCustomerIconForPlotLike);
        }
    }

    @Override
    public int getItemCount() {
        if (customerDataModelArrayList != null && customerDataModelArrayList.size() > 0) {
            return customerDataModelArrayList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardViewCustomerListForPlotLike;
        private final ImageView imageViewCustomerIconForPlotLike;
        private final TextView textViewCustomerNameLikePlot;
        private final ImageView imageViewPlotLike;

        ViewHolder(View view) {
            super(view);
            cardViewCustomerListForPlotLike = view.findViewById(R.id.cardViewCustomerListForPlotLike);
            imageViewCustomerIconForPlotLike = view.findViewById(R.id.imageViewCustomerIconForPlotLike);
            imageViewPlotLike = view.findViewById(R.id.imageViewPlotLike);
            textViewCustomerNameLikePlot = view.findViewById(R.id.textViewCustomerNameLikePlot);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    customerRecyclerListenerForLikePlot.onLikeClick(getLayoutPosition());
                }
            });
        }
    }
}