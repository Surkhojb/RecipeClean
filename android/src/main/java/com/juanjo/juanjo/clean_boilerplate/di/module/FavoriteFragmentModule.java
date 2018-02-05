package com.juanjo.juanjo.clean_boilerplate.di.module;

import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.FavoriteFragment;
import com.juanjo.presentation.favoritefragment.FavoriteFragmentContract;
import com.juanjo.presentation.favoritefragment.FavoriteFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juanj on 24/01/2018.
 */

@Module
public class FavoriteFragmentModule {

    FavoriteFragment favoriteFragment;

    public FavoriteFragmentModule(FavoriteFragment fragment) {
        this.favoriteFragment = fragment;
    }

    @PerFragment
    @Provides
    FavoriteFragmentContract.View providesView(){return favoriteFragment;}

    @PerFragment
    @Provides
    FavoriteFragmentContract.Presenter providesPresenter(FavoriteFragmentPresenter presenter){
        return presenter;
    }
}
