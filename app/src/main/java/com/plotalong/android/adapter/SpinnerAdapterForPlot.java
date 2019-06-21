package com.plotalong.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.quickSyncModel.PlotModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 18/9/17.
 */

public class SpinnerAdapterForPlot extends ArrayAdapter<PlotModel> {
    private final Context context;
    private final ArrayList<PlotModel> plotModelArrayList;

    public SpinnerAdapterForPlot(ArrayList<PlotModel> plotModelArrayList, int textViewResourceId, Context context) {
        super(context, textViewResourceId, plotModelArrayList);
        this.context = context;
        this.plotModelArrayList = plotModelArrayList;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_text_with_card, parent, false);
        TextView label = (TextView) row.findViewById(R.id.textViewPlotNumber);
        label.setText(plotModelArrayList.get(position).getPlot_number());
        return row;
    }
}