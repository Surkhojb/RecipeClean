package com.juanjo.juanjo.datasource.cloud;

import com.juanjo.juanjo.datasource.cloud.model.ListRecipesDto;
import com.juanjo.juanjo.datasource.cloud.model.RecipeDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by juanj on 24/01/2018.
 */

public interface ApiService {
    @GET("/api/json/v1/1/latest.php")
    Observable<ListRecipesDto> getLatestRecipes();
    @GET("/api/json/v1/1/random.php")
    Observable<ListRecipesDto> getRandomRecipes();
    @GET("/api/json/v1/1/lookup.php")
    Observable<List<RecipeDto>> getRecipeDetails(@Query("i") String id);
}
