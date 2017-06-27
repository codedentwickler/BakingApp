package com.example.codedentwickler.bakingapp.data.remote;

import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;

import java.io.IOException;
import java.util.List;

import rx.Observable;

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

        return Observable.defer(apiService::fetchRecipes)

                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }
}
