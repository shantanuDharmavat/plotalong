package com.plotalong.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.nearbyModel.Nearby;
import com.plotalong.android.util.Utility;
import java.util.List;

/**
 * Created by shantanu on 7/8/17.
 */

public class NearbyListAdapter extends ArrayAdapter<Nearby>{
    private Nearby[] nearbies;
    private Context mContext;
    private int layoutResourceId;
    private String TAG  = getClass().getSimpleName();

    public NearbyListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Nearby[] objects) {
        super(context, resource, objects);
        this.nearbies = objects;
        layoutResourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        view = inflater.inflate(layoutResourceId,parent,false);
        ImageView imageView = view.findViewById(R.id.imageViewIcon);
        TextView textView = view.findViewById(R.id.textViewName);
        Nearby nearby = nearbies[position];
        String catName = nearby.getCategoryType();
        String iconName = "icon_" + catName;
        String str = Utility.convertFirstLetterToUpperCase(Utility.removeCharacterFromString(catName,"_"));
        textView.setText(str);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),Utility.getResourceIdeFromString(iconName,mContext));
        imageView.setImageBitmap(bitmap);
        return view;
    }
}
