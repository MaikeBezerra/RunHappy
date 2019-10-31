package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Usuario;

import java.text.DecimalFormat;

public class FinalizarCorridaActivity extends AppCompatActivity {

    TextView textViewDistancia;
    TextView textViewTempo;
    TextView textViewRitmoMedio;

    private UsuarioDAOSQLite usuarioDAOSQLite;
    private SQLiteHandle handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_corrida);

        textViewDistancia = (TextView) findViewById(R.id.textViewDistancia);
        textViewTempo = (TextView) findViewById(R.id.textViewTempo);
        textViewRitmoMedio = (TextView) findViewById(R.id.textViewRitmoMedio);

        handle = new SQLiteHandle(this);

        if(getIntent().getExtras() != null){
            String distancia = getIntent().getExtras().get("distancia").toString();
            double auxDistancia = (double) getIntent().getExtras().get("distancia");

            long auxTempo = (long) getIntent().getExtras().get("tempo");
            int horas = (int) Math.floor((auxTempo/(3.6*1000000)));
            auxTempo -= horas;
            int minutos = (int) Math.floor((((auxTempo)/60000)));
            auxTempo -= minutos;
            int segundos = (int) Math.floor((auxTempo - minutos)/1000);
            auxTempo -= segundos;

            String tempo = horas + "h"+" "+minutos+"min"+" "+segundos+"s";
            String ritmoMedio = (String) getIntent().getExtras().get("ritmoMedio").toString();



            if (auxDistancia > 1000){
                distancia = (int) Math.floor(auxDistancia/1000)+"km"+" "+(int) (auxDistancia%1000)+"m";
            }else{
                distancia = String.valueOf((int) (auxDistancia%1000)+"m");
            }

            textViewDistancia.setText(distancia);
            textViewTempo.setText(tempo);
            textViewRitmoMedio.setText(ritmoMedio);
        }
    }

    public void cancelar(View view){
        finish();
    }

    public void concluir(View view){

        // a fazer: método de cadastrar a corrida feita
        usuarioDAOSQLite = new UsuarioDAOSQLite(handle);
        System.out.println(TelaInicialActivity.getEmailUsuarioLogado()+"bbbbbbbbbbbbbbbbb");
        Usuario usuario = usuarioDAOSQLite.findByEmail(TelaInicialActivity.getEmailUsuarioLogado());
        System.out.println(usuario.getNome());
        Corrida corrida = new Corrida((double) getIntent().getExtras().get("distancia"), (long) getIntent().getExtras().get("tempo"), (double) getIntent().getExtras().get("ritmoMedio"));
        usuario.addCorrida(corrida);
        usuarioDAOSQLite.editUsuario(usuario);

        System.out.println(usuario.getCorridas().size());


        Intent confirmacaoCorrida = new Intent(this, ConfirmacaoFinalizarCorridaActivity.class);
        startActivity(confirmacaoCorrida);

        setResult(1);

        finish();
    }
}
