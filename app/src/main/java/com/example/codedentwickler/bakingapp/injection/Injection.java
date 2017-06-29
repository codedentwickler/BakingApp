package com.example.codedentwickler.bakingapp.injection;

import com.example.codedentwickler.bakingapp.RxUtils.schedulers.BaseSchedulerProvider;
import com.example.codedentwickler.bakingapp.RxUtils.schedulers.SchedulerProvider;
import com.example.codedentwickler.bakingapp.data.remote.RecipeRepo;
import com.example.codedentwickler.bakingapp.data.remote.RecipeRepoImpl;
import com.example.codedentwickler.bakingapp.data.remote.model.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class Injection {

    private static RecipeRepo recipeRepoInstance;
    private static ApiService apiService;

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;


    public static RecipeRepo provideRecipeRepo(){
        if (recipeRepoInstance == null)
            recipeRepoInstance = new RecipeRepoImpl(provideApiService());
        return recipeRepoInstance;
    }

    private static ApiService provideApiService() {
        if (apiService == null) {
            apiService = getRetrofitInstance().create(ApiService.class);
        }

        return apiService;
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        return okHttpClient;
    }

    private static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            String BASE_URL = "http://go.udacity.com/";
            Retrofit.Builder retrofit = new Retrofit.Builder().client(getOkHttpClient()).
                    baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();
        }
        return retrofitInstance;
    }

}
