package com.example.runhappy.data;

import com.example.runhappy.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void addUsuario(Usuario usuario);

    void adicionarSeguidor(Usuario usuario, String emailSeguido);

    void editUsuario(Usuario usuario);

    void deleteUsuario( int usuarioId);

    Usuario getUsuario( int usuarioId);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    List<Usuario> findAllSeguidores(String myEmail);
}
