package com.plotalong.android.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.PlotSelectionListener;
import com.plotalong.android.model.quickSyncModel.PlotModel;
import com.plotalong.android.util.GlobalConstant;

import java.util.ArrayList;

/**
 * Created by kbhakade on 13/10/17.
 */

public class LikePlotByCustomerAdapter extends RecyclerView.Adapter<LikePlotByCustomerAdapter.ViewHolder> {
    private static final String TAG = LikePlotByCustomerAdapter.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private final PlotSelectionListener plotSelectionListener;
    private ArrayList<PlotModel> plotModelArrayList;

    public LikePlotByCustomerAdapter(ArrayList<PlotModel> plotModelArrayList, Context context, PlotSelectionListener plotSelectionListener) {
        this.plotModelArrayList = plotModelArrayList;
        this.context = context;
        this.plotSelectionListener = plotSelectionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.liked_plot_by_customer_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PlotModel plotModel = plotModelArrayList.get(position);
        if (plotModel.getAddToPropasalFlag() == 1) {
            holder.cardViewCustomerListForAddPlot.setBackgroundColor(ContextCompat.getColor(context, R.color.primary));
        }
        holder.textViewPlotNo.setText(plotModel.getPlot_number());
        holder.textViewPlotSize.setText(String.valueOf(plotModel.getPlot_size()));
        holder.textViewPlotStatus.setText(plotModel.getPlot_status());
        if (plotModel.getPlot_status().equals("a")) {
            holder.textViewPlotStatus.setText("Available");
            holder.cardViewCustomerListForAddPlot.setBackgroundColor(ContextCompat.getColor(context, R.color.AVAILABLE));
        }

        if (plotModel.getPlot_status().equals("r")) {
            holder.textViewPlotStatus.setText("Reserved");
            holder.cardViewCustomerListForAddPlot.setBackgroundColor(ContextCompat.getColor(context, R.color.RESERVE));
        }
        if (plotModel.getPlot_status().equals("b")) {
            holder.textViewPlotStatus.setText("Booked");
            holder.cardViewCustomerListForAddPlot.setBackgroundColor(ContextCompat.getColor(context, R.color.BOOK));
        }
        if (plotModel.getPlot_status().equals("s")) {
            holder.textViewPlotStatus.setText("Sold");
            holder.cardViewCustomerListForAddPlot.setBackgroundColor(ContextCompat.getColor(context, R.color.SOLD));
        }
    }

    @Override
    public int getItemCount() {
        if (plotModelArrayList != null && plotModelArrayList.size() > 0) {
            return plotModelArrayList.size();
        }
        return 0;
    }

    public ArrayList<PlotModel> getAddablePlots() {
        ArrayList<PlotModel> addablePlotList = new ArrayList<>();
        for (int i = 0; i < plotModelArrayList.size(); i++) {
            if (plotModelArrayList.get(i).getAddToPropasalFlag() == 1) {
                addablePlotList.add(plotModelArrayList.get(i));
            }
        }
        return addablePlotList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewPlotNo;
        private final TextView textViewPlotSize;
        private final TextView textViewPlotStatus;
        private final CardView cardViewCustomerListForAddPlot;

        ViewHolder(View view) {
            super(view);
            cardViewCustomerListForAddPlot = view.findViewById(R.id.cardViewCustomerListForAddPlot);
            textViewPlotNo = view.findViewById(R.id.textViewPlotNo);
            textViewPlotSize = view.findViewById(R.id.textViewPlotSize);
            textViewPlotStatus = view.findViewById(R.id.textViewPlotStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    plotSelectionListener.onPlotSelect(getLayoutPosition());
                }
            });
        }
    }
}