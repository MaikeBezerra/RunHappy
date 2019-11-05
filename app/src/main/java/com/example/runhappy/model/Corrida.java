package com.example.runhappy.model;

public class Corrida {

    private int id;
    private double distancia;
    private long tempo;
    private double ritmoMedio;
    private int corredor;

    public Corrida(){}

    public Corrida(double distancia, long tempo, double ritmoMedio, int corredor) {
        this.distancia = distancia;
        this.tempo = tempo;
        this.ritmoMedio = ritmoMedio;
        this.corredor = corredor;
    }

    public Corrida(int id, double distancia, long tempo, double ritmoMedio, int corredor) {
        this(distancia, tempo, ritmoMedio, corredor);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public double getRitmoMedio() {
        return ritmoMedio;
    }

    public void setRitmoMedio(int ritmoMedio) {
        this.ritmoMedio = ritmoMedio;
    }

    public int getCorredor() {
        return corredor;
    }

    public void setCorredor(int corredor) {
        this.corredor = corredor;
    }

    public String getDistanciaFormatada(){
        String distanciaStr = String.valueOf(distancia);
        double auxDistancia = distancia;

        if (auxDistancia > 1000){
            distanciaStr = (int) Math.floor(auxDistancia/1000)+"km"+" "+(int) (auxDistancia%1000)+"m";
        }else{
            distanciaStr = String.valueOf((int) (auxDistancia%1000)+"m");
        }

        return distanciaStr;
    }

    public String getTempoFormatado(){
        long auxTempo = tempo;
        int horas = (int) Math.floor((auxTempo/(3.6*1000000)));
        auxTempo -= horas;
        int minutos = (int) Math.floor((((auxTempo)/60000)));
        auxTempo -= minutos;
        int segundos = (int) Math.floor((auxTempo - minutos)/1000);
        auxTempo -= segundos;

        String tempoStr = horas + "h"+" "+minutos+"min"+" "+segundos+"s";

        return tempoStr;
    }

    public String getRitmoMedioFormatado(){
        return String.valueOf(ritmoMedio);
    }
}
