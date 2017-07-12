package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 6/9/17.
 */

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private final OnItemClickListener mItemClickListener;
    private List<Recipe> recipes;

    RecipeListAdapter(List<Recipe> recipes, OnItemClickListener mItemClickListener) {
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

    List<Recipe> getRecipes() {
        if (!recipes.isEmpty()) return recipes;
        return new ArrayList<>();
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

        @BindView(R.id.list_recipe_name)
        TextView nameTextView;
        @BindView(R.id.list_recipe_no_of_ingredients)
        TextView noOfIngredientTextView;

        @BindView(R.id.recipe_thumbnail)
        ImageView recipeThumbnailImageView;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        void bind(final Recipe recipe,final OnItemClickListener itemClickListener) {

            String noOfIngredients = itemView.getContext().getString(
                    R.string.recipe_list_ingredients_text,recipe.getIngredients().size());

            nameTextView.setText(recipe.getName());
            noOfIngredientTextView.setText(noOfIngredients);

            if (!recipe.getImage().isEmpty()) {
                recipeThumbnailImageView.setVisibility(View.VISIBLE);

                Glide.with(itemView.getContext())
                        .load(recipe.getImage())
                        .centerCrop()
                        .into(recipeThumbnailImageView);
            }


            itemView.setOnLongClickListener(
                    new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return itemClickListener.onRecipeLongClicked(itemView, ViewHolder.this.getAdapterPosition());
                        }
                    });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onRecipeClicked(recipe);
                }
            });
        }
    }

    interface OnItemClickListener {
        void onRecipeClicked(Recipe recipe);

        boolean onRecipeLongClicked(View addWidget, int adapterPosition);
    }
}
