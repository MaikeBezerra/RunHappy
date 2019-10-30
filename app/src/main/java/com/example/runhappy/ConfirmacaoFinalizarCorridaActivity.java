package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ConfirmacaoFinalizarCorridaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_finalizar_corrida);
    }

    public void confirmar(View view){

        finish();
    }
}
