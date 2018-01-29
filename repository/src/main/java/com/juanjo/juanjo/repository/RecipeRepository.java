package com.juanjo.juanjo.repository;

import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;
import com.juanjo.juanjo.repository.cloud.ICloudDataSource;
import com.juanjo.juanjo.repository.local.ILocalDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class RecipeRepository implements IRecipeRepository{

    @Inject
    ICloudDataSource cloudDataSource;
    @Inject
    ILocalDataSource localDataSource;

    @Inject
    public RecipeRepository (){}

    @Override
    public Observable<List<RecipeModel>> getLatestRecipes() {
        return cloudDataSource.getLatestRecipes();
    }

    @Override
    public Observable<List<RecipeModel>> getRandomRecipes() {
        return cloudDataSource.getRandomRecipes();
    }

    @Override
    public Observable<List<RecipeModel>> getFavorites() {
        return localDataSource.getFavoriteRecipes();
    }

    @Override
    public Completable addFavorite(RecipeModel recipeToAdd) {
        return localDataSource.addToFavorites(recipeToAdd);
    }
}
