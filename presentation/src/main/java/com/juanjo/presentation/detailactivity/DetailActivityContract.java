package com.juanjo.presentation.detailactivity;

import com.juanjo.presentation.base.model.Recipe;

/**
 * Created by juanj on 30/01/2018.
 */

public interface DetailActivityContract {

    interface View{
        void showMessage(String message);
        void showErrorMessage(String errorMessage);
    }

    interface Presenter{
        void addToFavorites(Recipe recipe);
    }
}
