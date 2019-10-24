package com.example.runhappy.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.runhappy.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioDAOSQLite implements UsuarioDAO {

    private SQLiteHandle handle = null;

    public UsuarioDAOSQLite(SQLiteHandle handle){
        this.handle = handle;
    }

    @Override
    public void addUsuario(Usuario usuario) {
        SQLiteDatabase database = this.handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());

        database.insert("usuario", null, values);
        database.close();
    }

    @Override
    public void editUsuario(Usuario usuario) {
        SQLiteDatabase database = handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());

        database.update("usuario", values, " id = ?", new String[] { String.valueOf(usuario.getId()) });
    }

    @Override
    public void deleteUsuario(int usuarioId) {
        SQLiteDatabase database = handle.getWritableDatabase();
        database.delete("usuario", " id = ?", new String[] { String.valueOf(usuarioId) });
        database.close();
    }

    @Override
    public Usuario getUsuario(int usuarioId) {
        SQLiteDatabase db = handle.getReadableDatabase();
        Cursor cursor = db.query("usuario", new  String[] {"id", "nome", "email", "senha"},
                "id = ?", new String[] {String.valueOf(usuarioId)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
            Usuario usuario = new Usuario(cursor.getInt(0), cursor.getString(2),
                    cursor.getString(2), cursor.getString(3));
            return usuario;
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        SQLiteDatabase database = handle.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM usuarios", null);

        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(cursor.getInt(0), cursor.getString(2),
                                        cursor.getString(2), cursor.getString(3));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

}
