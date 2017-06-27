package com.example.codedentwickler.bakingapp.injection;

import com.example.codedentwickler.bakingapp.RxUtils.schedulers.BaseSchedulerProvider;
import com.example.codedentwickler.bakingapp.RxUtils.schedulers.SchedulerProvider;
import com.example.codedentwickler.bakingapp.data.remote.ApiService;
import com.example.codedentwickler.bakingapp.data.remote.RecipeRepo;
import com.example.codedentwickler.bakingapp.data.remote.RecipeRepoImpl;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class Injection {

    private static RecipeRepo recipeRepoInstance;
    private static ApiService apiService;

    public static RecipeRepo provideRecipeRepo(){
        if (recipeRepoInstance == null)
            recipeRepoInstance = new RecipeRepoImpl(provideApiService());
        return recipeRepoInstance;
    }

    private static ApiService provideApiService() {
        if (apiService == null) {
            apiService = ApiService.Factory.create();
        }
        return apiService;
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
