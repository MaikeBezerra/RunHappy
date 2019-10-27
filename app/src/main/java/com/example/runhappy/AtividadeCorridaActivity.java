package com.example.runhappy;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class AtividadeCorridaActivity extends AppCompatActivity {

//    private static TextView section_label;
//    private static long initialTime;
//    private static Handler handler;
//    private static boolean isRunning;
//    private static final long MILLIS_IN_SEC = 1000L;
//    private static final int SECS_IN_MIN = 60;

    private Chronometer chronometer;
    private long milisegundos;
    private boolean estaContando;
    private Button pause;
    private Button bandeira;
    private double distancia;
    private long tempo;
    private double ritmoMedio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_corrida);
        Button pause = (Button) findViewById(R.id.botao_pausar);
        milisegundos = 0;
        estaContando = true;
        chronometer = (Chronometer) findViewById(R.id.tempo);
        pause = (Button) findViewById(R.id.botao_pausar);
        bandeira = (Button) findViewById(R.id.botao_bandeira);
        bandeira.setVisibility(View.INVISIBLE);
        ritmoMedio = 0;

        chronometer.setBase(SystemClock.elapsedRealtime() - milisegundos);
        chronometer.start();
    }


    public void iniciarEPausar(View view){
        if (estaContando){
            milisegundos = SystemClock.elapsedRealtime() - chronometer.getBase();
            estaContando = false;
            chronometer.stop();
            bandeira.setVisibility(View.VISIBLE);
        }else{
            estaContando = true;
            chronometer.setBase(SystemClock.elapsedRealtime() - milisegundos);
            chronometer.start();
            bandeira.setVisibility(View.INVISIBLE);
        }

    }

    public void finalizarCorrida(View view){
        tempo = milisegundos;
        Intent finalizar = new Intent(this, FinalizarCorridaActivity.class);
        finalizar.putExtra("tempo", tempo);
        finalizar.putExtra("distancia", distancia);
        finalizar.putExtra("ritmoMedio", ritmoMedio);
        startActivity(finalizar);
    }



}
