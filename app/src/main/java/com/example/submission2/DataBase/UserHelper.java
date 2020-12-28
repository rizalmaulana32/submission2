package com.example.submission2.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.submission2.Model.UserModel;

import java.util.ArrayList;

import static com.example.submission2.DataBase.DatabaseContract.UserColumn.ID;
import static com.example.submission2.DataBase.DatabaseContract.UserColumn.AVATAR;
import static com.example.submission2.DataBase.DatabaseContract.UserColumn.TABLE_USER_NAME;
import static com.example.submission2.DataBase.DatabaseContract.UserColumn.USERNAME;

public class UserHelper {

    private static final String DATABASE_TABLE = TABLE_USER_NAME;
    private static DatabaseHelper databaseHelper;
    private static UserHelper INSTANCE;
    private static SQLiteDatabase database;

    public UserHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static UserHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new UserHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll(){
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC"
        );
    }

    public Cursor queryById(String id){
        return database.query(
                DATABASE_TABLE,
                null,
                ID + " + ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public ArrayList<UserModel> getDataUser(){
        ArrayList<UserModel> userGithub = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                USERNAME + " ASC",
                null);
        cursor.moveToFirst();
        UserModel userModel;
        if (cursor.getCount() > 0){
            do {
                userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                userModel.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                userModel.setAvatarUrl(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));
                userGithub.add(userModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }cursor.close();
        return userGithub;
    }

    public long userInsert(UserModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, userModel.getId());
        contentValues.put(USERNAME, userModel.getLogin());
        contentValues.put(AVATAR, userModel.getAvatarUrl());
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int userDelete(String id){
        return database.delete(TABLE_USER_NAME,ID + " = '" + id + "'", null);
    }

    public int DeleteProvider(String id) {
        return database.delete(TABLE_USER_NAME, ID+ "=?",new String[]{id});
    }
    public int UpdateProvider(String id, ContentValues values) {
        return database.update(TABLE_USER_NAME, values, ID + " =?", new String[]{id});
    }
    public long InsertProvider(ContentValues values) {
        return database.insert(TABLE_USER_NAME, null, values);
    }

}
