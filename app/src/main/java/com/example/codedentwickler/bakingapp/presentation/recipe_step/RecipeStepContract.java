package com.example.codedentwickler.bakingapp.presentation.recipe_step;

import com.example.codedentwickler.bakingapp.data.model.Step;
import com.example.codedentwickler.bakingapp.presentation.base.MvpPresenter;
import com.example.codedentwickler.bakingapp.presentation.base.MvpView;

import java.util.List;

interface RecipeStepContract {

  interface View extends MvpView {

    void showStepsInViewpager(List<Step> steps);

    void showRecipeNameInAppBar(String recipeName);

    void moveToCurrentStepPage();
  }

  interface Presenter extends MvpPresenter<View> {

    void loadStepsToAdapter();

    void loadRecipeName();

  }
}
