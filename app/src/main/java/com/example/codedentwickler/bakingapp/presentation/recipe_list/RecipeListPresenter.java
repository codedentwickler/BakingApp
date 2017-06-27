package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import com.example.codedentwickler.bakingapp.RxUtils.schedulers.BaseSchedulerProvider;
import com.example.codedentwickler.bakingapp.data.remote.RecipeRepo;
import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;
import com.example.codedentwickler.bakingapp.presentation.base.BasePresenter;

import java.util.List;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class RecipeListPresenter extends BasePresenter<RecipeListContract.View>
        implements RecipeListContract.Presenter{

    private final RecipeRepo mRecipeRepo;
    private final BaseSchedulerProvider mSchedulerProvider;

    RecipeListPresenter(RecipeRepo mRecipeRepo, BaseSchedulerProvider mSchedulerProvider) {
        this.mRecipeRepo = mRecipeRepo;
        this.mSchedulerProvider = mSchedulerProvider;
    }


    @Override
    public void loadRecipes() {
        checkViewAttached();
        getView().showLoading();
        mRecipeRepo.getRecipes()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Subscriber<List<Recipe>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().onError(e.getMessage());
                        Timber.e(e);
                    }

                    @Override
                    public void onNext(List<Recipe> recipes) {
                        Timber.d(recipes.toString());
                        getView().hideLoading();
                        getView().showRecipes(recipes);
                    }
                });

    }

    @Override
    public void navigateToRecipeSteps(Recipe recipe) {
        getView().showToRecipeDetails(recipe);
    }


}
