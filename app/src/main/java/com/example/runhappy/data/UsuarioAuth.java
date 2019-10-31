package com.example.runhappy.data;

public interface UsuarioAuth {
    void registrar(String email, String senha);

    void login(String email, String senha);

    void logout();

    boolean isAutenticado();
}
