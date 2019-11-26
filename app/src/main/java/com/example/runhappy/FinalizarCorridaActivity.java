package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.runhappy.data.CorridaDAO;
import com.example.runhappy.data.SQLiteHandle;
import com.example.runhappy.data.UsuarioDAOSQLite;
import com.example.runhappy.model.Corrida;
import com.example.runhappy.model.Usuario;
import com.example.runhappy.ui.corrida.CorridaViewModel;

import java.text.DecimalFormat;

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
