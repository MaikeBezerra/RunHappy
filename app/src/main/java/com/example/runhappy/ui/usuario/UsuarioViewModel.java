package com.example.runhappy.ui.usuario;

import android.content.Context;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.UsuarioChangeListener;

import java.util.List;

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
        changeUsuario(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        usuarioDAO.editUsuario(usuario);
        changeUsuario(usuario);
    }

    public Usuario findUsuarioByEmail(String email){
        return usuarioDAO.findByEmail(email);
    }

    private void changeUsuario(Usuario usuario){
        UsuarioChangeListener.getInstance().update(usuario);
    }

    public List<Usuario> getUsuarios(){
        return usuarioDAO.findAll();
    }
}
