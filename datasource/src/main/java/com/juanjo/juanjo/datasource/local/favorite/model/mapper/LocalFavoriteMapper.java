package com.juanjo.juanjo.datasource.local.favorite.model.mapper;

import com.juanjo.juanjo.datasource.base.Mapper;
import com.juanjo.juanjo.datasource.local.favorite.model.FavoriteVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalFavoriteMapper implements Mapper<RecipeModel,FavoriteVo> {

    @Inject
    public LocalFavoriteMapper(){}

    @Override
    public RecipeModel map(FavoriteVo vo) {
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
    public FavoriteVo inverseMap(RecipeModel model) {
        FavoriteVo vo = new FavoriteVo();
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
