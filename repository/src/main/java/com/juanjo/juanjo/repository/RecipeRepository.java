package com.juanjo.juanjo.repository;

import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;
import com.juanjo.juanjo.repository.cloud.ICloudDataSource;
import com.juanjo.juanjo.repository.local.ILocalDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RecipeRepository implements IRecipeRepository{

    @Inject
    ICloudDataSource cloudDataSource;
    @Inject
    ILocalDataSource localDataSource;

    Observable<List<RecipeModel>> dbResults;
    Observable<List<RecipeModel>> cloudResults;

    @Inject
    public RecipeRepository (){}

    @Override
    public Observable<List<RecipeModel>> getLatestRecipes() {
        dbResults = localDataSource.getLatestRecipes();
        cloudResults = cloudDataSource.getLatestRecipes()
                .doOnNext(models -> localDataSource.saveLatest(models));

        return Observable.merge(dbResults,cloudResults);
    }

    @Override
    public Observable<List<RecipeModel>> getRandomRecipes() {

        dbResults = localDataSource.getRandomRecipes();
        cloudResults = cloudDataSource.getRandomRecipes()
                .doOnNext(models -> localDataSource.saveRandom(models));

        return Observable.merge(dbResults,cloudResults);
    }

    @Override
    public Observable<List<RecipeModel>> getFavorites() {
        return localDataSource.getFavoriteRecipes();
    }

    @Override
    public Completable addFavorite(RecipeModel recipeToAdd) {
        return localDataSource.addToFavorites(recipeToAdd);
    }

    @Override
    public Single<Boolean> removeFromFavorites(RecipeModel recipeToRemove) {
        return localDataSource.removeFavorite(recipeToRemove);
    }
}
