package com.example.runhappy.ui.usuario;

import android.content.Context;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;

public class UsuarioViewModel {

    private Context context;
    private UsuarioDAO usuarioDAO;

    public UsuarioViewModel(Context context) {
        SQLiteHandle handle = new SQLiteHandle(context);

        this.context = context;
        this.usuarioDAO = new UsuarioDAOSQLite(handle);
    }

    public void adicionarUsuario(Usuario usuario){
        usuarioDAO.addUsuario(usuario);
    }



}
