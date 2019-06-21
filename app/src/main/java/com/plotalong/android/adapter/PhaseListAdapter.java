package com.plotalong.android.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.listener.ProjectExpandOptionListener;
import com.plotalong.android.model.quickSyncModel.PhaseDataModel;
import com.plotalong.android.util.GlobalConstant;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by shantanu on 20/7/17.
 */

public class PhaseListAdapter extends RecyclerView.Adapter<PhaseListAdapter.MyViewHolder> {
    private static final String TAG = PhaseListAdapter.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);
    private final Context context;
    private ArrayList<PhaseDataModel> phaseDataModelArrayList;
    private ProjectExpandOptionListener projectExpandOptionListener;

    public PhaseListAdapter(ArrayList<PhaseDataModel> phaseDataModels, Context context, ProjectExpandOptionListener projectExpandOptionListener) {
        this.phaseDataModelArrayList = phaseDataModels;
        this.context = context;
        this.projectExpandOptionListener = projectExpandOptionListener;
    }

    @Override
    public PhaseListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item_row, parent, false);
        return new PhaseListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhaseListAdapter.MyViewHolder holder, int position) {
        PhaseDataModel phaseDataModel = phaseDataModelArrayList.get(position);
        holder.textViewProjectName.setText(phaseDataModel.getPhas_name());

        String absolutePath = GlobalConstant.PROJECT_FOLDER_PATH + phaseDataModel.getPhas_logo().replace("assets/user/", "");
        Uri uri = Uri.fromFile(new File(absolutePath));
        Picasso.with(context)
                .load(uri)
                .into(holder.imageViewProjectIcon);
    }

    @Override
    public int getItemCount() {
        return phaseDataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageViewProjectIcon;
        private final TextView textViewProjectName;
        private final ExpandableLayout expandableLayout;
        private final ImageView imageViewInfoOfProject;
        private final ImageView imageViewDirectionOfProject;
        private final ImageView imageViewVisitOfProject;
        private final ImageView imageViewMetingOfProject;
        private final ImageView imageViewPhotoOfProject;


        MyViewHolder(View view) {
            super(view);
            imageViewProjectIcon = view.findViewById(R.id.imageViewProjectIcon);
            textViewProjectName = view.findViewById(R.id.textViewProjectName);
            expandableLayout = view.findViewById(R.id.expandable_layout);
            imageViewInfoOfProject = view.findViewById(R.id.imageViewInfoOfProject);
            imageViewDirectionOfProject = view.findViewById(R.id.imageViewDirectionOfProject);
            imageViewVisitOfProject = view.findViewById(R.id.imageViewVisitOfProject);
            imageViewMetingOfProject = view.findViewById(R.id.imageViewMetingOfProject);
            imageViewPhotoOfProject = view.findViewById(R.id.imageViewPhotoOfProject);

            imageViewInfoOfProject.setOnClickListener(this);
            imageViewDirectionOfProject.setOnClickListener(this);
            imageViewVisitOfProject.setOnClickListener(this);
            imageViewMetingOfProject.setOnClickListener(this);
            imageViewPhotoOfProject.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: ");
                    projectExpandOptionListener.onProjectClick(expandableLayout,textViewProjectName);
                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick: ");
            switch (v.getId()) {
                case R.id.imageViewInfoOfProject:
                    expandableLayout.toggle();
                    projectExpandOptionListener.onProjectOptionClick(0, getLayoutPosition());
                    break;

                case R.id.imageViewDirectionOfProject:
                    expandableLayout.toggle();
                    projectExpandOptionListener.onProjectOptionClick(1, getLayoutPosition());
                    break;

                case R.id.imageViewVisitOfProject:
                    expandableLayout.toggle();
                    projectExpandOptionListener.onProjectOptionClick(2, getLayoutPosition());
                    break;

                case R.id.imageViewMetingOfProject:
                    expandableLayout.toggle();
                    projectExpandOptionListener.onProjectOptionClick(3, getLayoutPosition());
                    break;

                case R.id.imageViewPhotoOfProject:
                    expandableLayout.toggle();
                    projectExpandOptionListener.onProjectOptionClick(4, getLayoutPosition());
                    break;
            }
        }
    }
}