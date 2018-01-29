package com.juanjo.juanjo.clean_boilerplate.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.datasource.cloud.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juanj on 24/01/2018.
 */

@Module
public class CloudDataSourceModule {
    private static final String BASE_URL = "http://www.themealdb.com/";
    private RecipeApp app;

    public CloudDataSourceModule(RecipeApp app){
        this.app = app;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    Gson providesGson(){
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson){
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    ApiService providesApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
