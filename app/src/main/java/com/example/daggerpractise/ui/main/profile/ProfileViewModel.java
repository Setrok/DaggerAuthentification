package com.example.daggerpractise.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.SessionManager;
import com.example.daggerpractise.models.User;
import com.example.daggerpractise.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return sessionManager.getAuthUser();
    }

}
