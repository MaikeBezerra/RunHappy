package com.example.runhappy.data;

public interface UsuarioAuth {
    void registrar(String nome, String email, String senha);

    void login(String email, String senha);

    void loginWithFacebook(String email, String senha);

    void setUsuarioLoged(String id);

    void logout();

    boolean isAutenticado();

    String idLoged();
}
