package com.juanjo.juanjo.datasource.local.favorite.model.mapper;

import com.juanjo.juanjo.datasource.local.favorite.model.FavoriteVo;
import com.juanjo.juanjo.domain.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by juanj on 26/01/2018.
 */

public class LocalFavoriteTransformer {

    @Inject
    LocalFavoriteMapper mapper;

    @Inject
    public LocalFavoriteTransformer(){}

    public List<RecipeModel> transformList(List<FavoriteVo> vos){
        List<RecipeModel> list = new ArrayList<>();
        for(FavoriteVo vo : vos)
            list.add(mapper.map(vo));

        return list;
    }

    public FavoriteVo mapToFavorite(RecipeModel model) {
        return mapper.inverseMap(model);
    }
}
