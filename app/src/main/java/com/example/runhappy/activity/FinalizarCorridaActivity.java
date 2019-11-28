package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.runhappy.R;

public class FinalizarCorridaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_corrida);


    }

    public void cancelar(View view){
        finish();
    }

    public void concluir(View view){



        Intent confirmacaoCorrida = new Intent(this, ConfirmacaoFinalizarCorridaActivity.class);
        startActivity(confirmacaoCorrida);

        setResult(1);

        finish();
    }
}
