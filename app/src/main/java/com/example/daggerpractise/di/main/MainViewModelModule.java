package com.example.daggerpractise.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.di.ViewModelKey;
import com.example.daggerpractise.ui.main.posts.PostsViewModel;
import com.example.daggerpractise.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);

}
