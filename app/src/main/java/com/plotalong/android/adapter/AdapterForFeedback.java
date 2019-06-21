package com.plotalong.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.plotalong.android.R;
import com.plotalong.android.model.feedback.QuestionsDataModel;

import java.util.ArrayList;

/**
 * Created by kbhakade on 25/10/17.
 */

public class AdapterForFeedback extends RecyclerView.Adapter<AdapterForFeedback.ViewHolder> {
    private ArrayList<QuestionsDataModel> questionsDataModelArrayList;

    public AdapterForFeedback(ArrayList<QuestionsDataModel> questionsDataModelArrayList) {
        this.questionsDataModelArrayList = questionsDataModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_questionaries_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        questionsDataModelArrayList.get(position).setAns_text("-1");
        QuestionsDataModel questionDataModel = questionsDataModelArrayList.get(position);
        holder.textViewFeedbackQuestion.setText(questionDataModel.getQue_text());
        holder.ratingBarFeedbackQuestion.setNumStars(questionDataModel.getQue_ans_range());
    }

    @Override
    public int getItemCount() {
        if (questionsDataModelArrayList.size() > 0) {
            return questionsDataModelArrayList.size();
        }
        return 0;
    }

    public ArrayList<QuestionsDataModel> getList() {
        return questionsDataModelArrayList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFeedbackQuestion;
        RatingBar ratingBarFeedbackQuestion;

        ViewHolder(View view) {
            super(view);
            textViewFeedbackQuestion = view.findViewById(R.id.textViewFeedbackQuestion);
            ratingBarFeedbackQuestion = view.findViewById(R.id.ratingBarFeedbackQuestion);

            ratingBarFeedbackQuestion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    questionsDataModelArrayList.get(getLayoutPosition()).setAns_text(String.valueOf(ratingBar.getRating()));
                }
            });
        }
    }
}