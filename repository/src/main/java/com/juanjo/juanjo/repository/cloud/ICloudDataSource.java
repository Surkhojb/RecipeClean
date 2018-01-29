package com.juanjo.juanjo.repository.cloud;


import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by juanj on 24/01/2018.
 */

public interface ICloudDataSource {
    Observable<List<RecipeModel>> getLatestRecipes();
    Observable<List<RecipeModel>> getRandomRecipes();
    Observable<List<RecipeModel>> getDetailRecipe(String id);

}
