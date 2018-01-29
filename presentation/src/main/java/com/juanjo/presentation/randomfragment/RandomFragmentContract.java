package com.juanjo.presentation.randomfragment;

import com.juanjo.presentation.base.model.Recipe;

import java.util.List;

/**
 * Created by juanj on 26/01/2018.
 */

public interface RandomFragmentContract {
    interface View{
        void showLoading(boolean status);
        void showListOfRecipes(List<Recipe> recipes);
        void showMessage(String message);
    }

    interface Presenter{
        void loadRecipes();
        void loadOneMore();
        void addToFavorite(Recipe recipe);
    }
}
