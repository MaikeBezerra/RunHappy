package com.example.runhappy.model;

import java.util.List;

public class Feed {

    private List<Corrida> corridas;

    public Feed(List<Corrida> corridas) {
        this.corridas = corridas;
    }

    public Feed(){}

    public List<Corrida> getCorridas() {
        return corridas;
    }

    public void setCorridas(List<Corrida> corridas) {
        this.corridas = corridas;
    }
}
