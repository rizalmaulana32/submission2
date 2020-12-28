package com.example.consumerapp.DataBase;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTH = "com.example.submission2";
    public static final String SCHEME = "content";

    public static final class UserColumn implements BaseColumns{
        public static final String TABLE_USER_NAME = "user";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String AVATAR = "avatar";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTH)
                .appendPath(TABLE_USER_NAME)
                .build();
    }

    public static String getFavoriteItem(Cursor cursor, String column){
        return cursor.getString(cursor.getColumnIndex(column));
    }

}
