package com.juanjo.juanjo.clean_boilerplate.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.clean_boilerplate.interactor.MainThreadImpl;
import com.juanjo.juanjo.datasource.cloud.CloudDataSource;
import com.juanjo.juanjo.datasource.local.LocalDataSource;
import com.juanjo.juanjo.datasource.local.RecipeDatabase;
import com.juanjo.juanjo.datasource.local.favorite.FavoriteDao;
import com.juanjo.juanjo.datasource.local.latest.LatestDao;
import com.juanjo.juanjo.datasource.local.random.RandomDao;
import com.juanjo.juanjo.domain.executor.JobExecutor;
import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;
import com.juanjo.juanjo.repository.RecipeRepository;
import com.juanjo.juanjo.repository.cloud.ICloudDataSource;
import com.juanjo.juanjo.repository.local.ILocalDataSource;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juanj on 24/01/2018.
 */

@Module
public class RecipeAppModule {
    private static final String DB_NAME = "recipe_database";
    private static final String PREFERENCES = "recipe_preferences";

    private RecipeApp app;

    public RecipeAppModule(RecipeApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    RecipeApp providesCriptoApp(){
        return app;
    }

    @Provides
    @Singleton
    ThreadExecutor providesThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }

    @Provides
    @Singleton
    MainThread provideMainThread(MainThreadImpl mainThread){
        return mainThread;
    }

    @Provides
    @Singleton
    ICloudDataSource provideApiDatasource(CloudDataSource restApiDatasource){
        return restApiDatasource;
    }

    @Provides
    @Singleton
    EventBus provideEventBus(){
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    SharedPreferences providePreferences(){
        return app.getApplicationContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    IRecipeRepository provideApiRepository(RecipeRepository recipeRepository){
        return recipeRepository;
    }

    //TODO: LOCAL DATASOURCE
    @Provides
    @Singleton
    ILocalDataSource provideLocalDataSource(LocalDataSource localDatasource){
       return localDatasource;
   }

    @Provides
    @Singleton
    RecipeDatabase providesCriptoDatabase(){
        return Room.databaseBuilder(app.getApplicationContext(),RecipeDatabase.class,DB_NAME).build();
    }

    @Provides
    @Singleton
    LatestDao providesCriptoDao(RecipeDatabase database){
        return database.latestDao();
    }

    @Provides
    @Singleton
    FavoriteDao providesFavoriteDao(RecipeDatabase database){
        return database.favoriteDao();
    }

    @Provides
    @Singleton
    RandomDao provideRandomDao(RecipeDatabase database){
        return database.randomDao();
    }

    /* TODO: PREFERENCESD DATASOURCE
    @Provides
    @Singleton
    ISettingsPreferences providePreferencesManager(){
        return new PreferencesManager(providePreferences());
    }
    */
}
