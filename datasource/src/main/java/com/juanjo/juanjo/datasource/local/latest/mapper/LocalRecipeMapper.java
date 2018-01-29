package com.juanjo.juanjo.datasource.local.latest.mapper;

import com.juanjo.juanjo.datasource.base.Mapper;
import com.juanjo.juanjo.datasource.local.latest.RecipeLatestVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalRecipeMapper implements Mapper<RecipeModel,RecipeLatestVo> {

    @Inject
    public LocalRecipeMapper(){}

    @Override
    public RecipeModel map(RecipeLatestVo vo) {
        RecipeModel model = new RecipeModel();
        model.setIdRecipe(vo.getIdRecipe());
        model.setName(vo.getName());
        model.setCategory(vo.getCategory());
        model.setCountry(vo.getCountry());
        model.setInstruction(vo.getInstruction());
        model.setImgThumb(vo.getImgThumb());
        model.setYoutubeLink(vo.getYoutubeLink());
        model.setIngredients(vo.getIngredients());
        model.setMeasures(vo.getMeasures());
        model.setWebLink(vo.getWebLink());

        return model;
    }

    @Override
    public RecipeLatestVo inverseMap(RecipeModel model) {
        RecipeLatestVo vo = new RecipeLatestVo();
        vo.setIdRecipe(model.getIdRecipe());
        vo.setName(model.getName());
        vo.setCategory(model.getCategory());
        vo.setCountry(model.getCountry());
        vo.setInstruction(model.getInstruction());
        vo.setImgThumb(model.getImgThumb());
        vo.setYoutubeLink(model.getYoutubeLink());
        vo.setIngredients(model.getIngredients());
        vo.setMeasures(model.getMeasures());
        vo.setWebLink(model.getWebLink());

        return vo;
    }
}
