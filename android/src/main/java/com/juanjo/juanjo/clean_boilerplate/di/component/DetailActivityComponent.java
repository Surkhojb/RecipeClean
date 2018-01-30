package com.juanjo.juanjo.clean_boilerplate.di.component;

import com.juanjo.juanjo.clean_boilerplate.di.module.DetailActivityModule;
import com.juanjo.juanjo.clean_boilerplate.di.scope.PerActivity;
import com.juanjo.juanjo.clean_boilerplate.ui.activity.DetailActivity;

import dagger.Component;

/**
 * Created by juanj on 30/01/2018.
 */

@PerActivity
@Component(dependencies = RecipeAppComponent.class,modules = DetailActivityModule.class)
public interface DetailActivityComponent {
    void inject(DetailActivity activity);
}
