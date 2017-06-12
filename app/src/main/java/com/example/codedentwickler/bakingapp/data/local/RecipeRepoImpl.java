package com.example.codedentwickler.bakingapp.data.local;

import android.content.Context;

import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.data.model.RecipeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 6/10/17.
 */

public class RecipeRepoImpl implements RecipeRepo {

    private final Context context;

    public RecipeRepoImpl(Context context) {
        this.context = checkNotNull( context);
    }


    @Override
    public Observable<List<Recipe>> getRecipes() {
        String[] json = {null};
        try {
            InputStream inputStream = context.getAssets().open("recipes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json[0] = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Observable.defer(() ->
                Observable.just(RecipeList.parseJson(json[0]).getRecipes()));
    }
}
