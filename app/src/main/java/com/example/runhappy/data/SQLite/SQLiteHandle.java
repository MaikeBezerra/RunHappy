package com.example.runhappy.data.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandle extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "runhappy";
    public static final int DATABASE_VERSION = 1;

    public SQLiteHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsuario = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, senha TEXT)";
        String createCorrida = "CREATE TABLE corrida (id INTEGER PRIMARY KEY, distancia INTEGER, tempo INTEGER, " +
                "ritmo INTEGER, idUsuario INTEGER, FOREIGN KEY (idUsuario) REFERENCES usuario(id))";
        String seguidoresUsuario = "CREATE TABLE seguidores (idSeguidor INTEGER PRIMARY KEY, " +
                "idSeguido INTEGER, FOREIGN KEY (idSeguido) REFERENCES usuario(id))";

        db.execSQL(createUsuario);
        db.execSQL(createCorrida);
        db.execSQL(seguidoresUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < newVersion) {
        String dropUsuario = "DROP TABLE IF EXISTS usuario";
        String dropCorrida = "DROP TABLE IF EXISTS corrida";
        String dropSeguidores = "DROP TABLE IF EXISTS seguidores";
        db.execSQL(dropUsuario);
        db.execSQL(dropCorrida);
        db.execSQL(dropSeguidores);
        onCreate(db);
//        }
    }


}
