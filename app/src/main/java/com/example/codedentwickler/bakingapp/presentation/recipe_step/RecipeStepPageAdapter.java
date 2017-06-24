package com.example.codedentwickler.bakingapp.presentation.recipe_step;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.model.Step;

import java.util.List;
import java.util.Locale;

class RecipeStepPageAdapter extends FragmentPagerAdapter {

  private List<Step> steps;
  private final String tabTitle;

  RecipeStepPageAdapter(FragmentManager fm, List<Step> steps, Context context) {
    super(fm);
    setSteps(steps);
    tabTitle = context.getResources().getString(R.string.recipe_step_tab_label);
  }

  void setSteps(@NonNull List<Step> steps) {
    this.steps = steps;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    return SingleStepFragment.newInstance(
        steps.get(position)
    );
  }

  @Override
  public int getCount() {
    return steps.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return String.format(Locale.US, tabTitle, position);
  }
}
