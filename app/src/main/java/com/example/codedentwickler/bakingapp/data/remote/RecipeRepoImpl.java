package com.example.codedentwickler.bakingapp.data.remote;

import com.example.codedentwickler.bakingapp.data.remote.model.ApiService;
import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;

import java.util.List;

import rx.Observable;
import rx.functions.Func0;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class RecipeRepoImpl implements RecipeRepo {

    private final ApiService apiService;

    public RecipeRepoImpl(ApiService apiService) {
        this.apiService = checkNotNull(apiService, "Baking App Api Service cannot be null");
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {

        return Observable.defer(new Func0<Observable<List<Recipe>>>() {
            @Override
            public Observable<List<Recipe>> call() {
                return apiService.fetchRecipes();
            }
        });
    }
}
