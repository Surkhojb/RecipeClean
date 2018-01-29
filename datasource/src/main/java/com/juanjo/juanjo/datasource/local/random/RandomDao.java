package com.juanjo.juanjo.datasource.local.random;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by juanj on 26/01/2018.
 */

@Dao
public interface RandomDao {
    @Query("SELECT * FROM random")
    List<RecipeRandomVo> getRandoms();

    @Query("SELECT * FROM random WHERE id=:id")
    RecipeRandomVo getRandomById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RecipeRandomVo favoriteVo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecipeRandomVo> recipes);
}
