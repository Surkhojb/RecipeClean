package com.juanjo.juanjo.clean_boilerplate.di.component;

import com.juanjo.juanjo.clean_boilerplate.di.module.LatestFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.LatestFragment;

import dagger.Component;

/**
 * Created by juanj on 24/01/2018.
 */

@PerFragment
@Component(dependencies = RecipeAppComponent.class,modules = LatestFragmentModule.class)
public interface LatestFragmentComponent {
    void inject(LatestFragment fragment);
}
