package com.example.runhappy.model;

import android.location.Location;

import java.util.List;

public class Corrida {

    private String id;
    private double distancia;
    private long tempo;
    private double ritmoMedio;
    private String corredor;
    private String descricao;
    private List<Location> locations;

    public Corrida(){}

    public Corrida(double distancia, long tempo, double ritmoMedio, String corredor, String descricao, List<Location> locations) {
        this.distancia = distancia;
        this.tempo = tempo;
        this.ritmoMedio = ritmoMedio;
        this.corredor = corredor;
        this.descricao = descricao;
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Corrida(String id, double distancia, long tempo, double ritmoMedio, String corredor, String descricao, List<Location> locations) {
        this(distancia, tempo, ritmoMedio, corredor, descricao, locations);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
