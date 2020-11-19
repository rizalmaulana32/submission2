package com.example.submission2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("search/users")
    @Headers("Authorization: token 89c3cf5406c6166808b7ea1a7a76c1b0ca7c979f")
    Call<ResponseUserModel> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token 89c3cf5406c6166808b7ea1a7a76c1b0ca7c979f")
    Call<DetailUserModel> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 89c3cf5406c6166808b7ea1a7a76c1b0ca7c979f")
    Call<FollowerModel> getFollower(
            @Path("username") String username
    );

}
