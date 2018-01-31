package com.juanjo.juanjo.domain.repository;

import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by juanj on 24/01/2018.
 */

public interface IRecipeRepository {
    Observable<List<RecipeModel>> getLatestRecipes();
    Observable<List<RecipeModel>> getRandomRecipes();
    Observable<List<RecipeModel>> getFavorites();
    Completable addFavorite(RecipeModel recipeToAdd);
    Single<Boolean> removeFromFavorites(RecipeModel recipeToRemove);
}
