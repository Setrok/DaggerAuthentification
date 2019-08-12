package com.example.daggerpractise.di.main;

import com.example.daggerpractise.network.auth.AuthApi;
import com.example.daggerpractise.network.main.MainApi;
import com.example.daggerpractise.ui.main.posts.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostRecyclerAdapter provideRecyclerAdapter(){
        return new PostRecyclerAdapter();
    }

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

}
