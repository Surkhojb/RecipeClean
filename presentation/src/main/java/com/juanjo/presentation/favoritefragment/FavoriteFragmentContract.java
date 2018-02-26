package com.juanjo.presentation.favoritefragment;

import com.juanjo.presentation.base.model.Recipe;

import java.util.List;

/**
 * Created by juanj on 29/01/2018.
 */

public interface FavoriteFragmentContract {
    interface View{
        void showLoading(boolean status);
        void showListOfRecipes(List<Recipe> recipes);
        void refreshData(boolean status);
        void showMessage(String message);
        void showErrorMessage(String errorMessage);
        void showEmptyView();

    }

    interface Presenter{
        void loadRecipes();
        void removeFromFavorite(Recipe recipe);
        void onDestroy();
    }
}
