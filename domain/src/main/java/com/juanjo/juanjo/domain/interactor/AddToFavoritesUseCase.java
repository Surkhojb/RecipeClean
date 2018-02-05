package com.juanjo.juanjo.domain.interactor;

import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by juanj on 24/01/2018.
 */

public class AddToFavoritesUseCase extends UseCase<Completable,AddToFavoritesUseCase.Params> {

    IRecipeRepository recipeRepository;

    @Inject
    public AddToFavoritesUseCase(ThreadExecutor threadExecutor, MainThread mainThread
            , IRecipeRepository repository) {
        super(threadExecutor, mainThread);
        this.recipeRepository = repository;

    }

    @Override
    Observable<Completable> buildUseCaseObservable(Params params) {
        return recipeRepository.addFavorite(params.model).toObservable();
    }

    public static final class Params {
        RecipeModel model;

        private Params(){}

        private Params(RecipeModel model){
            this.model = model;
        }

        public static AddToFavoritesUseCase.Params create(){
            return new AddToFavoritesUseCase.Params();
        }

        public static AddToFavoritesUseCase.Params create(RecipeModel model){
            return new AddToFavoritesUseCase.Params(model);
        }

    }
}

