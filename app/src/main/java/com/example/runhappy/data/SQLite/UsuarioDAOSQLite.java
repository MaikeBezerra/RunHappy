package com.example.runhappy.data.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDAOSQLite implements UsuarioDAO {

    private SQLiteHandle handle;

    public UsuarioDAOSQLite(SQLiteHandle handle){
        this.handle = handle;
    }

    @Override
    public void addUsuario(Usuario usuario) {
        SQLiteDatabase database = this.handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());

        database.insert("usuario", null, values);
        database.close();
    }

    @Override
    public void logar(String id) {

    }

    @Override
    public void editUsuario(String id, Map<String, Object> update) {

    }

    public void editUsuario(Usuario usuario) {
        SQLiteDatabase database = handle.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());

        database.update("usuario", values, " id = ?", new String[] { String.valueOf(usuario.getId()) });
        database.close();
    }

    @Override
    public void deleteUsuario(int usuarioId) {
        SQLiteDatabase database = handle.getWritableDatabase();
        database.delete("usuario", " id = ?", new String[] { String.valueOf(usuarioId) });
        database.close();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        SQLiteDatabase db = handle.getReadableDatabase();
        Cursor cursor = db.query("usuario", new  String[] {"id", "nome", "email", "senha"},
                "id = ?", new String[] {usuarioId}, null, null, null, null);

        if (cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2));
            db.close();
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario findByEmail(String email) {
        SQLiteDatabase db = handle.getReadableDatabase();
        Cursor cursor = db.query("usuario", new String[] {"id", "nome", "email", "senha"},
                "email = ?", new String[] {email}, null, null, null, null);
        if (cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2));
            db.close();
            return usuario;
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase database = handle.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM usuario", null);
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(1),
                                        cursor.getString(2));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        database.close();
        return usuarios;
    }

    @Override
    public List<Usuario> findAllSeguidores(String myEmail) {
        Usuario usuario = findByEmail(myEmail);
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase database = handle.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT s FROM seguidores s WHERE s.idSeguidor = " + usuario.getId(), null);
        if (cursor.moveToFirst()) {
            do {
                Usuario seguido = getUsuario(cursor.getString(1));
                if (seguido != null) { usuarios.add(seguido); }
            } while (cursor.moveToNext());
        }
        database.close();
        return usuarios;
    }

}
