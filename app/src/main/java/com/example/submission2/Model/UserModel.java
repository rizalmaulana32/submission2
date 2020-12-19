package com.example.submission2.Model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserModel implements Parcelable {

    @SerializedName("login")
    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("name")
    String name;

    @SerializedName("id")
    private int id;

    public UserModel() {
    }

    protected UserModel(android.os.Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
        name = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(android.os.Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
