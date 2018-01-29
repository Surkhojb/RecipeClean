package com.juanjo.juanjo.datasource.cloud;

import com.juanjo.juanjo.datasource.cloud.model.mapper.CloudRecipeTransformer;
import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.juanjo.repository.cloud.ICloudDataSource;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.Observable;

/**
 * Created by juanj on 24/01/2018.
 */

public class CloudDataSource implements ICloudDataSource {

    @Inject
    Lazy<ApiService> apiServiceLazy;
    @Inject
    CloudRecipeTransformer cloudTransformer;

    @Inject
    public CloudDataSource(){}

    @Override
    public Observable<List<RecipeModel>> getLatestRecipes() {
        return apiServiceLazy.get().getLatestRecipes()
                .map(listRecipesDto -> cloudTransformer.transformRecipes(listRecipesDto.getMeals()));
    }

    @Override
    public Observable<List<RecipeModel>> getRandomRecipes() {
        return apiServiceLazy.get().getRandomRecipes()
                .map(listRecipesDto -> cloudTransformer.transformRecipes(listRecipesDto.getMeals()));
    }

    @Override
    public Observable<List<RecipeModel>> getDetailRecipe(String id) {
        return apiServiceLazy.get().getRecipeDetails(id).map(cloudTransformer::transformRecipes);
    }
}
