package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.local.RecipeRepo;
import com.example.codedentwickler.bakingapp.data.model.Recipe;
import com.example.codedentwickler.bakingapp.injection.Injection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by codedentwickler on 6/22/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityIntentTest {

    private static final String RECIPE_KEY = "RECIPE_KEY";
    private RecipeRepo mRecipeRepo;
    private final int POSITION = 0;

    @Before
    public void setUp() {
        mRecipeRepo = Injection.provideRecipeRepo(InstrumentationRegistry.getTargetContext());
    }

    @Rule
    public IntentsTestRule<RecipeListActivity> mIntentsTestRule =
            new IntentsTestRule<>(RecipeListActivity.class);

    @Test
    public void clickRecipeListItem_doRecipeDetailsActivityIntent() {

        onView(withId(R.id.recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(POSITION, click()));

        final Recipe[] recipe = new Recipe[1];
        mRecipeRepo.getRecipes()
                .subscribe(recipes -> recipe[0] = recipes.get(POSITION));

        intended(
                hasExtra(RECIPE_KEY, recipe[0])
        );
    }
}