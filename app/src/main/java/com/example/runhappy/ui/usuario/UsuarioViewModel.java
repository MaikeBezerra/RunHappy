package com.example.runhappy.ui.usuario;

import android.content.Context;

import com.example.runhappy.data.SQLite.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.SQLite.UsuarioDAOSQLite;
import com.example.runhappy.data.firebase.UsuarioDBFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.UsuarioChangeListener;

import java.util.List;

public class UsuarioViewModel {

    private UsuarioDAO dbSQLite;
    private UsuarioDAO dbFirebase;

    public UsuarioViewModel( Context context ) {
        SQLiteHandle handle = new SQLiteHandle(context);

        this.dbSQLite = new UsuarioDAOSQLite(handle);
        this.dbFirebase = new UsuarioDBFirebase();
    }

    public void adicionarUsuario(Usuario usuario){
        //dbSQLite.addUsuario(usuario);
        dbFirebase.addUsuario(usuario);
        changeUsuario(usuario);
    }

    public Usuario findUsuarioByEmail(String email){
        return dbSQLite.findByEmail(email);
    }

    private void changeUsuario(Usuario usuario){
        UsuarioChangeListener.getInstance().update(usuario);
    }

    public List<Usuario> getUsuarios(){
        return dbSQLite.findAll();
    }
}
