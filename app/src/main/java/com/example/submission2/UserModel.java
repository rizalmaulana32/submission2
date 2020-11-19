package com.example.submission2;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserModel {

    @SerializedName("login")
    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

}
