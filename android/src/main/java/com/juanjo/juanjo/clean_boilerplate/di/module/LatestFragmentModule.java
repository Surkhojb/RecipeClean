package com.juanjo.juanjo.clean_boilerplate.di.module;

import com.juanjo.juanjo.clean_boilerplate.di.scope.PerFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.LatestFragment;
import com.juanjo.presentation.latestfragment.LatestFragmentContract;
import com.juanjo.presentation.latestfragment.LatestFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juanj on 24/01/2018.
 */

@Module
public class LatestFragmentModule {

    LatestFragment latestFragment;

    public LatestFragmentModule(LatestFragment latestFragment) {
        this.latestFragment = latestFragment;
    }

    @PerFragment
    @Provides
    LatestFragmentContract.View providesView(){return latestFragment;}

    @PerFragment
    @Provides
    LatestFragmentContract.Presenter providesPresenter(LatestFragmentPresenter presenter){
        return presenter;
    }
}
