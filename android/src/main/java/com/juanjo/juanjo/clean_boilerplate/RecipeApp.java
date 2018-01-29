package com.juanjo.juanjo.clean_boilerplate;

import android.app.Application;

import com.juanjo.juanjo.clean_boilerplate.di.component.DaggerRecipeAppComponent;
import com.juanjo.juanjo.clean_boilerplate.di.component.RecipeAppComponent;
import com.juanjo.juanjo.clean_boilerplate.di.module.CloudDataSourceModule;
import com.juanjo.juanjo.clean_boilerplate.di.module.RecipeAppModule;

/**
 * Created by juanj on 22/01/2018.
 */

public class RecipeApp extends Application {
    private RecipeAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    void injectDependencies(){
        appComponent = DaggerRecipeAppComponent.builder()
                .recipeAppModule(new RecipeAppModule(this))
                .cloudDataSourceModule(new CloudDataSourceModule(this))
                .build();

        appComponent.inject(this);
    }

    public RecipeAppComponent getAppComponent(){return appComponent;}
}
