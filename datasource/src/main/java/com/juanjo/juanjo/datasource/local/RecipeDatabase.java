package com.juanjo.juanjo.datasource.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.juanjo.juanjo.datasource.local.latest.RecipeLatestVo;
import com.juanjo.juanjo.datasource.local.favorite.FavoriteDao;
import com.juanjo.juanjo.datasource.local.favorite.model.FavoriteVo;
import com.juanjo.juanjo.datasource.local.latest.LatestDao;
import com.juanjo.juanjo.datasource.local.random.RandomDao;
import com.juanjo.juanjo.datasource.local.random.RecipeRandomVo;

import static com.juanjo.juanjo.datasource.local.RecipeDatabase.DB_VERSION;

/**
 * Created by juanj on 26/01/2018.
 */

@Database(entities = {RecipeLatestVo.class, RecipeRandomVo.class,FavoriteVo.class}, version = DB_VERSION,exportSchema = false)
@TypeConverters({DatabaseConverters.class})
public abstract class RecipeDatabase extends RoomDatabase {
    static final int DB_VERSION = 1;

    public abstract LatestDao latestDao();
    public abstract RandomDao randomDao();
    public abstract FavoriteDao favoriteDao();
}
