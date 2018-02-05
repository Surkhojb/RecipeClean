package com.juanjo.juanjo.clean_boilerplate.di.component;

import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.clean_boilerplate.di.module.CloudDataSourceModule;
import com.juanjo.juanjo.clean_boilerplate.di.module.RecipeAppModule;
import com.juanjo.juanjo.domain.executor.MainThread;
import com.juanjo.juanjo.domain.executor.ThreadExecutor;
import com.juanjo.juanjo.domain.repository.IRecipeRepository;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by juanj on 11/01/2018.
 */

@Singleton
@Component(modules = {CloudDataSourceModule.class, RecipeAppModule.class})
public interface RecipeAppComponent {
    void inject(RecipeApp app);

    ThreadExecutor threadExecutor();

    MainThread mainThread();

    IRecipeRepository apiRepository();

    EventBus provideEventBus();
}
