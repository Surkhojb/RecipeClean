package com.juanjo.presentation.randomfragment;

import com.juanjo.juanjo.domain.interactor.DefaultObserver;
import com.juanjo.juanjo.domain.interactor.GetRandomRecipesUseCase;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.base.model.mapper.RecipeTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class RandomFragmentPresenter implements RandomFragmentContract.Presenter {

    @Inject
    RandomFragmentContract.View view;
    @Inject
    RecipeTransformer transformer;
    @Inject
    GetRandomRecipesUseCase getRandom;


    @Inject
    public RandomFragmentPresenter(){}

    @Override
    public void loadRecipes() {
        view.showLoading(true);
        getRandom.execute(new GetRandomObserver(),GetRandomRecipesUseCase.Params.create());
    }

    @Override
    public void loadOneMore() {
        view.showLoading(true);
        getRandom.execute(new GetRandomObserver(),GetRandomRecipesUseCase.Params.create());
    }

    @Override
    public void addToFavorite(Recipe recipe) {

    }

    final class GetRandomObserver extends DefaultObserver<List<RecipeModel>> {
        @Override
        public void onNext(List<RecipeModel> models) {
            view.showListOfRecipes(transformer.transform(models));
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
