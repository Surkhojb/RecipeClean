package com.juanjo.juanjo.datasource.cloud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juanj on 24/01/2018.
 */

public class ListRecipesDto {
    @Expose
    @SerializedName("meals")
    List<RecipeDto> meals;

    public List<RecipeDto> getMeals() {
        return meals;
    }

    public void setMeals(List<RecipeDto> meals) {
        this.meals = meals;
    }
}
