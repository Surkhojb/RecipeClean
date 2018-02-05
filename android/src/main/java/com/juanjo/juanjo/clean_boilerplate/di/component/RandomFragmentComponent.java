package com.juanjo.juanjo.clean_boilerplate.di.component;

import com.juanjo.juanjo.clean_boilerplate.di.module.RandomFragmentModule;
import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.RandomFragment;

import dagger.Component;

/**
 * Created by juanj on 26/01/2018.
 */

@PerFragment
@Component(dependencies = RecipeAppComponent.class,modules = RandomFragmentModule.class)
public interface RandomFragmentComponent {
    void inject(RandomFragment fragment);
}
