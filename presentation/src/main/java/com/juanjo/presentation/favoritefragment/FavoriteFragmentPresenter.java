package com.juanjo.presentation.favoritefragment;

import com.juanjo.juanjo.domain.interactor.DefaultObserver;
import com.juanjo.juanjo.domain.interactor.GetFavoriteRecipesUseCase;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.base.model.mapper.RecipeTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 29/01/2018.
 */

public class FavoriteFragmentPresenter implements FavoriteFragmentContract.Presenter {

    @Inject
    FavoriteFragmentContract.View view;

    @Inject
    GetFavoriteRecipesUseCase getFavorites;

    @Inject
    RecipeTransformer transformer;

    @Inject
    public FavoriteFragmentPresenter(){}



    @Override
    public void loadRecipes() {
        getFavorites.execute(new GetFavoriteObserver(),GetFavoriteRecipesUseCase.Params.create());
    }

    @Override
    public void removeFromFavorite(Recipe recipe) {
        //TODO: Add remove funcionality.
        view.showMessage("Removed");
    }

    final class GetFavoriteObserver extends DefaultObserver<List<RecipeModel>>{
        @Override
        public void onNext(List<RecipeModel> models) {
            if(!models.isEmpty())
                view.showListOfRecipes(transformer.transform(models));
            else
                view.showEmptyView();

        }

        @Override
        public void onComplete() {
            view.showLoading(false);
        }

        @Override
        public void onError(Throwable exception) {
            view.showMessage(exception.getMessage());
        }
    }
}
