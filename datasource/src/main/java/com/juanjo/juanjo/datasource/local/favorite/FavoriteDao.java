package com.juanjo.juanjo.datasource.local.favorite;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.juanjo.juanjo.datasource.local.favorite.model.FavoriteVo;

import java.util.List;

/**
 * Created by juanj on 26/01/2018.
 */

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    List<FavoriteVo> getFavorites();

    @Query("SELECT * FROM favorites WHERE id=:id")
    FavoriteVo getFavoriteById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(FavoriteVo favoriteVo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FavoriteVo> recipes);
}
