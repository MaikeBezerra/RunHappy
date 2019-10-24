package com.example.runhappy.data;

import com.example.runhappy.model.Usuario;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UsuarioDBFirebase implements UsuarioDAO {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

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
