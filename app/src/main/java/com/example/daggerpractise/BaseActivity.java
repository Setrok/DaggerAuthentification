package com.example.daggerpractise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggerpractise.models.User;
import com.example.daggerpractise.ui.auth.AuthActivity;
import com.example.daggerpractise.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeAuthState();
    }

    private void observeAuthState(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    switch (userAuthResource.status){
                        case LOADING: {
                            Log.i(TAG,"Auth Loading");
                            break;
                        }
                        case AUTHENTICATED:{
                            Log.i(TAG,"Auth Success");
                            break;
                        }
                        case ERROR:{
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            Log.i(TAG,"Auth Back");
                            navLoginScreen();
                            break;
                        }
                    }
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
