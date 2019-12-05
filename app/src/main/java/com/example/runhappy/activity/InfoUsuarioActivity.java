package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.runhappy.R;
import com.example.runhappy.ui.usuario.UsuarioListView;

public class InfoUsuarioActivity extends AppCompatActivity {

    private UsuarioListView listView;
    private TextView nomeUsuario;
    private String idBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        nomeUsuario = (TextView) findViewById(R.id.infoNome);
        nomeUsuario.setText(getIntent().getExtras().get("nomeUsuario").toString());
        idBusca = getIntent().getExtras().get("idBusca").toString();
    }

    public void verSeguidores(View view){
        Intent intent = new Intent(this, SeguidoresActivity.class);
        intent.putExtra("idBusca", idBusca);
        startActivity(intent);
    }

    public void verSeguindo(View view){
        Intent intent = new Intent(this, SeguindoActivity.class);
        intent.putExtra("idBusca", idBusca);
        startActivity(intent);
    }

    public void verHistorico(View view){
        Toast.makeText(this, "Histórico do corredor", Toast.LENGTH_SHORT).show();

    }

    public void seguirEdeixarDeSeguir(View view){

    }


}
