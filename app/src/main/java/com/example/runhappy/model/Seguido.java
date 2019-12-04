package com.example.runhappy.model;

import java.util.List;

public class Seguido {

    private List<Usuario> seguidores;

    public Seguido(){}

    public Seguido(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }
}
