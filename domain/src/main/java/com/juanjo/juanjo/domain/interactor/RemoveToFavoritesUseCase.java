package com.juanjo.juanjo.domain.interactor;

import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by juanj on 24/01/2018.
 */

public class RemoveToFavoritesUseCase extends UseCase<Boolean,RemoveToFavoritesUseCase.Params> {

    IRecipeRepository recipeRepository;

    @Inject
    public RemoveToFavoritesUseCase(ThreadExecutor threadExecutor, MainThread mainThread
            , IRecipeRepository repository) {
        super(threadExecutor, mainThread);
        this.recipeRepository = repository;

    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return recipeRepository.removeFromFavorites(params.model).toObservable();
    }

    public static final class Params {
        RecipeModel model;

        private Params(){}

        private Params(RecipeModel model){
            this.model = model;
        }

        public static RemoveToFavoritesUseCase.Params create(){
            return new RemoveToFavoritesUseCase.Params();
        }

        public static RemoveToFavoritesUseCase.Params create(RecipeModel model){
            return new RemoveToFavoritesUseCase.Params(model);
        }

    }
}

