package com.example.runhappy;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.CorridaDAOSQLite;
import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAO;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.transactions.Constantes;

public class AtividadeCorridaActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long milisegundos;
    private boolean estaContando;
    private Button pause;
    private Button bandeira;
    private double distancia;
    private long tempo;
    private double ritmoMedio;
    private CorridaDAO corridaDAO;
    private SQLiteHandle handle;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_corrida);

        handle = new SQLiteHandle(this);
        corridaDAO = new CorridaDAOSQLite(handle);
        usuarioDAO = new UsuarioDAOSQLite(handle);

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
        startActivityForResult(finalizar, Constantes.REQUEST_CONCLUIR);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constantes.REQUEST_CONCLUIR && resultCode == Constantes.REQUEST_CONCLUIR){
            //a definir os comandos para cadastrar corrida
            int idCorredor = usuarioDAO.findByEmail(TelaInicialActivity.getEmailUsuarioLogado()).getId();
            Corrida corrida = new Corrida(distancia, tempo, ritmoMedio, idCorredor);
            corridaDAO.adicionarCorrida(corrida);

            finish();
        } else if(requestCode == Constantes.REQUEST_CONCLUIR && resultCode == Constantes.REQUEST_CANCELAR){

        }
    }



}
