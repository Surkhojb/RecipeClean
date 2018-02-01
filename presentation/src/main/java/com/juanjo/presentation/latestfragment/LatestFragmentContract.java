package com.juanjo.presentation.latestfragment;

import com.juanjo.presentation.base.model.Recipe;

import java.util.List;

/**
 * Created by juanj on 24/01/2018.
 */

public interface LatestFragmentContract {
    interface View{
        void showLoading(boolean status);
        void showListOfRecipes(List<Recipe> recipes);
        void showMessage(String message);
        void eventRefresh();
    }

    interface Presenter{
        void loadRecipes();
        void addToFavorite(Recipe recipe);
    }
}
