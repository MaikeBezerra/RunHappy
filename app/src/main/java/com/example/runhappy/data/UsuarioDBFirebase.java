package com.example.runhappy.data;

import android.content.Context;

import com.example.runhappy.model.Usuario;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

public class UsuarioDBFirebase implements UsuarioDAO {

    private FirebaseAnalytics firebaseAnalytics;

    public UsuarioDBFirebase(Context context){
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public void addUsuario(Usuario usuario) {
        
    }

    @Override
    public void editUsuario(Usuario usuario) {

    }

    @Override
    public void deleteUsuario(int usuarioId) {

    }

    @Override
    public Usuario getUsuario(int usuarioId) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }
}
