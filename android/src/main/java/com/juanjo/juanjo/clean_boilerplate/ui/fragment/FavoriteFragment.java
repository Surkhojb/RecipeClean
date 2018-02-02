package com.juanjo.juanjo.clean_boilerplate.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.clean_boilerplate.di.component.DaggerFavoriteFragmentComponent;
import com.juanjo.juanjo.clean_boilerplate.di.module.FavoriteFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.di.module.LatestFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.ui.activity.DetailActivity;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeAdapter;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeClickListener;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.RecipeFavoritesAdapter;
import com.juanjo.juanjo.domain.model.event.OnRefreshFavorite;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.favoritefragment.FavoriteFragmentContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FavoriteFragment extends Fragment implements FavoriteFragmentContract.View,RecipeClickListener {


    @BindView(R.id.rv_recipes)
    RecyclerView listOfRecipes;
    @BindView(R.id.progress_loading_list)
    ProgressBar loadingIndicator;
    @BindView(R.id.view_content)
    View viewContent;
    @BindView(R.id.view_empty)
    View viewEmpty;

    AppBarLayout appBarLayout;

    RecipeFavoritesAdapter recipeAdapter;

    @Inject
    FavoriteFragmentContract.Presenter presenter;

    @Inject
    EventBus eventBus;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_favorite, container, false);
        appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appbar);
        ButterKnife.bind(this,v);
        injectDependencies();
        initRecyclerView();
        presenter.loadRecipes();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        eventBus.unregister(this);
        super.onStop();
    }

    @Override
    public void showLoading(boolean status) {
        if (status) {
            loadingIndicator.setVisibility(VISIBLE);
            listOfRecipes.setVisibility(GONE);
        } else {
            loadingIndicator.setVisibility(GONE);
            listOfRecipes.setVisibility(VISIBLE);
        }
    }

    @Override
    public void showListOfRecipes(List<Recipe> recipes) {
        if (!recipes.isEmpty())
            recipeAdapter.refreshData(recipes);
    }

    @Override
    public void refreshData(boolean status) {
        if(status)
            presenter.loadRecipes();
    }

    @Override
    public void showMessage(String message) {
        Toasty.warning(getContext(),message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toasty.error(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyView() {
        appBarLayout.setExpanded(true,true);
        viewContent.setVisibility(GONE);
        viewEmpty.setVisibility(VISIBLE);
    }

    @Override
    public void onCardViewClicked(View v, int position) {
        startActivity(new Intent(getContext(),DetailActivity.class).putExtra(DetailActivity.EXTRA_RECIPE,recipeAdapter.getRecipe(position)));
    }

    @Override
    public void onFavClicked(View v, int position) {
        presenter.removeFromFavorite(recipeAdapter.getRecipe(position));
    }

    void initRecyclerView() {
        listOfRecipes.setHasFixedSize(true);
        listOfRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeAdapter = new RecipeFavoritesAdapter();
        recipeAdapter.setRecipeClickListener(this);
        listOfRecipes.setAdapter(recipeAdapter);
    }

    private void injectDependencies() {
        RecipeApp app = (RecipeApp) getActivity().getApplication();

        DaggerFavoriteFragmentComponent.builder()
                .recipeAppComponent(app.getAppComponent())
                .favoriteFragmentModule(new FavoriteFragmentModule(this))
                .build()
                .inject(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshFavorites(OnRefreshFavorite refresh){
        presenter.loadRecipes();
    }
}
