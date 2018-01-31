package com.juanjo.juanjo.datasource.local;

import com.juanjo.juanjo.datasource.local.latest.mapper.LocalRecipeTransformer;
import com.juanjo.juanjo.datasource.local.favorite.FavoriteDao;
import com.juanjo.juanjo.datasource.local.favorite.model.mapper.LocalFavoriteTransformer;
import com.juanjo.juanjo.datasource.local.latest.LatestDao;
import com.juanjo.juanjo.datasource.local.random.RandomDao;
import com.juanjo.juanjo.datasource.local.random.mapper.LocalRandomTransformer;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.repository.local.ILocalDataSource;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by juanj on 29/01/2018.
 */

public class LocalDataSource implements ILocalDataSource {

    @Inject
    FavoriteDao favoriteDao;
    @Inject
    LatestDao latestDao;
    @Inject
    RandomDao randomDao;

    @Inject
    LocalFavoriteTransformer favoriteTransformer;
    @Inject
    LocalRecipeTransformer localRecipeTransformer;
    @Inject
    LocalRandomTransformer localRandomTransformer;

    @Inject
    public LocalDataSource(){}

    @Override
    public Observable<List<RecipeModel>> getLatestRecipes() {
        return Observable.fromCallable(() -> localRecipeTransformer.transformList(latestDao.getLatests()));

    }

    @Override
    public Observable<List<RecipeModel>> getRandomRecipes() {
        return Observable.fromCallable(() -> localRandomTransformer.transformList(randomDao.getRandoms()));
    }

    @Override
    public Observable<List<RecipeModel>> getFavoriteRecipes() {
        return Observable.fromCallable(() -> favoriteTransformer.transformList(favoriteDao.getFavorites()));
    }

    @Override
    public Completable addToFavorites(RecipeModel model) {
        return Completable.fromCallable(() -> favoriteDao.insert(favoriteTransformer.mapToFavorite(model)));
    }

    @Override
    public Single<Boolean> removeFavorite(RecipeModel model) {
        return Single.fromCallable(() -> favoriteDao.remove(favoriteTransformer.mapToFavorite(model)) > 0);
    }
}
