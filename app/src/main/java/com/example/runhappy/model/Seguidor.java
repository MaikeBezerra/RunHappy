package com.example.runhappy.model;

import java.util.List;

public class Seguidor {

    private List<Usuario> seguidores;

    public Seguidor(){}

    public Seguidor(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }
}
