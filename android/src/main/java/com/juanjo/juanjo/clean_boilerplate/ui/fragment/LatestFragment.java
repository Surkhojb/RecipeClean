package com.juanjo.juanjo.clean_boilerplate.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.juanjo.juanjo.clean_boilerplate.di.module.LatestFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.ui.activity.DetailActivity;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeClickListener;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeAdapter;
import com.juanjo.juanjo.domain.model.event.OnRefreshFavorite;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.latestfragment.LatestFragmentContract;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class LatestFragment extends Fragment implements LatestFragmentContract.View,RecipeClickListener {


    @BindView(R.id.rv_recipes)
    RecyclerView listOfRecipes;
    @BindView(R.id.progress_loading_list)
    ProgressBar loadingIndicator;

    @Inject
    LatestFragmentContract.Presenter presenter;

    @Inject
    EventBus eventBus;

    RecipeAdapter recipeAdapter;

    public LatestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_latest, container, false);
        ButterKnife.bind(this, v);
        initRecyclerView();
        injectDependencies();
        presenter.loadRecipes();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void showListOfRecipes(List<Recipe> recipes) {
        if (!recipes.isEmpty())
            recipeAdapter.refreshData(recipes);
    }

    @Override
    public void showMessage(String message) {
        Toasty.success(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toasty.error(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
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
    public void onCardViewClicked(View v, int position) {
        startActivity(new Intent(getContext(),DetailActivity.class).putExtra("RECIPE",recipeAdapter.getRecipe(position)));
    }

    @Override
    public void onFavClicked(View v, int position) {
        presenter.addToFavorite(recipeAdapter.getRecipe(position));
    }

    @Override
    public void eventRefresh(){
        eventBus.post(new OnRefreshFavorite());
    }

    void initRecyclerView() {
        listOfRecipes.setHasFixedSize(true);
        listOfRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeAdapter = new RecipeAdapter();
        recipeAdapter.setRecipeClickListener(this);
        listOfRecipes.setAdapter(recipeAdapter);
    }

    private void injectDependencies() {
        RecipeApp app = (RecipeApp) getActivity().getApplication();

        DaggerLatestFragmentComponent.builder()
                .recipeAppComponent(app.getAppComponent())
                .latestFragmentModule(new LatestFragmentModule(this))
                .build()
                .inject(this);
    }

}
