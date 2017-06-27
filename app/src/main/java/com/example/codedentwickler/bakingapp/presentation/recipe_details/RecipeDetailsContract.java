package com.example.codedentwickler.bakingapp.presentation.recipe_details;

import com.example.codedentwickler.bakingapp.data.remote.model.Ingredient;
import com.example.codedentwickler.bakingapp.data.remote.model.Step;
import com.example.codedentwickler.bakingapp.presentation.base.MvpPresenter;
import com.example.codedentwickler.bakingapp.presentation.base.MvpView;

import java.util.List;

/**
 * Created by codedentwickler on 6/15/17.
 */

interface RecipeDetailsContract {

    interface View extends MvpView {

        void showSteps(List<Step> steps);

        void showIngredients(List<Ingredient> ingredients);

        void showRecipeNameInAppBar(String recipeName);

        void showStepDetails(int stepId);

        void showStepsDetailInContainer(int stepId);

        void setPresenter(Presenter presenter);
    }

    interface Presenter extends MvpPresenter<RecipeDetailsContract.View> {

        void loadRecipeName();

        void loadIngredients();

        void loadSteps();

        void loadStepData(int stepId);

        void navigateToStepDetails(int stepId);

    }
    
}
