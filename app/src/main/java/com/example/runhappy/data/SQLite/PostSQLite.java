package com.example.runhappy.data.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PostSQLite {


    private static final String TABLE = "curtidas_posts";
    private SQLiteHandle handle;

    public PostSQLite(SQLiteHandle handle){
        this.handle = handle;
    }

    public void curtirPost(String id){
        SQLiteDatabase db = handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);
        db.insert(TABLE, null, values);
        db.close();
    }

    public void descurtirPost(String id){
        SQLiteDatabase database = handle.getWritableDatabase();
        database.delete(TABLE, " id = ?", new String[] { id });
        database.close();
    }

    public boolean isCurtido(String id){
        SQLiteDatabase db = handle.getReadableDatabase();
        Cursor cursor = db.query(TABLE, new  String[] {"id"},
                "id = ?", new String[] {id}, null, null, null, null);

        if (cursor.moveToFirst()){
            //db.close();
            return true;
        }
        return false;
    }
}
