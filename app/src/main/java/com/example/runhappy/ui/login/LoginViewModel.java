package com.example.runhappy.ui.login;

import android.content.Context;

import com.example.runhappy.data.UsuarioAuth;
import com.example.runhappy.data.UsuarioFirebaseAuth;
import com.example.runhappy.model.Usuario;

public class LoginViewModel {

    private Usuario usuario;
    private UsuarioAuth auth;

    public static LoginViewModel instance;
    private Context context;

    private LoginViewModel(Context context){
        this.context = context;
        auth = UsuarioFirebaseAuth.getInstance(context);
    }

    public static LoginViewModel getInstance(Context context) {
        if(instance == null) {
            instance = new LoginViewModel(context);
        }

        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String idLogedUser(){
        return auth.idLoged();
    }

    public void logar(String email, String senha) {
        auth.login(email, senha);
    }
}
