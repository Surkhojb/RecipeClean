package com.juanjo.juanjo.domain.interactor;

import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by juanj on 24/01/2018.
 */

public class GetLatestRecipesUseCase extends UseCase<List<RecipeModel>,GetLatestRecipesUseCase.Params> {

    IRecipeRepository recipeRepository;

    @Inject
    public GetLatestRecipesUseCase(ThreadExecutor threadExecutor, MainThread mainThread
            , IRecipeRepository repository) {
        super(threadExecutor, mainThread);
        this.recipeRepository = repository;

    }

    @Override
    Observable<List<RecipeModel>> buildUseCaseObservable(GetLatestRecipesUseCase.Params params) {
        return recipeRepository.getLatestRecipes();
    }

    public static final class Params {
        private Params(){}

        public static GetLatestRecipesUseCase.Params create(){
            return new GetLatestRecipesUseCase.Params();
        }

    }
}

