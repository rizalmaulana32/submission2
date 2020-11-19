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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }
}
