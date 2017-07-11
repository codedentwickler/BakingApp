package com.example.codedentwickler.bakingapp.presentation.recipe_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.codedentwickler.bakingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by codedentwickler on 6/22/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityUiTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource() {
        idlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void onRecipeListActivityOpen_displayRecyclerView(){

        // Check that the Recycler View is  displayed
        onView(withId(R.id.recipe_list)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecipeListItem_openRecipeDetailsActivity() {

        onView(withId(R.id.recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.recipe_details_ingredients))
                .check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}