package com.example.submission2.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.submission2.DataBase.DatabaseContract.UserColumn.TABLE_USER_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbgithubuser";
    private final static int DATABASE_VERSION = 2;

    private final static String SQL_CREATE_TABLE_NOTE = String.format(
            "CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            TABLE_USER_NAME,
            DatabaseContract.UserColumn.ID,
            DatabaseContract.UserColumn.USERNAME,
            DatabaseContract.UserColumn.AVATAR);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
        onCreate(sqLiteDatabase);
    }
}
