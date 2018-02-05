package com.juanjo.juanjo.datasource.local.latest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by juanj on 26/01/2018.
 */

@Dao
public interface LatestDao {
    @Query("SELECT * FROM latest")
    List<RecipeLatestVo> getLatests();

    @Query("SELECT * FROM latest WHERE id=:id")
    RecipeLatestVo getLastestById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RecipeLatestVo favoriteVo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<RecipeLatestVo> recipes);
}
