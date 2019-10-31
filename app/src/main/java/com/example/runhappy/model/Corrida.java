package com.example.runhappy.model;

public class Corrida {

    private int id;
    private int distancia;
    private int tempo;
    private int ritmoMedio;

    public Corrida(){}

    public Corrida(int distancia, int tempo, int ritmoMedio) {
        this.distancia = distancia;
        this.tempo = tempo;
        this.ritmoMedio = ritmoMedio;
    }

    public Corrida(int id, int distancia, int tempo, int ritmoMedio) {
        this(distancia, tempo, ritmoMedio);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getRitmoMedio() {
        return ritmoMedio;
    }

    public void setRitmoMedio(int ritmoMedio) {
        this.ritmoMedio = ritmoMedio;
    }
}
