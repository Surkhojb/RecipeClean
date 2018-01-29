package com.juanjo.juanjo.datasource.cloud.model.mapper;

import com.juanjo.juanjo.datasource.base.Mapper;
import com.juanjo.juanjo.datasource.cloud.model.RecipeDto;
import com.juanjo.juanjo.domain.model.RecipeModel;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 24/01/2018.
 */

public class CloudRecipeMapper implements Mapper<RecipeDto,RecipeModel> {

    private final String methodIngredients = "getStrIngredient";
    private final String methodMeasures = "getStrMeasure";

    @Inject
    public CloudRecipeMapper(){}

    @Override
    public RecipeDto map(RecipeModel model) {
        return null;
    }

    @Override
    public RecipeModel inverseMap(RecipeDto dto) {
        RecipeModel recipe = new RecipeModel();
        recipe.setIdRecipe(dto.getIdMeal());
        recipe.setName(dto.getStrMeal());
        recipe.setCategory(dto.getStrCategory());
        recipe.setCountry(dto.getStrArea());
        recipe.setInstruction(dto.getStrInstructions());
        recipe.setImgThumb(dto.getStrMealThumb());
        recipe.setYoutubeLink(dto.getStrYoutube());
        recipe.setIngredients(createListFromMethods(dto,methodIngredients));
        recipe.setMeasures(createListFromMethods(dto,methodMeasures));
        recipe.setYoutubeLink(dto.getStrYoutube());

        return recipe;
    }

    private List<String> createListFromMethods(RecipeDto dto,String method) {
        List<String> listFromMethod = new ArrayList<>();
        for(Method m: dto.getClass().getMethods()) {
            if (m.getName().startsWith(method)) {
                try {
                    final String ing = (String) m.invoke(dto);
                    if(!ing.isEmpty())
                        listFromMethod.add(ing);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return listFromMethod;
    }
}
