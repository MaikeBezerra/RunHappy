package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.runhappy.ui.corrida.CorridaViewModel;

public class ConfirmacaoFinalizarCorridaActivity extends AppCompatActivity {

    private CorridaViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_finalizar_corrida);
//        model = new CorridaViewModel(this);
//        model.setValuesAttributes();
    }

    public void confirmar(View view){
//        model.salvar();
        finish();
    }
}
