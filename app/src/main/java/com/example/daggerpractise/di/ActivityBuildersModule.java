package com.example.daggerpractise.di;

import com.example.daggerpractise.di.auth.AuthModule;
import com.example.daggerpractise.di.auth.AuthScope;
import com.example.daggerpractise.di.auth.AuthViewModelsModule;
import com.example.daggerpractise.di.main.MainFragmentBuildersModule;
import com.example.daggerpractise.di.main.MainModule;
import com.example.daggerpractise.di.main.MainScope;
import com.example.daggerpractise.di.main.MainViewModelModule;
import com.example.daggerpractise.ui.auth.AuthActivity;
import com.example.daggerpractise.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
