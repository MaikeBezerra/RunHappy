package com.example.runhappy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHandle extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "runhappy";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, senha TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < newVersion) {
        String sql = "DROP TABLE IF EXISTS usuario";
        db.execSQL(sql);
        onCreate(db);
//        }
    }


}
