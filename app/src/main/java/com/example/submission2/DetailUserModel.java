package com.example.submission2;

import com.google.gson.annotations.SerializedName;


public class DetailUserModel {

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String login;

    @SerializedName("company")
    private String company;

    @SerializedName("location")
    private String location;

    @SerializedName("public_repos")
    private String repository;

    @SerializedName("following")
    private String following;

    @SerializedName("followers")
    private String followers;

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getRepository() {
        return repository;
    }

}
