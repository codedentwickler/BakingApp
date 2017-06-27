package com.example.codedentwickler.bakingapp.presentation.recipe_details;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codedentwickler.bakingapp.R;
import com.example.codedentwickler.bakingapp.data.remote.model.Ingredient;
import com.example.codedentwickler.bakingapp.data.remote.model.Recipe;
import com.example.codedentwickler.bakingapp.data.remote.model.Step;
import com.example.codedentwickler.bakingapp.presentation.base.BaseFragment;
import com.example.codedentwickler.bakingapp.presentation.recipe_step.RecipeStepActivity;
import com.example.codedentwickler.bakingapp.presentation.recipe_step.SingleStepFragment;
import com.example.codedentwickler.bakingapp.utils.ActivityUtils;
import com.example.codedentwickler.bakingapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindBool;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends BaseFragment
        implements RecipeDetailsContract.View {

    @BindView(R.id.recipe_details_ingredients)
    TextView mIngredientTextView;
    @BindView(R.id.recipe_details_steps)
    RecyclerView mRecipeListView;
    @BindString(R.string.ingredients_header)
    String ingredientsHeader;

    @BindBool(R.bool.isTablet)
    boolean isTwoPane;

    private RecipeDetailsContract.Presenter mPresenter;

    private RecipeDetailsAdapter mStepsAdapter;
    private Recipe mCurrentRecipe;

    private static final String CURRENT_RECIPE = "CURRENT_RECIPE";


    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailsFragment newInstance(Bundle bundle) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mCurrentRecipe = getArguments().getParcelable(RecipeDetailsActivity.RECIPE_KEY);
        } else {
            mCurrentRecipe = savedInstanceState.getParcelable(CURRENT_RECIPE);
            mPresenter = new RecipeDetailsPresenter(mCurrentRecipe);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_RECIPE, mCurrentRecipe);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_details,
                container, false);

        setUnBinder(ButterKnife.bind(this,view));

        mStepsAdapter = new RecipeDetailsAdapter(new ArrayList<>(0),
                step -> mPresenter.navigateToStepDetails(step.getId()));
        setUpRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.attachView(this);

        if (isTwoPane) {
            mPresenter.loadStepData(0);
        }
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecipeListView.setLayoutManager(layoutManager);
        mRecipeListView.setHasFixedSize(true);
        mRecipeListView.setAdapter(mStepsAdapter);

        mRecipeListView.addItemDecoration(new DividerItemDecoration(getContext()
                , DividerItemDecoration.VERTICAL));
    }

    private void setViewVisibility(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSteps(List<Step> steps) {
        mStepsAdapter.replaceData(steps);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {

        StringBuilder sb = new StringBuilder();
        sb.append(ingredientsHeader);

        for (Ingredient ingredient : ingredients) {

            String name = ingredient.getIngredient();
            double quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();

            sb.append("\n");
            sb.append(CommonUtils.formatIngredient(getContext(), name, quantity, measure));
        }

        CommonUtils.setTextWithSpan(mIngredientTextView, sb.toString(), ingredientsHeader,
                new StyleSpan(Typeface.BOLD));
    }

    @Override
    public void showRecipeNameInAppBar(String recipeName) {
        this.getActivity().setTitle(recipeName);
    }

    @Override
    public void showStepDetails(int stepId) {

        if (isTwoPane) {
            mPresenter.loadStepData(stepId);
        } else {
            startActivity(
                    RecipeStepActivity
                            .buildIntent(getActivity(), mCurrentRecipe, stepId)
            );
        }
    }

    @Override
    public void showStepsDetailInContainer(int stepId) {
        SingleStepFragment stepFragment =
                SingleStepFragment.newInstance(mCurrentRecipe.getSteps().get(stepId));

        ActivityUtils.addFragmentToActivity(getChildFragmentManager()
                , stepFragment
                , R.id.content_frame_recipe_steps);
    }

    @Override
    public void setPresenter(RecipeDetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
