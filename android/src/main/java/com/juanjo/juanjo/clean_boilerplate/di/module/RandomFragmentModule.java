package com.juanjo.juanjo.clean_boilerplate.di.module;

import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.RandomFragment;
import com.juanjo.presentation.randomfragment.RandomFragmentContract;
import com.juanjo.presentation.randomfragment.RandomFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juanj on 26/01/2018.
 */

@Module
public class RandomFragmentModule {
    RandomFragment fragment;

    public RandomFragmentModule(RandomFragment fragment) {
        this.fragment = fragment;
    }

    @PerFragment
    @Provides
    RandomFragmentContract.View provideView(){
        return fragment;
    }

    @PerFragment
    @Provides
    RandomFragmentContract.Presenter providePresenter(RandomFragmentPresenter presenter){
        return presenter;
    }
}
