package com.example.daggerpractise.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.di.ViewModelKey;
import com.example.daggerpractise.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

//    IF THERE WERE ANOTHER VIEW MODEL
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel.class)
//    public abstract ViewModel bindAuthViewModel(MainViewModel viewModel);

}
