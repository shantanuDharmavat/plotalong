package com.plotalong.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.commonModel.DirectionFromDatabaseModel;

/**
 * Created by shantanu on 8/8/17.
 */

public class DirectionListAdapter extends ArrayAdapter<DirectionFromDatabaseModel> {
    private int resource;
    private Context context;
    private DirectionFromDatabaseModel[] model;

    public DirectionListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull DirectionFromDatabaseModel[] objects) {
        super(context, resource, objects);
        this.model = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        view = inflater.inflate(resource,parent,false);

        String cityName = model[position].getLocation();
        String cityDistance = model[position].getDistance();

        TextView city = (TextView) view.findViewById(R.id.editTextCityName);
        TextView distance = (TextView) view.findViewById(R.id.editTextDistance);

        city.setText(cityName);
        distance.setText(cityDistance);

        return view;
    }
}
