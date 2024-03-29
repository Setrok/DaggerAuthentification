package com.example.daggerpractise.network.main;

import com.example.daggerpractise.models.Post;
import com.example.daggerpractise.models.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id// QUERY STANDS FOR ? IN URL ADDRESS
    );
}
