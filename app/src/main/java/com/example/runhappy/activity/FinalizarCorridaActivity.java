package com.example.runhappy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.runhappy.R;

public class FinalizarCorridaActivity extends AppCompatActivity {

    private EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_corrida);

        this.descricao = findViewById(R.id.edtDescricao);
    }

    public void cancelar(View view){
        finish();
    }

    public void concluir(View view){

        Intent confirmacaoCorrida = new Intent(this, ConfirmacaoFinalizarCorridaActivity.class);
        startActivity(confirmacaoCorrida);

        Intent corrida = new Intent(this, AtividadeCorridaActivity.class);
        corrida.putExtra("descricao", descricao.getText().toString());

        setResult(1, corrida);
        finish();
    }
}
