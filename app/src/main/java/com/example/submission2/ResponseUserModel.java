package com.example.submission2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseUserModel {

    @SerializedName("items")
    private ArrayList<UserModel> items;

    public ArrayList<UserModel> getItems() {
        return items;
    }

}
