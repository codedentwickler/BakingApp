package com.example.codedentwickler.bakingapp.data.local;

import com.example.codedentwickler.bakingapp.data.model.Recipe;

import java.util.List;

import rx.Observable;

/**
 * Created by codedentwickler on 6/10/17.
 */

public interface RecipeRepo {

    Observable<List<Recipe>> getRecipes();

}
