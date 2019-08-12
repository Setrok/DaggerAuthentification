package com.example.daggerpractise.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractise.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);


    //Exactly the same thing but less efficient
//    @Provides
//    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory factory){
//        return factory;
//    }

}
