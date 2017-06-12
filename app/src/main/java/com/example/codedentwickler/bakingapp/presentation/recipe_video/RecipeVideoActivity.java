package com.example.codedentwickler.bakingapp.presentation.recipe_video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.utils.ActivityUtils;

public class RecipeVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video);

        Recipe recipe = getIntent().getParcelableExtra("RECIPE");
        Bundle bundle = new Bundle();
        bundle.putParcelable("RECIPE", recipe);

        RecipeVideoFragment videoFragment
                = (RecipeVideoFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame_list);

        if (videoFragment == null) {

            videoFragment = RecipeVideoFragment.newInstance(bundle);

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    videoFragment, R.id.content_frame_list);

        }
    }
}
