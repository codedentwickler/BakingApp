package com.example.codedentwickler.bakingapp.presentation.recipe_details;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.codedentwickler.bakingapp.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by codedentwickler on 6/22/17.
 */
public class RecipeDetailsActivityUITest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeDetailsActivity.class);
    private final int STEP_IN_TABLET = 1;
    private final int STEP_IN_NO_TABLET = 2;

    @Test
    public void clickOnStepsListItem_opensRecipeStepActivity() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_viewpager))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnStepInTabletView_showsVideoPlayerView() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_IN_TABLET, click()));

        onView(
                allOf(
                        withId(R.id.recipe_step_video),
                        withParent(withParent(withId(R.id.step_viewpager))),
                        isDisplayed()
                )
        )
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnStepNotInTabletView_hidesVideoPlayerView() {

        onView(withId(R.id.recipe_details_steps))
                .perform(RecyclerViewActions.actionOnItemAtPosition(STEP_IN_NO_TABLET, click()));

        onView(
                allOf(
                        withId(R.id.recipe_step_video),
                        withParent(withParent(withId(R.id.step_viewpager))),
                        isDisplayed()
                )
        )
                .check(doesNotExist());


    }
}