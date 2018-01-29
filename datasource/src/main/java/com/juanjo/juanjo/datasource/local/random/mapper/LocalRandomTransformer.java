package com.juanjo.juanjo.datasource.local.random.mapper;

import com.juanjo.juanjo.datasource.local.latest.RecipeLatestVo;
import com.juanjo.juanjo.datasource.local.random.RecipeRandomVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalRandomTransformer {

    @Inject
    LocalRandomMapper mapper;

    @Inject
    public LocalRandomTransformer(){}

    public List<RecipeModel> transformList(List<RecipeRandomVo> vos){
        List<RecipeModel> list = new ArrayList<>();
        for(RecipeRandomVo vo : vos)
            list.add(mapper.map(vo));

        return list;
    }
}
