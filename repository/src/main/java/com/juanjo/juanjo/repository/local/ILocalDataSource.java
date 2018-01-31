package com.juanjo.juanjo.repository.local;

import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by juanj on 26/01/2018.
 */

public interface ILocalDataSource {
    Observable<List<RecipeModel>> getLatestRecipes();
    Observable<List<RecipeModel>> getRandomRecipes();
    Observable<List<RecipeModel>> getFavoriteRecipes();
    Completable addToFavorites(RecipeModel model);
    Single<Boolean> removeFavorite(RecipeModel model);
}
