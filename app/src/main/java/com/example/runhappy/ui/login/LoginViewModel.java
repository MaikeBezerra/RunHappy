package com.example.runhappy.ui.login;

import android.content.Context;

import com.example.runhappy.data.UsuarioAuth;
import com.example.runhappy.data.firebase.UsuarioFirebaseAuth;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnLoginEventListener;

public class LoginViewModel implements OnLoginEventListener {

    private Usuario usuario;
    private String idLoged;

    private UsuarioAuth auth;
    private Context context;

    public LoginViewModel(Context context){
        this.context = context;
        auth = new UsuarioFirebaseAuth( this );
    }


    public Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String idLogedUser(){
        return auth.idLoged();
    }

    public void logar(String email, String senha) {
        auth.login(email, senha);
    }

    public void registrar(String nome, String email, String senha){ auth.registrar(nome, email, senha);}

    @Override
    public void onUsuarioLoged(Usuario usuario) {
        setUsuario(usuario);
    }
}
