package com.example.runhappy.ui.usuario;

import android.app.Activity;
import android.content.Context;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.data.UsuarioDBFirebase;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.UsuarioChangeListener;

import java.util.List;

public class UsuarioViewModel {

    private UsuarioDAO dbSQLite;
    private UsuarioDAO dbFirebase;

    public UsuarioViewModel(Activity activity, Context context) {
        SQLiteHandle handle = new SQLiteHandle(context);

        this.dbSQLite = new UsuarioDAOSQLite(handle);
        this.dbFirebase = UsuarioDBFirebase.getInstance(activity, context);
    }

    public void adicionarUsuario(Usuario usuario){
        //dbSQLite.addUsuario(usuario);
        dbFirebase.addUsuario(usuario);
        changeUsuario(usuario);
    }

    public void logarUsuario(String email, String senha){
        dbFirebase.logar(email, senha);
    }

    public void editarUsuario(Usuario usuario) {
        dbSQLite.editUsuario(usuario);
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
