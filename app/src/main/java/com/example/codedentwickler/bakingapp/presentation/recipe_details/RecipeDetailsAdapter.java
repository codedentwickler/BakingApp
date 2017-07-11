package com.example.codedentwickler.bakingapp.presentation.recipe_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.remote.model.Step;

import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 6/15/17.
 */

class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.ViewHolder> {

    private final OnStepClickListener mStepClickListener;
    private List<Step> steps;

    private int currentPosition;

    RecipeDetailsAdapter(List<Step> steps, OnStepClickListener stepClickListener) {
        this.mStepClickListener = stepClickListener;
        setSteps(steps);
    }
    private void setSteps(List<Step> steps) {
        this.steps = checkNotNull(steps);
    }

    void replaceData(List<Step> steps) {
        setSteps(steps);
        notifyDataSetChanged();
    }

    @Override
    public RecipeDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_step_list_item, parent, false);

        return new RecipeDetailsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeDetailsAdapter.ViewHolder holder, int position) {
        if (steps == null) {
            return;
        }

        holder.bind(steps.get(position), position, mStepClickListener);
    }

    @Override
    public int getItemCount() {
        if (steps == null)
            return 0;
        return steps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_layout)
        RelativeLayout stepItemLayout;
        @BindView(R.id.step_description)
        TextView stepDescription;
        @BindView(R.id.step_video_icon)
        ImageView videoIcon;
        @BindColor(R.color.colorGrayBackground)
        int normalItemBackground;
        @BindColor(R.color.colorPrimaryLight)
        int currentItemBackground;

        private int currentId;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        void bind(final Step step, int position ,final OnStepClickListener itemClickListener) {

            currentId = step.getId();

            String description = step.getShortDescription();

            stepDescription.setText(String.format(Locale.ENGLISH, "%d. %s", currentId, description));

            String video = step.getVideoURL();

            if (video.isEmpty()) {
                videoIcon.setVisibility(View.INVISIBLE);
            } else {
                videoIcon.setVisibility(View.VISIBLE);
            }

            if (currentPosition == position) {
                stepItemLayout.setBackgroundColor(currentItemBackground);
            } else {
                stepItemLayout.setBackgroundColor(normalItemBackground);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = currentId;
                    itemClickListener.onStepClicked(step);
                    notifyDataSetChanged();
                }
            });
        }
    }

    interface OnStepClickListener {
        void onStepClicked(Step step);
    }
}
