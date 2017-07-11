package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;
import com.example.codedentwickler.bakingapp.idlingresource.RecipesIdlingResource;
import com.example.codedentwickler.bakingapp.presentation.base.MvpPresenter;
import com.example.codedentwickler.bakingapp.presentation.base.MvpView;

import java.util.List;

/**
 * Created by codedentwickler on 6/10/17.
 */

interface RecipeListContract {

    interface View extends MvpView {

        void showRecipes(List<Recipe> recipes);

        void showToRecipeDetails(Recipe recipe);
    }

    interface Presenter extends MvpPresenter<RecipeListContract.View> {

        void loadRecipes(RecipesIdlingResource idlingResource);

        void navigateToRecipeSteps(Recipe recipe);
    }
}
