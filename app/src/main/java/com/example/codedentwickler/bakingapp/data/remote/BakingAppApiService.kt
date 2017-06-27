package com.example.codedentwickler.bakingapp.data.remote

import com.example.codedentwickler.bakingapp.data.remote.model.Recipe
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by codedentwickler on 6/27/17.
 */

interface ApiService {

    @GET("android-baking-app-json")
    fun fetchRecipes() : Observable<List<Recipe>>

    companion object Factory {
        val BASE_URL = "http://go.udacity.com/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}