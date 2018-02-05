package com.juanjo.juanjo.clean_boilerplate.di.component;

import com.juanjo.juanjo.clean_boilerplate.di.module.FavoriteFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.FavoriteFragment;

import dagger.Component;

/**
 * Created by juanj on 24/01/2018.
 */

@PerFragment
@Component(dependencies = RecipeAppComponent.class,modules = FavoriteFragmentModule.class)
public interface FavoriteFragmentComponent {
    void inject(FavoriteFragment fragment);
}
