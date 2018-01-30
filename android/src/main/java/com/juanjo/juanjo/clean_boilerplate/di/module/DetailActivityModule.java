package com.juanjo.juanjo.clean_boilerplate.di.module;

import com.juanjo.juanjo.clean_boilerplate.di.scope.PerActivity;
import com.juanjo.juanjo.clean_boilerplate.ui.activity.DetailActivity;
import com.juanjo.presentation.detailactivity.DetailActivityContract;
import com.juanjo.presentation.detailactivity.DetailActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juanj on 30/01/2018.
 */

@Module
public class DetailActivityModule {

    DetailActivity activity;

    public DetailActivityModule(DetailActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    DetailActivityContract.View provideView(){
        return this.activity;
    }

    @PerActivity
    @Provides
    DetailActivityContract.Presenter providePresenter(DetailActivityPresenter presenter){
        return presenter;
    }
}
