package com.example.runhappy.data;

import com.example.runhappy.model.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioDAO {

    void addUsuario(Usuario usuario);

    void logar(String id);

    void editUsuario(String id, Map<String, Object> update);

    void deleteUsuario( int usuarioId);

    Usuario getUsuario(String usuarioId);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    List<Usuario> findAllSeguidores(String id);

}
