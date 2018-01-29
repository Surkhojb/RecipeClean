package com.juanjo.presentation.base.model.mapper;

import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.presentation.base.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 24/01/2018.
 */

public class RecipeTransformer {

    @Inject
    RecipeMapper recipeMapper;

    @Inject
    public RecipeTransformer(){}

    public List<Recipe> transform(List<RecipeModel> models){
        List<Recipe> recipes = new ArrayList<>();
        for(RecipeModel m : models)
            recipes.add(recipeMapper.map(m));

        return recipes;
    }

    public RecipeModel transformToModel(Recipe recipe){
        return recipeMapper.inverseMap(recipe);
    }
}
