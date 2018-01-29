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

public class GetRandomRecipesUseCase extends UseCase<List<RecipeModel>,GetRandomRecipesUseCase.Params> {

    IRecipeRepository recipeRepository;

    @Inject
    public GetRandomRecipesUseCase(ThreadExecutor threadExecutor, MainThread mainThread
            , IRecipeRepository repository) {
        super(threadExecutor, mainThread);
        this.recipeRepository = repository;

    }

    @Override
    Observable<List<RecipeModel>> buildUseCaseObservable(GetRandomRecipesUseCase.Params params) {
        return recipeRepository.getRandomRecipes();
    }

    public static final class Params {
        private Params(){}

        public static GetRandomRecipesUseCase.Params create(){
            return new GetRandomRecipesUseCase.Params();
        }

    }
}

