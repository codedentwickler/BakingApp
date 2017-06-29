package com.example.codedentwickler.bakingapp.data.remote.model;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by codedentwickler on 6/29/17.
 */

public interface ApiService {
    @GET("android-baking-app-json")
    Observable<List<Recipe>> fetchRecipes();
}
