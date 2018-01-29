package com.juanjo.juanjo.datasource.cloud.model.mapper;

import com.juanjo.juanjo.datasource.cloud.model.RecipeDto;
import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 24/01/2018.
 */

public class CloudRecipeTransformer {

    @Inject
    CloudRecipeMapper cloudRecipeMapper;

    @Inject
    public CloudRecipeTransformer(){}

    public List<RecipeModel> transformRecipes(List<RecipeDto> dtos){
        List<RecipeModel> recipes = new ArrayList<>();
        for(RecipeDto dto : dtos)
            recipes.add(cloudRecipeMapper.inverseMap(dto));

        return recipes;
    }
}
