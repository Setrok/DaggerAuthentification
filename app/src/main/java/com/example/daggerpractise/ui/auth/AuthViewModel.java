package com.example.daggerpractise.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.SessionManager;
import com.example.daggerpractise.models.User;
import com.example.daggerpractise.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    AuthApi authApi;
    private static final String Tag="AuthViewModel";
    private SessionManager sessionManager;

    @Inject
    public  AuthViewModel(AuthApi authApi,SessionManager sessionManager){

        this.authApi = authApi;
        this.sessionManager = sessionManager;

    }

    public void authencticateWithId(int userId){
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return  LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId()==-1){
                                    return AuthResource.error("Failed auth",(User) null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState(){

        return sessionManager.getAuthUser();
    }

}

//        authApi.getUser(1)
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<User>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//                }
//
//        @Override
//        public void onNext(User user) {
//                Log.d(Tag,"onNext() " + user.getUsername());
//                }
//
//        @Override
//        public void onError(Throwable e) {
//                Log.e(Tag,"Error() " + e.getMessage());
//                }
//
//        @Override
//        public void onComplete() {
//
//                }
//                });
