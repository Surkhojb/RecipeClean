package com.juanjo.juanjo.clean_boilerplate.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.clean_boilerplate.di.component.DaggerLatestFragmentComponent;
import com.juanjo.juanjo.clean_boilerplate.di.component.DaggerRandomFragmentComponent;
import com.juanjo.juanjo.clean_boilerplate.di.module.LatestFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.di.module.RandomFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.ui.activity.DetailActivity;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeAdapter;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeClickListener;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeRandomAdapter;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.randomfragment.RandomFragmentContract;
import com.juanjo.presentation.randomfragment.RandomFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RandomFragment extends Fragment implements RandomFragmentContract.View,RecipeClickListener,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_recipes)
    RecyclerView listOfRecipes;
    @BindView(R.id.progress_loading_list)
    ProgressBar loadingIndicator;
    @BindView(R.id.swipe_more_random)
    SwipeRefreshLayout swipeMoreRandoms;

    @Inject
    RandomFragmentContract.Presenter presenter;

    RecipeRandomAdapter recipeAdapter;


    public RandomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_random, container, false);
        ButterKnife.bind(this,v);
        injectDependencies();
        initRecyclerView();
        presenter.loadRecipes();
        return v;
    }

    @Override
    public void showLoading(boolean status) {
        if (status) {
            loadingIndicator.setVisibility(View.VISIBLE);
            listOfRecipes.setVisibility(View.GONE);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            listOfRecipes.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showListOfRecipes(List<Recipe> recipes) {
        if(swipeMoreRandoms.isRefreshing())
            swipeMoreRandoms.setRefreshing(false);

        if (!recipes.isEmpty())
            recipeAdapter.refreshData(recipes);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCardViewClicked(View v, int position) {
        startActivity(new Intent(getContext(),DetailActivity.class)
                .putExtra(DetailActivity.EXTRA_RECIPE,recipeAdapter.getRecipe(position)));

    }

    @Override
    public void onFavClicked(View v, int position) {

    }

    private void initRecyclerView() {
        listOfRecipes.setHasFixedSize(true);
        listOfRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeAdapter = new RecipeRandomAdapter();
        recipeAdapter.setRecipeClickListener(this);
        listOfRecipes.setAdapter(recipeAdapter);
        swipeMoreRandoms.setOnRefreshListener(this);
    }

    private void injectDependencies() {
        RecipeApp app = (RecipeApp) getActivity().getApplication();

        DaggerRandomFragmentComponent.builder()
                .recipeAppComponent(app.getAppComponent())
                .randomFragmentModule(new RandomFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {
        presenter.loadOneMore();
    }
}
