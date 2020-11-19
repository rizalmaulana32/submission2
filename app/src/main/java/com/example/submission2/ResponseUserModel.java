package com.example.submission2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseUserModel {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("result")
    private boolean result;

    @SerializedName("items")
    private ArrayList<UserModel> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ArrayList<UserModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<UserModel> items) {
        this.items = items;
    }
}
