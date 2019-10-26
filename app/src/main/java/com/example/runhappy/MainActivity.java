package com.example.runhappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

       // Button cadastrar = (Button) findViewById(R.id.botao_cadastre_se);

    }

    public void cadastrar(View view){
        Intent casdastro = new Intent(this, CadastroActivity.class);
        startActivity(casdastro);
    }
}
