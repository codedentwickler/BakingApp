package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.injection.Injection;
import com.example.codedentwickler.bakingapp.presentation.base.BaseActivity;
import com.example.codedentwickler.bakingapp.presentation.recipe_video.RecipeVideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity
        implements RecipeListContract.View {

    @BindView(R.id.recipe_list)
    RecyclerView mRecipeCards;

    private RecipeAdapter mRecipeAdapter;

    private RecipeListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        setUnBinder(ButterKnife.bind(this));

        mPresenter = new RecipeListPresenter(
                Injection.provideRecipeRepo(getApplicationContext()),
                Injection.provideSchedulerProvider()
        );
        mPresenter.attachView(this);

        mRecipeAdapter = new RecipeAdapter(new ArrayList<>(0),
                recipe -> mPresenter.navigateToRecipeDetails(recipe));

        setUpRecipeRecycler();

        mPresenter.loadRecipes();
    }

    private void setUpRecipeRecycler(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecipeCards.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecipeCards.getContext(),
                layoutManager.getOrientation());
        mRecipeCards.addItemDecoration(dividerItemDecoration);

        mRecipeCards.setHasFixedSize(true);
        mRecipeCards.setItemAnimator(new DefaultItemAnimator());
        mRecipeCards.setAdapter(mRecipeAdapter);

    }

    @Override
    public void showToRecipeDetails(Recipe recipe) {

        Intent intent = new Intent(this, RecipeVideoActivity.class);
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);
    }

    @Override
    public void showSnackBarMessage(String message) {

    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        mRecipeAdapter.replaceData(recipes);
        mRecipeCards.setVisibility(View.VISIBLE);
    }
}
