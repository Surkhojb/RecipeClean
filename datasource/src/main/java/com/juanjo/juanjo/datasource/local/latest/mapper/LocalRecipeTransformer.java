package com.juanjo.juanjo.datasource.local.latest.mapper;

import com.juanjo.juanjo.datasource.local.latest.RecipeLatestVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalRecipeTransformer {

    @Inject
    LocalRecipeMapper mapper;

    @Inject
    public LocalRecipeTransformer(){}

    public List<RecipeModel> transformList(List<RecipeLatestVo> vos){
        List<RecipeModel> list = new ArrayList<>();
        for(RecipeLatestVo vo : vos)
            list.add(mapper.map(vo));

        return list;
    }

    public List<RecipeLatestVo> transformFromModel(List<RecipeModel> models){
        List<RecipeLatestVo> list = new ArrayList<>();
        for(RecipeModel m : models)
            list.add(mapper.inverseMap(m));

        return list;
    }
}
