package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.injection.Injection;
import com.example.codedentwickler.bakingapp.presentation.base.BaseActivity;
import com.example.codedentwickler.bakingapp.presentation.recipe_details.RecipeDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends BaseActivity
        implements RecipeListContract.View {

    public static final String RECIPE_KEY = "RECIPE_KEY";

    @BindView(R.id.recipe_list)
    RecyclerView mRecipeCards;

    @BindInt(R.integer.grid_column_count)
    int columnCount;

    private RecipeListAdapter mRecipeListAdapter;
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

        mRecipeListAdapter = new RecipeListAdapter(new ArrayList<>(0),
                recipe -> mPresenter.navigateToRecipeSteps(recipe));

        setUpRecipeRecycler();

        mPresenter.loadRecipes();
    }

    private void setUpRecipeRecycler(){

        GridLayoutManager layoutManager =
                new GridLayoutManager(this, columnCount);
        mRecipeCards.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecipeCards.getContext() ,
                layoutManager.getOrientation());

        mRecipeCards.addItemDecoration(dividerItemDecoration);

        mRecipeCards.setHasFixedSize(true);
        mRecipeCards.setItemAnimator(new DefaultItemAnimator());
        mRecipeCards.setAdapter(mRecipeListAdapter);

    }

    @Override
    public void showToRecipeDetails(Recipe recipe) {

        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra(RECIPE_KEY, recipe);
        startActivity(intent);

    }

    @Override
    public void showSnackBarMessage(String message) {
        onError(message);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        mRecipeListAdapter.replaceData(recipes);
        mRecipeCards.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
