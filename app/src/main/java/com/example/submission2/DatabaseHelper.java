package com.example.submission2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "github_user";

    private final static int DATABASE_VERSION = 1;

    private final static String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s " +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.NAME,
            DatabaseContract.NoteColumns.USERNAME);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
