package com.example.daggerpractise.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggerpractise.R;
import com.example.daggerpractise.models.User;
import com.example.daggerpractise.ui.auth.AuthResource;
import com.example.daggerpractise.ui.auth.AuthViewModel;
import com.example.daggerpractise.ui.main.MainActivity;
import com.example.daggerpractise.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    ProfileViewModel viewModel;

    private TextView emailView;
    private TextView nameView;
    private TextView websiteView;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        emailView = view.findViewById(R.id.email);
        nameView = view.findViewById(R.id.username);
        websiteView = view.findViewById(R.id.website);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null) {
                    switch (userAuthResource.status) {

                        case AUTHENTICATED:{
                            setUserDetails(userAuthResource.data);
                            break;
                        }
                        case ERROR:{
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        emailView.setText(message);
        nameView.setText("error");
        websiteView.setText("error");
    }

    private void setUserDetails(User data) {
        emailView.setText(data.getEmail());
        nameView.setText(data.getUsername());
        websiteView.setText(data.getWebsite());
    }
}
