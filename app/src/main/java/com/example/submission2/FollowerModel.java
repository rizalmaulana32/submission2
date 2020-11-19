package com.example.submission2;

import com.google.gson.annotations.SerializedName;

public class FollowerModel {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("login")
    private String login;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
