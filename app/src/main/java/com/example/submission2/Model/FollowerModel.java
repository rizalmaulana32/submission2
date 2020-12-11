package com.example.submission2.Model;

import com.google.gson.annotations.SerializedName;

public class FollowerModel {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("login")
    private String login;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }

}
