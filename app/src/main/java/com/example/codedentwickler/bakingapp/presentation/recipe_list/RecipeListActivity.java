package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.presentation.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity {

    @BindView(R.id.recipe_list)
    RecyclerView mMovieRecyclerView;

    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        setUnBinder(ButterKnife.bind(this));

        mRecipeAdapter = new RecipeAdapter(new ArrayList<>(0),
                recipe -> navigateToRecipeDetails(recipe));
    }

    private void navigateToRecipeDetails(Recipe recipe) {


    }

    @Override
    public void showSnackBarMessage(String message) {

    }
}
