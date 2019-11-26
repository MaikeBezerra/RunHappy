package com.example.runhappy.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.runhappy.model.Corrida;

import java.util.ArrayList;
import java.util.List;

public class CorridaDAOSQLite implements CorridaDAO{

    private SQLiteHandle handle;

    public CorridaDAOSQLite(SQLiteHandle handle) {
        this.handle = handle;
    }

    @Override
    public void adicionarCorrida(Corrida corrida) {
        SQLiteDatabase db = handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("distancia", corrida.getDistancia());
        values.put("tempo", corrida.getTempo());
        values.put("ritmo", corrida.getRitmoMedio());
        values.put("idUsuario", corrida.getCorredor());

        db.insert("corrida", null, values);
        db.close();
    }

    @Override
    public void editarCorrida(Corrida corrida) {
        SQLiteDatabase database = handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("distancia", corrida.getDistancia());
        values.put("tempo", corrida.getTempo());
        values.put("ritmo", corrida.getRitmoMedio());
        values.put("idUsuario", corrida.getCorredor());

        database.update("corrida", values, " id = ?", new String[] { String.valueOf(corrida.getId()) });
    }

    @Override
    public void deleteCorrida(int corridaId) {
        SQLiteDatabase database = handle.getWritableDatabase();
        database.delete("corrida", " id = ?", new String[] { String.valueOf(corridaId) });
        database.close();
    }

    @Override
    public Corrida getCorrida(int corridaId) {
        SQLiteDatabase db = handle.getReadableDatabase();
        Cursor cursor = db.query("corrida", new  String[] {"id", "distancia", "tempo", "ritmo"},
                "id = ?", new String[] {String.valueOf(corridaId)}, null, null, null, null);


        if (cursor.moveToFirst()){
            Corrida corrida = new Corrida(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
            return corrida;
        }
        return null;
    }

    @Override
    public List<Corrida> findAll() {
        List<Corrida> corridas = new ArrayList<>();

        SQLiteDatabase database = handle.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM corrida", null);

        if (cursor.moveToFirst()) {
            do {
                Corrida corrida = new Corrida(cursor.getInt(0), cursor.getInt(1),
                        cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
                corridas.add(corrida);
            } while (cursor.moveToNext());
        }
        return corridas;
    }

    @Override
    public List<Corrida> findAll(String idUsuario) {
        List<Corrida> corridas = new ArrayList<>();

        SQLiteDatabase database = handle.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM corrida c WHERE c.idUsuario = " + idUsuario, null);

        if (cursor.moveToFirst()) {
            do {
                Corrida corrida = new Corrida(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getString(4));
                corridas.add(corrida);
            } while (cursor.moveToNext());
        }
        return corridas;
    }
}
