package com.juanjo.presentation.detailactivity;

import com.juanjo.juanjo.domain.interactor.AddToFavoritesUseCase;
import com.juanjo.juanjo.domain.interactor.DefaultObserver;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.base.model.mapper.RecipeMapper;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by juanj on 30/01/2018.
 */

public class DetailActivityPresenter implements DetailActivityContract.Presenter {

    @Inject
    DetailActivityContract.View view;

    @Inject
    AddToFavoritesUseCase addToFavoritesUseCase;
    @Inject
    RecipeMapper recipeMapper;

    @Inject
    public DetailActivityPresenter(){}

    @Override
    public void addToFavorites(Recipe recipe) {
        addToFavoritesUseCase.execute(new AddFavoritesObserver(),
                AddToFavoritesUseCase.Params.create(recipeMapper.inverseMap(recipe)));
    }

    final class AddFavoritesObserver extends DefaultObserver<Completable> {
        @Override
        public void onNext(Completable completable) {

        }

        @Override
        public void onComplete() {
            view.showMessage("Recipe added.");
        }

        @Override
        public void onError(Throwable exception) {
            view.showMessage(exception.getMessage());
        }
    }
}
