package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.customview.ProportionalImageView;
import com.example.codedentwickler.bakingapp.data.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 6/9/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final OnItemClickListener mItemClickListener;
    private List<Recipe> recipes;

    RecipeAdapter(List<Recipe> recipes, OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        setRecipes(recipes);
    }
    private void setRecipes(List<Recipe> recipes) {
        this.recipes = checkNotNull(recipes);
    }

    void replaceData(List<Recipe> recipes) {
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (recipes == null) {
            return;
        }

        holder.bind(recipes.get(position), mItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (recipes == null)
            return 0;
        return recipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView nameTextView;
        @BindView(R.id.image_item)
        ProportionalImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        void bind(final Recipe recipe,final OnItemClickListener itemClickListener) {

            Uri uri = (Uri.parse("file:///android_asset")).buildUpon()
                    .appendEncodedPath(recipe.getImage()).build();

            Glide.with(itemView.getContext())
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(imageView);

            nameTextView.setText(recipe.getName());

            itemView.setOnClickListener(v -> itemClickListener.onItemClick(recipe));
        }
    }

    interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }
}
