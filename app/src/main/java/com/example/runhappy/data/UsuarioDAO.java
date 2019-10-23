package com.example.runhappy.data;

import com.example.runhappy.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void addUsuario(Usuario usuario);

    void editUsuario(Usuario usuario);

    void deleteUsuario( int usuarioId);

    Usuario getUsuario( int usuarioId);

    List<Usuario> findAll();
}
