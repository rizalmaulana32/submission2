package com.example.submission2;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "github_users_fav";

    static final class NoteColumns implements BaseColumns {
        static String NAME = "name";
        static String USERNAME = "user_name";
    }

}
