package com.example.runhappy.ui.login;

import android.content.Context;

import com.example.runhappy.LoginFacebookObserver;
import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.data.UsuarioAuth;
import com.example.runhappy.data.firebase.UsuarioFirebaseAuth;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.presenter.OnLoginEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel implements OnLoginEventListener {

    private Usuario usuario;
    private String idLoged;

    private UsuarioAuth auth;
    private Context context;

    public static LoginViewModel instance;

    private List<UsuarioObserver> observers;
    private List<LoginFacebookObserver> facebookObservers;

    private LoginViewModel(Context context){
        this.context = context;
        auth = new UsuarioFirebaseAuth( this );
        observers = new ArrayList<>();
        facebookObservers = new ArrayList<>();
    }

    public static LoginViewModel getInstance(Context context) {
        if(instance == null){
            instance = new LoginViewModel(context);
        }

        return instance;
    }

    public boolean isAuthenticated(){ return auth.isAutenticado(); }

    public Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setIdLoged(){

        if (idLogedUser() != null) {
            auth.setUsuarioLoged(idLogedUser());
        }

    }

    public String idLogedUser(){
        return auth.idLoged();
    }

    public void logar(String email, String senha) {
        auth.login(email, senha);
    }

    public void registrar(String nome, String email, String senha){ auth.registrar(nome, email, senha);}

    public void addObserver(UsuarioObserver observer){
        observers.add(observer);
    }

    public void addFacebookObserver(LoginFacebookObserver observer){
        facebookObservers.add(observer);
    }

    @Override
    public void onUsuarioLoged(Usuario usuario) {
        setUsuario(usuario);
        update();
    }

    @Override
    public void onUsuarioFacebookNotLoged() {
        for (LoginFacebookObserver observer : facebookObservers){
            observer.onNotLoged();
        }
    }

    private void update(){
        for ( UsuarioObserver observer: observers) {
            observer.onChangeUsuario(usuario);
        }
    }
}
