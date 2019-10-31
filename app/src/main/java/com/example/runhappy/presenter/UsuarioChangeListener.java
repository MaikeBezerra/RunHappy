package com.example.runhappy.presenter;

import com.example.runhappy.UsuarioObserver;
import com.example.runhappy.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioChangeListener {

    private List<UsuarioObserver> observers;

    public static UsuarioChangeListener listener;

    private UsuarioChangeListener(){
        this.observers = new ArrayList<>();
    }

    public static UsuarioChangeListener getInstance(){
        if (listener == null) {
            listener = new UsuarioChangeListener();
        }

        return listener;
    }

    public void adicionaObeserver(UsuarioObserver observer){
        observers.add(observer);
    }

    public void update(Usuario usuario){
        for (UsuarioObserver observer: observers) {
            observer.onChangeUsuario(usuario);
        }
    }
}
