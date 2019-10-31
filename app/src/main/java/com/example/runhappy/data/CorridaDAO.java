package com.example.runhappy.data;

import com.example.runhappy.model.Corrida;

import java.util.List;

public interface CorridaDAO {

    void adicionarCorrida(Corrida corrida);

    void editarCorrida(Corrida corrida);

    void deleteCorrida( int corridaId);

    Corrida getCorrida( int corridaId);

    List<Corrida> findAll();
}
