package com.juanjo.presentation.base.model.mapper;

import com.juanjo.juanjo.domain.model.RecipeModel;
import com.juanjo.presentation.base.Mapper;
import com.juanjo.presentation.base.model.Recipe;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class RecipeMapper implements Mapper<Recipe,RecipeModel> {
    private  final static String NULL_FIELD = "null";
    private final static String EMPTY_FIELD = "";
    private final static String NOT_FIELD = "-";

    @Inject
    public RecipeMapper(){}

    @Override
    public Recipe map(RecipeModel model) {
        Recipe recipe = new Recipe();
        recipe.setIdRecipe(model.getIdRecipe());
        recipe.setName(model.getName());
        recipe.setCategory(validateNullOrEmpty(model.getCategory()));
        recipe.setCountry(validateNullOrEmpty(model.getCountry()));
        recipe.setInstruction(validateNullOrEmpty(model.getInstruction()));
        recipe.setImgThumb(validateNullOrEmpty(model.getImgThumb()));
        recipe.setYoutubeLink(validateNullOrEmpty(model.getYoutubeLink()));
        recipe.setIngredients(model.getIngredients());
        recipe.setMeasures(model.getMeasures());
        recipe.setWebLink(validateNullOrEmpty(model.getWebLink()));

        return recipe;
    }

    @Override
    public RecipeModel inverseMap(Recipe recipe) {
        RecipeModel model = new RecipeModel();
        model.setIdRecipe(recipe.getIdRecipe());
        model.setName(recipe.getName());
        model.setCategory(recipe.getCategory());
        model.setCountry(recipe.getCountry());
        model.setInstruction(recipe.getInstruction());
        model.setImgThumb(recipe.getImgThumb());
        model.setYoutubeLink(recipe.getYoutubeLink());
        model.setIngredients(recipe.getIngredients());
        model.setMeasures(recipe.getMeasures());
        model.setWebLink(recipe.getWebLink());

        return model;
    }

    private String validateNullOrEmpty(String s){
        if(s == null || s.equals(NULL_FIELD)|| s.equals(EMPTY_FIELD))
            return NOT_FIELD;
        else
            return s;
    }
}
