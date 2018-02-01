package com.juanjo.presentation.latestfragment;

import com.juanjo.juanjo.domain.interactor.AddToFavoritesUseCase;
import com.juanjo.juanjo.domain.interactor.DefaultObserver;
import com.juanjo.juanjo.domain.interactor.GetLatestRecipesUseCase;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.base.model.mapper.RecipeTransformer;
import io.reactivex.Completable;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by juanj on 24/01/2018.
 */

public class LatestFragmentPresenter implements LatestFragmentContract.Presenter {

  @Inject LatestFragmentContract.View view;
  @Inject RecipeTransformer transformer;
  @Inject GetLatestRecipesUseCase getLatest;
  @Inject AddToFavoritesUseCase addFavorites;

  @Inject public LatestFragmentPresenter() {
  }

  @Override public void loadRecipes() {
    view.showLoading(true);
    getLatest.execute(new GetLatestObserver(), GetLatestRecipesUseCase.Params.create());
  }

  @Override public void addToFavorite(Recipe recipe) {
    view.showLoading(true);
    addFavorites.execute(new AddFavoritesObserver(),
        AddToFavoritesUseCase.Params.create(transformer.transformToModel(recipe)));
  }

  final class GetLatestObserver extends DefaultObserver<List<RecipeModel>> {
    @Override public void onNext(List<RecipeModel> models) {
      view.showListOfRecipes(transformer.transform(models));
    }

    @Override public void onComplete() {
      view.showLoading(false);
    }

    @Override public void onError(Throwable exception) {
      view.showMessage(exception.getMessage());
    }
  }

  final class AddFavoritesObserver extends DefaultObserver<Completable> {
    @Override public void onNext(Completable completable) {
      view.showMessage("Recipe added.");
    }

    @Override public void onComplete() {
      view.eventRefresh();
    }

    @Override public void onError(Throwable exception) {
      view.showMessage(exception.getMessage());
    }

    @Override public void onTerminate() {
      view.showLoading(false);
    }
  }
}
