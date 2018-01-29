package com.juanjo.juanjo.datasource.local.random.mapper;

import com.juanjo.juanjo.datasource.base.Mapper;
import com.juanjo.juanjo.datasource.local.latest.RecipeLatestVo;
import com.juanjo.juanjo.datasource.local.random.RecipeRandomVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalRandomMapper implements Mapper<RecipeModel,RecipeRandomVo> {

    @Inject
    public LocalRandomMapper(){}

    @Override
    public RecipeModel map(RecipeRandomVo vo) {
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
    public RecipeRandomVo inverseMap(RecipeModel model) {
        RecipeRandomVo vo = new RecipeRandomVo();
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
