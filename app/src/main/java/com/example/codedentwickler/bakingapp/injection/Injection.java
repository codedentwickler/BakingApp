package com.example.codedentwickler.bakingapp.injection;

import android.content.Context;

import com.example.codedentwickler.bakingapp.RxUtils.schedulers.BaseSchedulerProvider;
import com.example.codedentwickler.bakingapp.RxUtils.schedulers.SchedulerProvider;
import com.example.codedentwickler.bakingapp.data.local.RecipeRepo;
import com.example.codedentwickler.bakingapp.data.local.RecipeRepoImpl;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class Injection {

    private static RecipeRepo recipeRepoInstance;

    public static RecipeRepo provideRecipeRepo(Context context){
        if (recipeRepoInstance == null)
            recipeRepoInstance = new RecipeRepoImpl(context);
        return recipeRepoInstance;
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
