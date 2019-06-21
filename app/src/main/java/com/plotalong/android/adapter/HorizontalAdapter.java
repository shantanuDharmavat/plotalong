package com.plotalong.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.plotalong.android.R;
import com.plotalong.android.activity.MainActivity;
import com.plotalong.android.model.geoGalleryModel.ContentDataModel;
import com.plotalong.android.util.Utility;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import static com.plotalong.android.R.id.imageViewGalleryRowItem;

/**
 * Created by shantanu on 28/9/17.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    ArrayList<ContentDataModel> list;
    Context context;
    private String TAG = getClass().getSimpleName();

    public HorizontalAdapter(ArrayList<ContentDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_image_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ContentDataModel model = list.get(position);

        Log.e(TAG, "onBindViewHolder: " + Utility.validateURIForGallery(model.getCont_file_location()) );
        Bitmap bMap;
        Uri uri = Uri.fromFile(new File(Utility.validateURIForGallery(model.getCont_file_location()) ));

        if (model.getCont_file_type().equalsIgnoreCase("mp4")) {
            bMap = ThumbnailUtils.createVideoThumbnail(uri.getPath(), MediaStore.Video.Thumbnails.MICRO_KIND);

            Bitmap bitmapOverLay = Utility.overLayBitmapWithPlaySymbol(bMap,context);

            holder.imageView.setImageBitmap(bitmapOverLay);

        }else if(model.getCont_file_type().equalsIgnoreCase("pdf")){
            ViewGroup.LayoutParams paramas = holder.imageView.getLayoutParams();
            paramas.height = 100;
            paramas.width = 100;
            holder.imageView.setLayoutParams(paramas);
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pdf));
        }else {
            Log.e(TAG, "onBindViewHolder: PICASSO START" );
            Picasso.with(context)
                    .load(uri)
                    .resize(100, 100)
                    .centerCrop()
                    .into(holder.imageView);
            Log.e(TAG, "onBindViewHolder: PICASSO END" );


        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).imageGalleryToggle(list,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public VideoView videoView;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(imageViewGalleryRowItem);
        }
    }
}